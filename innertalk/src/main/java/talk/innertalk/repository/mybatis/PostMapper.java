package talk.innertalk.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import talk.innertalk.domain.Post;
import talk.innertalk.dto.SearchPostDto;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> findAll(@Param("searchPostDto") SearchPostDto searchPostDto);

}
