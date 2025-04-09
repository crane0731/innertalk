package talk.innertalk.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.MemberRole;

/**
 * 회원 가입 요청 DTO
 */
@Getter
@Setter
public class JoinRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank
    private String nickname;


}
