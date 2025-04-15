package talk.innertalk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCommentReportDto {

    private Long postId;
    private Long commentId;

    @NotBlank(message = "이유를 입력해 주세요.")
    private String reason;
}
