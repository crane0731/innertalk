package talk.innertalk.dto;

import lombok.Getter;
import lombok.Setter;
import talk.innertalk.domain.BookMark;
import talk.innertalk.domain.Member;
import talk.innertalk.domain.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * 회원정보 응답 DTO
 */
@Getter
@Setter
public class MemberInfoResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private String memberRole;
    private List<PostListDto> postDtos=new ArrayList<>();
    private List<BookMarkListDto> BookMarkDtos=new ArrayList<>();
    private List<LikePostListDto> likePostListDtos= new ArrayList<>();
    private List<ReportPostListDto> reportPostListDtos = new ArrayList<>();


    public MemberInfoResponseDto() {

    }

    public static MemberInfoResponseDto createDto(Member member) {
        MemberInfoResponseDto dto = new MemberInfoResponseDto();
        dto.setId(member.getId());
        dto.setEmail(member.getEmail());
        dto.setNickname(member.getNickname());
        dto.setMemberRole(member.getMemberRole().toString());

        //PostDto 생성
        List<Post> posts = member.getPosts();
        for (Post post : posts) {
            dto.getPostDtos().add(PostListDto.createDto(post));
        }

        //BookMarkListDto 생성
        List<BookMark> bookmarks = member.getBookmarks();
        for (BookMark bookmark : bookmarks) {
            dto.getBookMarkDtos().add(BookMarkListDto.createDto(bookmark));
        }

        return dto;
    }



}
