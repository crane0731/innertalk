package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import talk.innertalk.domain.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {


    //member_id 와 post_id 가 일치하는 like 객체가있는지 확인
    @Query("select count (l) >0 from Like l where l.member.id=:memberId and l.post.id=:postId")
    boolean existsByMemberIdAndPostId(@Param("memberId") Long memberId, @Param("postId") Long postId);

    @Modifying
    @Query("DELETE FROM Like l WHERE l.post.id = :postId")
    void deleteByPostId(@Param("postId") Long postId);

    //member_id로 조회
    List<Like> findByMemberId(Long memberId);

}
