package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talk.innertalk.domain.BookMark;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

}
