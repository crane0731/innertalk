package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.Like;
import talk.innertalk.domain.Post;
import talk.innertalk.util.DateFormatUtil;

@Getter
@Setter
public class LikePostListDto {

    private Long id;
    private Long postId;
    private String postTitle;
    private String postCreatedDate;

    public LikePostListDto() {
    }


    public static LikePostListDto createDto(Like like) {
        LikePostListDto dto = new LikePostListDto();
        dto.setId(like.getId());
        dto.setPostId(like.getPost().getId());
        dto.setPostTitle(like.getPost().getTitle());
        dto.setPostCreatedDate(DateFormatUtil.DateFormat(like.getPost().getCreatedDate()));
        return dto;
    }
}
