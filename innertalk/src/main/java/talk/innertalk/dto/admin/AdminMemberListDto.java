package talk.innertalk.dto.admin;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.Member;
import talk.innertalk.util.DateFormatUtil;

@Getter
@Setter
public class AdminMemberListDto {


    private Long id;
    private String email;
    private String nickname;
    private String role;
    private Long reportedCount;
    private Long likedCount;
    private String createdDate;
    private String updatedDate;


    public static AdminMemberListDto createDto(Member member){
        AdminMemberListDto dto = new AdminMemberListDto();
        dto.setId(member.getId());
        dto.setEmail(member.getEmail());
        dto.setNickname(member.getNickname());
        dto.setReportedCount(member.getReportedCount());
        dto.setLikedCount(member.getLikedCount());
        dto.setRole(member.getMemberRole().toString());
        dto.setCreatedDate(DateFormatUtil.DateFormat(member.getCreatedDate()));
        dto.setUpdatedDate(DateFormatUtil.DateFormat(member.getUpdatedDate()));
        return dto;
    }
}
