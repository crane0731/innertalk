package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.Comment;
import talk.innertalk.util.DateFormatUtil;


/**
 * 댓글 정보를 담은 DTO
 */
@Getter
@Setter
public class CommentDto {

    private Long id;
    private Long memberId;
    private String content;
    private Long reportCount;
    private String createdDate;


    public CommentDto() {
    }



    /**
     * Comment -> CommentDto 변환
     * @param comment
     * @return
     */
    public static CommentDto createDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setMemberId(comment.getMember().getId());
        commentDto.setContent(comment.getContent());
        commentDto.setReportCount(comment.getReportCount());
        commentDto.setCreatedDate(DateFormatUtil.DateFormat(comment.getCreatedDate()));
        return commentDto;
    }
}
