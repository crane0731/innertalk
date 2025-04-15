package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talk.innertalk.domain.Like;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.Post;
import talk.innertalk.dto.LikePostListDto;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.LikeRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberService memberService;
    private final PostService postService;

    //LikePostListDto를 생성 후 반환
    public List<LikePostListDto> getLikePostListDtos(Long memberId){
        return likeRepository.findByMemberId(memberId).stream().map(LikePostListDto::createDto)
                .toList();
    }


    @Transactional
    public void addLike(Long postId){
        //회원 조회
        Member loginMember = memberService.getLoginMember();

        //게시글 조회
        Post post = postService.findPostById(postId);

        //중복 확인
        existsLikeCheck(loginMember, post);


        //like 객체 생성
        Like like= createLike(loginMember, post);

        //게시글의 공감수 증가 시키기
        post.addLikeCount();

        //like 저장
        try{
            likeRepository.save(like);

        }catch (DataIntegrityViolationException e){
            throw new CustomException(ErrorMessages.EXISTS_LIKE);
        }

    }


    /**
     * like가 이미 DB에 있는지 확인
     */
    private void existsLikeCheck(Member loginMember, Post post) {
        boolean exists = likeRepository.existsByMemberIdAndPostId(loginMember.getId(), post.getId());
        if(exists){
            throw new CustomException(ErrorMessages.EXISTS_LIKE);
        }
    }



    /**
     * like 객체 생성
     */
    private Like createLike(Member loginMember, Post post) {
        return Like.builder()
                .member(loginMember)
                .post(post)
                .build();
    }

}
