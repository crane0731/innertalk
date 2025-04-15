package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.*;
import talk.innertalk.dto.AddCommentDto;
import talk.innertalk.dto.AddPostDto;
import talk.innertalk.dto.PostListDto;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final MemberService memberService;
    private final BookMarkRepository bookMarkRepository;
    private final PostReportRepository postReportRepository;
    private final LikeRepository likeRepository;

    /**
     * 북마크에 게시글을 추가
     */

    @Transactional
    public void addBookMark(Member member, Post post) {
        BookMark.createBookMark(member, post);
    }


    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(()->new CustomException(ErrorMessages.NO_SUCH_POST));
    }

    //게시글을 조회하고 조회수를 증가시킴
    @Transactional
    public Post findPostAndAddViewCount(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorMessages.NO_SUCH_POST));
        post.addViewCount();
        return post;
    }

    public List<PostListDto> findAll(){
        return postRepository.findAll().stream().map(PostListDto::createDto).toList();
    }


    /**
     * 페이징을 사용한 전체 조회
     */
    public List<PostListDto> findPageAll() {

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.Direction.DESC, "createdDate");
        Page<Post> page = postRepository.findPageAll(pageRequest);
        return page.map(PostListDto::createDto).toList();
    }


    /**
     * 게시글 저장
     */
    @Transactional
    public void savePost(AddPostDto dto) {

        Member loginMember = memberService.getLoginMember();

        Category category = categoryRepository.findById(dto.getCategoryId()).get();
        
        Post post = convertToPost(dto, category);

        loginMember.addPost(post);

        postRepository.save(post);

    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deletePost(Long postId) {


        //게시글의 아이디를 가지고 있는 모든 북마크 삭제
        bookMarkRepository.deleteByPostId(postId);

        //게시글의 아이디를 가지고 있는 모든 공감 삭제
        likeRepository.deleteByPostId(postId);

        //게시글의 아이디를 가지고 있는 모든 게시글 신고 삭제
        postReportRepository.deleteByPostId(postId);

        //게시글 삭제
        postRepository.deleteById(postId);
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public void deleteComment(Long postId, Long commentId) {

        Post post = findPostById(postId);

        Comment findComment = post.getComments().stream()
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorMessages.NO_SUCH_COMMENT));

        post.delComment(findComment);

    }

    /**
     * 게시글에 댓글 추가
     */

    @Transactional
    public void saveComment(AddCommentDto dto) {
        Post post = findPostById(dto.getPostId());

        Comment comment = convertToComment(dto);

        post.addComment(comment);

    }

    /**
     * AddCommentDto ->Comment 변환
     * @param dto
     * @return
     */
    private Comment convertToComment(AddCommentDto dto) {
        return Comment.builder()
                .member(memberService.getLoginMember())
                .content(dto.getContent())
                .build();
    }


    /**
     * AddPostDto -> Post 변환
     * @param dto
     * @param category
     * @return
     */
    private Post convertToPost(AddPostDto dto, Category category) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(category)
                .build();
    }


}
