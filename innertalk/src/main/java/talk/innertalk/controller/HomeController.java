package talk.innertalk.controller;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talk.innertalk.domain.Member;
import talk.innertalk.dto.HomeMemberDto;
import talk.innertalk.dto.PostListDto;
import talk.innertalk.service.CustomUserDetail;
import talk.innertalk.service.MemberService;
import talk.innertalk.service.PostService;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class HomeController {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping({"", "/"})
    public String redirectToHome() {
        return "redirect:/innertalk/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        HomeMemberDto memberDto = getHomeMemberDto();

        List<PostListDto> postListDtos = getPostListDtos();


        model.addAttribute("postListDtos", postListDtos);
        model.addAttribute("memberDto", memberDto);



        return "home";
    }



    /**
     *PostListDtos 조회
     */
    private List<PostListDto> getPostListDtos() {
        List<PostListDto> postListDtos = postService.findPageAll();
        return postListDtos;
    }

    /**
     * HomeMemberDto 조회
     */
    private HomeMemberDto getHomeMemberDto() {
        log.info("여긴가?????");
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
