package talk.innertalk;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import talk.innertalk.domain.*;
import talk.innertalk.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        String encodedPassword = encodingPassword("1234");

        Member member = createMember(encodedPassword);

        Category category1 = new Category("일상");
        Category category2 = new Category("고민");
        Category category3 = new Category("연애");
        Category category4 = new Category("기타");




        Post post1 = createPost("테스트용 글입니다.", "안녕하세요 관리자 입니다. 테스트용 글입니다.",category1);
        Post post2 = createPost("북마크 테스트용 글입니다.", "안녕하세요 관리자 입니다. 북마크 테스트용 글입니다.",category1);



        member.addPost(post1);
        member.addPost(post2);

        memberRepository.save(member);


        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);

        postRepository.save(post1);
        postRepository.save(post2);

        Comment comment1 = createComment(member,"테스트용 댓글1 입니다.");
        Comment comment2 = createComment(member,"테스트용 댓글2 입니다.");
        Comment comment3 = createComment(member,"테스트용 댓글3 입니다.");
        Comment comment4 = createComment(member,"테스트용 댓글4 입니다.");

        post1.addComment(comment1);
        post1.addComment(comment2);
        post1.addComment(comment3);
        post1.addComment(comment4);


        Member findMember = memberRepository.findById(member.getId()).get();
        Post findPost = postRepository.findById(post2.getId()).get();

        postService.addBookMark(findMember, findPost);

    }

    private Comment createComment(Member member,String content) {
        return Comment.builder()
                .content(content)
                .member(member)
                .build();
    }

    private Post createPost(String title, String content, Category category) {
        return Post.builder()
                .title(title)
                .content(content)
                .category(category)
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
