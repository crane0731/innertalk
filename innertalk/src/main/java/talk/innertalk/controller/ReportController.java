package talk.innertalk.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import talk.innertalk.domain.PostReport;
import talk.innertalk.dto.AddCommentReportDto;
import talk.innertalk.dto.AddPostReportDto;
import talk.innertalk.service.ReportService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/innertalk")
public class ReportController {
    private final ReportService reportService;

    /**
     * 게시글 신고
     */
    @PostMapping("/report/posts/{postId}")
    public String reportPost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("addPostReportDto") AddPostReportDto addPostReportDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // 에러가 있으면 에러 메시지를 flashAttribute로 전달
            redirectAttributes.addFlashAttribute("hasErrors", true);
            redirectAttributes.addFlashAttribute("errorMessage", "신고 사유를 정확히 입력해주세요.");
            return "redirect:/innertalk/posts/" + postId;
        }

        reportService.reportPost(addPostReportDto);
        return "redirect:/innertalk/posts/" + postId;
    }


    /**
     * 신고 승인
     */
    @PostMapping("/approve/report/{reportId}")
    public String approveReport(@PathVariable("reportId") Long reportId){

        //report 조회후 승인
        reportService.reportApprove(reportId);

        return "redirect:/innertalk/admin";
    }


    /**
     * 신고 거절
     */
    @PostMapping("/reject/report/{reportId}")
    public String rejectReport(@PathVariable("reportId") Long reportId){

        //report 조회후 거절
        reportService.reportReject(reportId);

        return "redirect:/innertalk/admin";
    }


}
