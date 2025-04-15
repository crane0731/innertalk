package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talk.innertalk.domain.*;
import talk.innertalk.dto.AddCommentReportDto;
import talk.innertalk.dto.AddPostReportDto;
import talk.innertalk.dto.ReportPostListDto;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.PostReportRepository;

import java.util.List;

/**
 * 신고 관련 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final PostReportRepository postReportRepository;
    private final MemberService memberService;
    private final PostService postService;


    /**
     *  ReportPostListDto 리스트를 반환
     */
    public List<ReportPostListDto> getReportPostListDtos(Long memberId){
        return postReportRepository.findByMemberId(memberId).stream().map(ReportPostListDto::createDto).toList();
    }

    /**
     * 게시글 신고
     */
    @Transactional
    public void reportPost(AddPostReportDto dto){
        //로그인한 회원 조회
        Member loginMember = memberService.getLoginMember();

        //게시글 조회
        Post post = postService.findPostById(dto.getPostId());

        //회원 - 게시글 로된 신고가 있는지 중복 확인
        existsPostReportCheck(loginMember, post);

        //게시글신고 객체 생성
        PostReport postReport = PostReport.create(loginMember, post, dto.getReason());

        //게시글 신고 저장
        try{
            postReportRepository.save(postReport);
        }catch (DataIntegrityViolationException e){
            throw new CustomException(ErrorMessages.EXISTS_REPORT_POST);
        }

        post.addReportCount();
    }


    /**
     *회원 - 게시글 로된 신고가 있는지 중복 확인
     */
    private void existsPostReportCheck(Member loginMember, Post post) {
        boolean exists = postReportRepository.existsByMemberIdAndPostId(loginMember.getId(), post.getId());
        if(exists){
            throw new CustomException(ErrorMessages.EXISTS_REPORT_POST);
        }
    }


}
