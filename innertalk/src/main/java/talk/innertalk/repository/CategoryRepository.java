package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talk.innertalk.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
