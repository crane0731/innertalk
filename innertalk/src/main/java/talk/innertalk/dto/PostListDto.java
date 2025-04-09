package talk.innertalk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.Post;
import talk.innertalk.util.DateFormatUtil;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PostListDto {

    private Long id;
    private String title;
    private String createdDate;


    public static PostListDto createDto(Post post) {
        PostListDto dto = new PostListDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());

        // 날짜 포맷 지정
        dto.setCreatedDate(DateFormatUtil.DateFormat(post.getCreatedDate()));

        return dto;
    }

}
