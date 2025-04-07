package talk.innertalk.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talk.innertalk.domain.baseentity.BaseAuditingEntity;
@Entity
@Table(name = "comment_report")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReport extends BaseAuditingEntity {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_report_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id",nullable = false)
    private Comment comment;


    @Column(name = "reason",nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status",nullable = false)
    private ReportStatus reportStatus;




}
