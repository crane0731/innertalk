package talk.innertalk.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talk.innertalk.domain.baseentity.BaseAuditingEntity;


@Entity
@Table(
        name = "post_report",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"member_id", "post_id"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReport  extends BaseAuditingEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status",nullable = false)
    private ReportStatus reportStatus;


    /**
     * 생성 메서드
     */
    public static PostReport create(Member member , Post post , String reason){
        PostReport postReport = new PostReport();
        postReport.member=member;
        postReport.post=post;
        postReport.reason= reason;
        postReport.reportStatus=ReportStatus.PENDING;
        return postReport;
    }


    /**
     * 신고 상태 변경 - 승인
     */
    public void reportApprove(){
        reportStatus=ReportStatus.APPROVED;
    }

    /**
     * 신고 상태 변경 - 거절
     */
    public void reportReject(){
        reportStatus=ReportStatus.REJECTED;
    }


}
