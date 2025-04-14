package talk.innertalk.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talk.innertalk.domain.BookMark;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.Post;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.BookMarkRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookMarkService {


    private final MemberService memberService;
    private final BookMarkRepository bookMarkRepository;
    private final PostService postService;

    /**
     * 북마크 저장
     */
    @Transactional
    public void saveBookMark(Long postId) {

        //로그인한 회원 조회
        Member loginMember = memberService.getLoginMember();

        //게시글 조회 + 조회수 감소
        Post post = postService.findPostById(postId);
        post.minusViewCount();


        log.info("지금 조회된 게시글 id={}",post.getId());

        //북마크 중복 확인
        duplicatedBookmarkCheck(loginMember.getId(), post.getId());

        //북마크 객체 생성
        BookMark bookMark = BookMark.createBookMark(loginMember, post);

        //북마크 저장
        try {
            bookMarkRepository.save(bookMark);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorMessages.DUPLICATED_BOOKMARK);
        }
    }


    /**
     * 북마크 중복 확인
     */
    private void duplicatedBookmarkCheck(Long memberId, Long postId) {
        boolean exists = bookMarkRepository.existsByMemberIdAndPostId(memberId, postId);
        log.warn("exists={}", exists);
        if (exists) {
            log.warn("여기서 터짐");
            throw new CustomException(ErrorMessages.DUPLICATED_BOOKMARK);
        }
    }



}
