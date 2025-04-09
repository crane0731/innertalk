package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.Member;
import talk.innertalk.dto.MemberInfoResponseDto;
import talk.innertalk.service.MemberService;
import talk.innertalk.service.PostService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class MemberController {


    private final MemberService memberService;
    private final PostService postService;


    /**
     *  마이페이지를 보여주는 컨트롤러
     */
    @GetMapping("/mypage")
    public String members(Model model) {

        //현재 로그인한 회원 조회
        Member loginMember = memberService.getLoginMember();

        //반환할 dto 생성
        MemberInfoResponseDto dto = MemberInfoResponseDto.createDto(loginMember);

        //model에 담기
        model.addAttribute("memberInfoResponseDto", dto);

        return "mypage";
    }

}
