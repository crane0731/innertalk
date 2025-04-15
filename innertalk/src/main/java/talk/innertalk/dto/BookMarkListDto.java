package talk.innertalk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.BookMark;
import talk.innertalk.util.DateFormatUtil;

@Getter
@Setter
public class BookMarkListDto {

    private Long id;
    private Long postId;
    private String postTitle;
    private String postCreatedDate;


    public BookMarkListDto() {
    }


    public static BookMarkListDto createDto(BookMark bookMark) {
            BookMarkListDto dto = new BookMarkListDto();
            dto.setId(bookMark.getId());
            dto.setPostId(bookMark.getPost().getId());
            dto.setPostTitle(bookMark.getPost().getTitle());
            dto.setPostCreatedDate(DateFormatUtil.DateFormat(bookMark.getPost().getCreatedDate()));

            return dto;

    }
}
