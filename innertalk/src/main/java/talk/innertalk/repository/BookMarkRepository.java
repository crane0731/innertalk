package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import talk.innertalk.domain.BookMark;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    @Modifying
    @Query("select b from BookMark b where b.post.id = :postId")
    List<BookMark> findByPostId(@Param("postId") Long postId);

    @Modifying
    @Query("DELETE FROM BookMark b WHERE b.post.id = :postId")
    void deleteByPostId(@Param("postId") Long postId);

}
