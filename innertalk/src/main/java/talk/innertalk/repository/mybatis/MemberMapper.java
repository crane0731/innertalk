package talk.innertalk.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import talk.innertalk.domain.Member;
import talk.innertalk.dto.SearchMemberDto;

import java.util.List;

@Mapper
public interface MemberMapper {

    /**
     * 이름에 따라  회원을 조회하는 동적 쿼리
      */
    List<Member> findAll(@Param("searchMemberDto") SearchMemberDto searchMemberDto);

}
