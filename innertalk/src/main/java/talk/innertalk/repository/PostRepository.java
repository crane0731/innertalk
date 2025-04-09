package talk.innertalk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import talk.innertalk.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT p FROM Post p")
    Page<Post> findPageAll(Pageable pageable);
}
