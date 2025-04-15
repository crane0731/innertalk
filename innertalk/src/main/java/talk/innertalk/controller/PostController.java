package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.Post;
import talk.innertalk.dto.*;
import talk.innertalk.repository.BookMarkRepository;
import talk.innertalk.repository.LikeRepository;
import talk.innertalk.repository.PostReportRepository;
import talk.innertalk.service.MemberService;
import talk.innertalk.service.PostService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class PostController {

    private final MemberService memberService;
    private final PostService postService;
    private final BookMarkRepository bookMarkRepository;
    private final LikeRepository likeRepository;
    private final PostReportRepository postReportRepository;
    /**
     * 게시글 보기
     */
    @GetMapping("/posts/{id}")
    public String postPage(@PathVariable("id")Long id, Model model) {

        HomeMemberDto memberDto = getHomeMemberDto();

        Post post = postService.findPostAndAddViewCount(id);
        PostInfoDto postInfoDto = PostInfoDto.createDto(post);

        //로그인한 회원의 id
        Long loginMemberId = memberService.getLoginMember().getId();
        LoginMemberIdDto loginMemberIdDto = LoginMemberIdDto.createDto(loginMemberId);

        //빈 댓글Dto
        AddCommentDto addCommentDto = new AddCommentDto();

        //빈 게시글 신고 Dto
        AddPostReportDto addPostReportDto = new AddPostReportDto();

        //빈 댓글 신고 Dto
        AddCommentReportDto addCommentReportDto = new AddCommentReportDto();

        //북마크가 된 게시물인지 확인
        boolean isBookmarked = bookMarkRepository.existsByMemberIdAndPostId(loginMemberId, id);

        //공감이 된 게시물인지 확인
        boolean isLiked = likeRepository.existsByMemberIdAndPostId(loginMemberId, id);

        //신고된 게시물인지 확인
        boolean isPostReported = postReportRepository.existsByMemberIdAndPostId(loginMemberId, id);

        model.addAttribute("memberDto", memberDto);
        model.addAttribute("postInfoDto", postInfoDto);
        model.addAttribute("addCommentDto", addCommentDto);
        model.addAttribute("addPostReportDto",addPostReportDto);
        model.addAttribute("addCommentReportDto",addCommentReportDto);
        model.addAttribute("loginMemberIdDto", loginMemberIdDto);
        model.addAttribute("isBookmarked", isBookmarked);
        model.addAttribute("isLiked",isLiked);
        model.addAttribute("isPostReported",isPostReported);

        return "post";
    }

    /**
     * 게시글 쓰기 페이지 이동
     */
    @GetMapping("/write")
    public String writePage(Model model) {

        HomeMemberDto memberDto = getHomeMemberDto();


        AddPostDto addPostDto = new AddPostDto();
        model.addAttribute("addPostDto", addPostDto);
        model.addAttribute("memberDto", memberDto);

        return "writePage";
    }

    /**
     * 게시글 쓰기
     */
    @PostMapping("/write")
    public String writePost(@ModelAttribute("addPostDto") AddPostDto addPostDto) {
        postService.savePost(addPostDto);

        return "redirect:/innertalk/home";
    }

    /**
     * 댓글 쓰기
     */
    @PostMapping("/comments")
    public String writeComment(@ModelAttribute("addCommentDto") AddCommentDto addCommentDto) {
        postService.saveComment(addCommentDto);

        Long postId = addCommentDto.getPostId();

        return "redirect:/innertalk/posts/" + postId;
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/delete/posts/{id}")
    public String deletePost(@PathVariable("id")Long id) {
        postService.deletePost(id);

        return "redirect:/innertalk/home";
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable("postId")Long postId,@PathVariable("commentId")Long commentId) {
        postService.deleteComment(postId,commentId);

        return "redirect:/innertalk/posts/" + postId;

    }


    /**
     * HomeMemberDto 조회
     */
    private HomeMemberDto getHomeMemberDto() {
        Member loginMember = memberService.getLoginMember();

        HomeMemberDto memberDto;

        if(loginMember != null) {
            memberDto=HomeMemberDto.toEntity(loginMember);
        }

        else {
            memberDto=new HomeMemberDto();
        }
        return memberDto;
    }


}
