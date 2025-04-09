package talk.innertalk.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import talk.innertalk.domain.baseentity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private MemberRole memberRole;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookMark> bookmarks=new ArrayList<>();


    @Builder
    public Member(String email, String password, String nickname,MemberRole memberRole) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.memberRole = memberRole;
    }


    /**
     * 연관관계 편의 메서드
     * @param post
     */
    public void addPost(Post post) {
        posts.add(post);
        post.setMember(this);
    }

}
