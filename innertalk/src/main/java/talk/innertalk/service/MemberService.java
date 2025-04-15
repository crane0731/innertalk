package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talk.innertalk.domain.Like;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.MemberRole;
import talk.innertalk.domain.PostReport;
import talk.innertalk.dto.JoinRequestDto;
import talk.innertalk.dto.admin.AdminMemberListDto;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.LikeRepository;
import talk.innertalk.repository.MemberRepository;
import talk.innertalk.repository.PostReportRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LikeRepository likeRepository;
    private final PostReportRepository postReportRepository;

    /**
     * 관리자-모든 회원 조회 (관리자)
     */
    public List<AdminMemberListDto> findAllMember(){
        return memberRepository.findAll().stream().map(AdminMemberListDto::createDto).toList();
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void deleteMember(Long memberId) {


        //회원 조회
        Member member = findMember(memberId);

        //회원 아이디로 like 조회
        List<Like> likes = likeRepository.findByMemberId(memberId);

        //like 삭제
        likeRepository.deleteAll(likes);

        //회원 아이디로 Report 조회
        List<PostReport> postReports = postReportRepository.findByMemberId(memberId);

        //Report 삭제
        postReportRepository.deleteAll(postReports);


        //회원 삭제
        memberRepository.deleteById(memberId);

    }


    /**
     * 회원 조회
     */
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(()->new CustomException(ErrorMessages.NO_SUCH_MEMBER));
    }


    /**
     * @description 로그인한 회원 조회
     * @parameter x
     * @return Member
     */
    public Member getLoginMember() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //로그인된 사용자 인지 확인
        if (principal instanceof CustomUserDetail customUserDetail) {
            Long id = customUserDetail.getId();
            return findMember(id);
        }

        return null;
    }




    /**
     * 회원 가입
     */
    @Transactional
    public void join(JoinRequestDto dto) {

        //회원 생성
        Member member = createMember(dto);

        //회원 저장
        memberRepository.save(member);
    }

    /**
     * 패스워드와 패스워드체크가 일치하는지 검사하는 로직
     * @param password
     * @param passwordCheck
     */
    private void passwordChecked(String password, String passwordCheck) {
        if(!password.equals(passwordCheck)) {
            throw new CustomException(ErrorMessages.NOT_EQUAL_PASSWORD);
        }
    }

    /**
     * 중복 검사
     */
    private void duplicatedCheck(String email, String nickname) {
        if(memberRepository.findByEmail(email).isPresent()) {
            throw new CustomException(ErrorMessages.DUPLICATED_EMAIL);
        }

        if (memberRepository.findByNickname(nickname).isPresent()) {
            throw new CustomException(ErrorMessages.DUPLICATED_NICKNAME);
        }
    }


    /**
     * Member 생성
     * @param dto
     * @return
     */
    private Member createMember(JoinRequestDto dto) {

        //패스워드 인코딩
        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());

        return Member.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .nickname(dto.getNickname())
                .memberRole(MemberRole.USER)
                .build();
    }

}
