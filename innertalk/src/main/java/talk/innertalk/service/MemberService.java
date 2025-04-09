package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.MemberRole;
import talk.innertalk.dto.JoinRequestDto;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
