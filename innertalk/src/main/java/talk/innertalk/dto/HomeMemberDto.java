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

    public HomeMemberDto() {
    }

    @Builder
    public HomeMemberDto(String nickname) {
        this.nickname = nickname;
    }

    // Member -> HomeMemberDto 변환
    public static HomeMemberDto toEntity(Member member) {
        return HomeMemberDto.builder()
                .nickname(member.getNickname())
                .build();
    }
}
