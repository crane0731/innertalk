package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talk.innertalk.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
