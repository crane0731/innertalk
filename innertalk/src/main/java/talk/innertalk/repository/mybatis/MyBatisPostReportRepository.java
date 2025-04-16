package talk.innertalk.repository.mybatis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talk.innertalk.domain.PostReport;
import talk.innertalk.dto.SearchReportDto;
import talk.innertalk.dto.admin.AdminReportInfoDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPostReportRepository {

    private final PostReportMapper postReportMapper;

    public List<AdminReportInfoDto> findAll(SearchReportDto searchReportDto) {
        return postReportMapper.findAll(searchReportDto);
    }

}
