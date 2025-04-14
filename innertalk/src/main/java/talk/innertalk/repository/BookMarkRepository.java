package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import talk.innertalk.domain.BookMark;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.Post;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    @Modifying
    @Query("select b from BookMark b where b.post.id = :postId")
    List<BookMark> findByPostId(@Param("postId") Long postId);

    @Modifying
    @Query("DELETE FROM BookMark b WHERE b.post.id = :postId")
    void deleteByPostId(@Param("postId") Long postId);

    //북마크가 존재하는지 확인
    @Query("select count(b)>0 from BookMark b where b.member.id=:memberId and b.post.id=:postId")
    boolean existsByMemberIdAndPostId(@Param("memberId") Long memberId, @Param("postId") Long postId);

    List<BookMark> findByMemberAndPost(Member member, Post post);
}
