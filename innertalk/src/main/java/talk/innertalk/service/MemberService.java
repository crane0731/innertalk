package talk.innertalk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import talk.innertalk.domain.Member;
import talk.innertalk.exception.CustomException;
import talk.innertalk.exception.ErrorMessages;
import talk.innertalk.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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

}
