package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.Post;

/**
 * 게시글 작성 DTO
 */
@Setter
@Getter
public class AddPostDto {

    private String title;
    private String content;
    private Long categoryId;

    public AddPostDto() {

    }


}
