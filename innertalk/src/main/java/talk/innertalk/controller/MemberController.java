package talk.innertalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
