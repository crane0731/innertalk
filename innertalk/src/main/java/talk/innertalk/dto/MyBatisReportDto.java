package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyBatisReportDto {

    private Long reportId;

    private Long memberId;
    private String memberEmail;
    private String memberName;

    private Long postId;


}
