package talk.innertalk.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import talk.innertalk.domain.baseentity.BaseTimeEntity;

@Entity
@Table(name = "bookmark")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMark extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    /**
     * 북마크 생성 메서드
     */
    public static BookMark createBookMark(Member member, Post post) {
        BookMark bookMark = new BookMark();
        bookMark.member = member;
        member.getBookmarks().add(bookMark);
        bookMark.post = post;
        return bookMark;
    }

}
