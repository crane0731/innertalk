package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.Comment;
import talk.innertalk.domain.Post;
import talk.innertalk.util.DateFormatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시물 상세 정보를 담은 DTO
 */

@Getter
@Setter
public class PostInfoDto {

    private Long id;
    private Long writerId;
    private String title;
    private String category;
    private String content;
    private Long commentCount;
    private Long likeCount;
    private Long viewCount;
    private Long reportCount;

    private List<CommentDto> commentDtoList = new ArrayList<>();

    private String createdDate;
    private String updatedDate;


    public PostInfoDto() {
    }


    /**
     * Post -> PostInfo 변환
     * @param post
     * @return
     */
    public static PostInfoDto createDto(Post post) {
        PostInfoDto postInfo = new PostInfoDto();
        postInfo.setId(post.getId());
        postInfo.setWriterId(post.getMember().getId());
        postInfo.setTitle(post.getTitle());
        postInfo.setCategory(post.getCategory().getName());
        postInfo.setContent(post.getContent());
        postInfo.setCommentCount(post.getCommentCount());
        postInfo.setLikeCount(post.getVoteCount());
        postInfo.setReportCount(post.getReportCount());
        postInfo.setViewCount(post.getViewCount());

        //댓글 생성
        List<Comment> comments = post.getComments();
        for (Comment comment : comments) {
            postInfo.commentDtoList.add(CommentDto.createDto(comment));
        }

        postInfo.setCreatedDate(DateFormatUtil.DateFormat(post.getCreatedDate()));
        postInfo.setUpdatedDate(DateFormatUtil.DateFormat(post.getUpdatedDate()));
        return postInfo;
    }

}
