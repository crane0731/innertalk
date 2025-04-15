package talk.innertalk.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.Member;
import talk.innertalk.dto.*;
import talk.innertalk.service.LikeService;
import talk.innertalk.service.MemberService;
import talk.innertalk.service.PostService;
import talk.innertalk.service.ReportService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class MemberController {


    private final MemberService memberService;
    private final LikeService likeService;
    private final ReportService reportService;


    /**
     *  마이페이지를 보여주는 컨트롤러
     */
    @GetMapping("/mypage")
    public String members(Model model) {

        HomeMemberDto memberDto = getHomeMemberDto();

        //현재 로그인한 회원 조회
        Member loginMember = memberService.getLoginMember();

        //반환할 dto 생성
        MemberInfoResponseDto dto = MemberInfoResponseDto.createDto(loginMember);

        //공감한 게시물 정보를 담을 dto 생성
        List<LikePostListDto> likePostListDto = likeService.getLikePostListDtos(loginMember.getId());

        //신고한 게시물 정보를 담을 dto 생성
        List<ReportPostListDto> reportPostListDtos = reportService.getReportPostListDtos(loginMember.getId());


        dto.setLikePostListDtos(likePostListDto);

        dto.setReportPostListDtos(reportPostListDtos);

        //model에 담기
        model.addAttribute("memberInfoResponseDto", dto);
        model.addAttribute("memberDto", memberDto);

        return "mypage";
    }


    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/members/{memberId}")
    public String deleteMember(@PathVariable("memberId") Long memberId, HttpServletRequest request, HttpServletResponse response) {
        //회원 삭제
        memberService.deleteMember(memberId);

        // 삭제 후 강제 로그아웃
        forcelogout(request, response);

        return "redirect:/innertalk/home";
    }


    /**
     * 수동 로그아웃
     */
    private void forcelogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
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
