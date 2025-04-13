package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import talk.innertalk.domain.Comment;

@Getter
@Setter
public class AddCommentDto {

    private Long postId;
    private String content;


    public AddCommentDto() {
    }


}
