package talk.innertalk.dto.admin;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.PostReport;
import talk.innertalk.util.DateFormatUtil;

/**
 * 관리자 전용 신고목록을 확인하기 위한 DTO
 */

@Getter
@Setter
public class AdminReportInfoDto {

    private Long id;
    private Long reporterId;
    private String reporterEmail;
    private Long reportedId;
    private String reportedEmail;
    private Long postId;
    private String reason;
    private String reportStatus;
    private String createdDate;


    /**
     * PostReport -> Dto 변환
     */
    public static AdminReportInfoDto createDto(PostReport report) {
        AdminReportInfoDto dto = new AdminReportInfoDto();
        dto.setId(report.getId());

        dto.setReporterId(report.getMember().getId());
        dto.setReporterEmail(report.getMember().getEmail());

        dto.setReportedId(report.getPost().getMember().getId());
        dto.setReportedEmail(report.getPost().getMember().getEmail());

        dto.setPostId(report.getPost().getId());
        dto.setReason(report.getReason());
        dto.setReportStatus(report.getReportStatus().toString());

        dto.setCreatedDate(DateFormatUtil.DateFormat(report.getCreatedDate()));

        return dto;

    }
}
