package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.dto.PostListDto;
import talk.innertalk.dto.SearchMemberDto;
import talk.innertalk.dto.SearchPostDto;
import talk.innertalk.dto.SearchReportDto;
import talk.innertalk.dto.admin.AdminMemberListDto;
import talk.innertalk.dto.admin.AdminReportInfoDto;
import talk.innertalk.service.MemberService;
import talk.innertalk.service.PostService;
import talk.innertalk.service.ReportService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/innertalk")
public class AdminController {

    private final MemberService memberService;
    private final ReportService reportService;
    private final PostService postService;

    /**
     * 관리자 페이지
     */
    @GetMapping("/admin")
    public String adminPage(@ModelAttribute("searchPostDto") SearchPostDto searchPostDto, @ModelAttribute("searchMemberDto") SearchMemberDto searchMemberDto,@ModelAttribute("searchReportDto") SearchReportDto searchReportDto, Model model) {
        
        //모든 게시글 조회
        List<PostListDto> allPostList = postService.findAllByCondition(searchPostDto);

        //모든 회원 조회
        List<AdminMemberListDto> allMemberList = memberService.findAllMemberByCondition(searchMemberDto);

        //모든 신고 조회
        List<AdminReportInfoDto> allReportList = reportService.findAllByCondition(searchReportDto);

        //모델에 담기
        model.addAttribute("allPostList", allPostList);
        model.addAttribute("allMemberList", allMemberList);
        model.addAttribute("allReportList", allReportList);

        return "adminPage";
    }



}
