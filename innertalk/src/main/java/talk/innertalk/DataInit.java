package talk.innertalk;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.BookMark;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.MemberRole;
import talk.innertalk.domain.Post;
import talk.innertalk.repository.MemberRepository;
import talk.innertalk.repository.PostRepository;
import talk.innertalk.service.PostService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        String encodedPassword = encodingPassword("1234");

        Member member = createMember(encodedPassword);

        Post post1 = createPost("테스트용 글입니다.", "안녕하세요 관리자 입니다. 테스트용 글입니다.");
        Post post2 = createPost("북마크 테스트용 글입니다.", "안녕하세요 관리자 입니다. 북마크 테스트용 글입니다.");

        member.addPost(post1);
        member.addPost(post2);

        memberRepository.save(member);


        postRepository.save(post1);
        postRepository.save(post2);


        Member findMember = memberRepository.findById(member.getId()).get();
        Post findPost = postRepository.findById(post2.getId()).get();
        postService.addBookMark(findMember, findPost);

    }

    private Post createPost(String title, String content) {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
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
