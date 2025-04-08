package talk.innertalk.controller;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import talk.innertalk.domain.Member;
import talk.innertalk.dto.HomeMemberDto;
import talk.innertalk.service.CustomUserDetail;
import talk.innertalk.service.MemberService;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class HomeController {

    private final MemberService memberService;

    @GetMapping({"", "/"})
    public String redirectToHome() {
        return "redirect:/innertalk/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        Member loginMember = memberService.getLoginMember();

        HomeMemberDto memberDto;

        if(loginMember != null) {
            memberDto=HomeMemberDto.toEntity(loginMember);
        }

        else {
            memberDto=new HomeMemberDto();
        }

        model.addAttribute("memberDto", memberDto);



        return "home";
    }


}
