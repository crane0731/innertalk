package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.Post;
import talk.innertalk.dto.AddCommentDto;
import talk.innertalk.dto.AddPostDto;
import talk.innertalk.dto.LoginMemberIdDto;
import talk.innertalk.dto.PostInfoDto;
import talk.innertalk.repository.BookMarkRepository;
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

    /**
     * 게시글 보기
     */
    @GetMapping("/posts/{id}")
    public String postPage(@PathVariable("id")Long id, Model model) {
        Post post = postService.findPostAndAddViewCount(id);
        PostInfoDto postInfoDto = PostInfoDto.createDto(post);

        //로그인한 회원의 id
        Long loginMemberId = memberService.getLoginMember().getId();
        LoginMemberIdDto loginMemberIdDto = LoginMemberIdDto.createDto(loginMemberId);

        //빈 댓글Dto
        AddCommentDto addCommentDto = new AddCommentDto();

        //북마크가 된 게시물인지 확인
        boolean isBookmarked = bookMarkRepository.existsByMemberIdAndPostId(loginMemberId, id);

        model.addAttribute("postInfoDto", postInfoDto);
        model.addAttribute("addCommentDto", addCommentDto);
        model.addAttribute("loginMemberIdDto", loginMemberIdDto);
        model.addAttribute("isBookmarked", isBookmarked);

        return "post";
    }

    /**
     * 게시글 쓰기 페이지 이동
     */
    @GetMapping("/write")
    public String writePage(Model model) {

        AddPostDto addPostDto = new AddPostDto();
        model.addAttribute("addPostDto", addPostDto);

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



}
