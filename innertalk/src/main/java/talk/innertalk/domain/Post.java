package talk.innertalk.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import talk.innertalk.domain.baseentity.BaseAuditingEntity;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseAuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "comment_count", nullable = false)
    @ColumnDefault("0")
    private Long commentCount=0L;

    @Column(name = "vote_count", nullable = false)
    @ColumnDefault("0")
    private Long voteCount=0L;

    @Column(name = "report_count", nullable = false)
    @ColumnDefault("0")
    private Long reportCount=0L;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments=new ArrayList<>();

    @Builder
    public Post(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }


    protected void setMember(Member member) {
        this.member = member;
    }


    /**
     * 연관관계 편의 메서드 : 댓글 추가
     */
    public void addComment(Comment comment) {
        addCommentCount();
        comments.add(comment);
        comment.setPost(this);
    }

    /**
     * 연관관계 편의 메서드 : 댓글 제거
     */
    public void delComment(Comment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }

    /**
     * 댓글 수 증가
     */
    private void addCommentCount(){
        commentCount++;
    }

}
