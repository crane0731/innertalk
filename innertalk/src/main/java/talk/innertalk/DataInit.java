package talk.innertalk;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.MemberRole;
import talk.innertalk.repository.MemberRepository;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        String encodedPassword = encodingPassword("1234");

        Member member = createMember(encodedPassword);

        memberRepository.save(member);
    }


    private Member createMember(String encodedPassword) {
        return Member.builder()
                .email("dlwnsgkr8318@naver.com")
                .password(encodedPassword)
                .nickname("관리자")
                .memberRole(MemberRole.ADMIN)
                .build();
    }


    private String encodingPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
