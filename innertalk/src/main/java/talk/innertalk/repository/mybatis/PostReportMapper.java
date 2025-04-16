package talk.innertalk.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import talk.innertalk.domain.PostReport;
import talk.innertalk.dto.SearchReportDto;
import talk.innertalk.dto.admin.AdminReportInfoDto;

import java.util.List;

@Mapper
public interface PostReportMapper {


    List<AdminReportInfoDto> findAll(@Param("searchReportDto") SearchReportDto searchReportDto);
}
