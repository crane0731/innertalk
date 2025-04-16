package talk.innertalk.repository.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talk.innertalk.domain.Post;
import talk.innertalk.dto.SearchPostDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPostRepository{

    private final PostMapper postMapper;

    public List<Post> findAll(SearchPostDto searchPostDto){
        return postMapper.findAll(searchPostDto);
    }



}
