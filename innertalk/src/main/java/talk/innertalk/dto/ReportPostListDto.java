package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.Post;
import talk.innertalk.domain.PostReport;
import talk.innertalk.util.DateFormatUtil;

@Getter
@Setter
public class ReportPostListDto {

    private Long id;
    private Long postId;
    private String postTitle;
    private String postCreatedDate;

    public ReportPostListDto() {

    }


    public static ReportPostListDto createDto(PostReport report) {
        ReportPostListDto dto = new ReportPostListDto();
        dto.setId(report.getId());
        dto.setPostId(report.getPost().getId());
        dto.setPostTitle(report.getPost().getTitle());
        dto.setPostCreatedDate(DateFormatUtil.DateFormat(report.getPost().getCreatedDate()));
        return dto;
    }
}
