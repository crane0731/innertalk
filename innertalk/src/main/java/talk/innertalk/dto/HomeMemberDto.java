package talk.innertalk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import talk.innertalk.domain.Member;

/**
 * Home 페이지에 뿌릴 Member 정보를 담은 DTO
 */
@Getter
@Setter
public class HomeMemberDto {

    private String nickname;
    private String role;

    public HomeMemberDto() {
    }

    @Builder
    public HomeMemberDto(String nickname,String role) {
        this.nickname = nickname;
        this.role = role;
    }

    // Member -> HomeMemberDto 변환
    public static HomeMemberDto toEntity(Member member) {
        return HomeMemberDto.builder()
                .nickname(member.getNickname())
                .role(member.getMemberRole().toString())
                .build();
    }
}
