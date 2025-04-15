package talk.innertalk.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import talk.innertalk.domain.baseentity.BaseAuditingEntity;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseAuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Column(name = "content", nullable = false)
    private String content;
    
    @Builder
    public Comment(Member member, String content) {
        this.member = member;
        this.content = content;
    }

    @Builder
    public Comment(String content) {
        this.content = content;
    }


}
