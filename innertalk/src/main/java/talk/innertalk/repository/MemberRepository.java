package talk.innertalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import talk.innertalk.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
