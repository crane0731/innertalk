package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import talk.innertalk.domain.PostReport;

import java.util.List;

public interface PostReportRepository extends JpaRepository<PostReport,Long> {

    /**
     * 회원 - 게시글 있는 지 확인
     */
    @Query("select count (p) >0 from PostReport p  where p.member.id=:memberId and p.post.id=:postId")
    boolean existsByMemberIdAndPostId(@Param("memberId") Long memberId, @Param("postId") Long postId);

    @Modifying
    @Query("DELETE FROM PostReport p WHERE p.post.id = :postId")
    void deleteByPostId(@Param("postId") Long postId);


    /**
     * member_id로 PostReport를 조회
     */
    List<PostReport> findByMemberId(Long memberId);

}
