package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.BookMark;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.Post;
import talk.innertalk.dto.PostListDto;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;


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

}
