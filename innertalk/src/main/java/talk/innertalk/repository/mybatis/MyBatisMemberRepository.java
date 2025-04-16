package talk.innertalk.repository.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talk.innertalk.domain.Member;
import talk.innertalk.dto.SearchMemberDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository {

    private final MemberMapper memberMapper;

    public List<Member> findAll(SearchMemberDto searchMemberDto) {
        return memberMapper.findAll(searchMemberDto);
    }

}
