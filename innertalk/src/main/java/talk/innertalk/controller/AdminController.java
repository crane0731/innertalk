package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.dto.admin.AdminMemberListDto;
import talk.innertalk.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/innertalk")
public class AdminController {

    private final MemberService memberService;


    /**
     * 관리자 페이지
     */
    @GetMapping("/admin")
    public String adminPage(Model model) {

        //모든 회원 조회
        List<AdminMemberListDto> allMemberList = memberService.findAllMember();

        //모델에 담기
        model.addAttribute("allMemberList", allMemberList);



        return "adminPage";
    }

}
