package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;

/*
로그인한 회원의 아이디를 담은 DTO
 */
@Getter
@Setter
public class LoginMemberIdDto {

    private Long memberId;

    public LoginMemberIdDto() {
    }

    public static LoginMemberIdDto createDto(Long id){
        LoginMemberIdDto dto = new LoginMemberIdDto();
        dto.setMemberId(id);
        return dto;
    }
}
