package gov.gz.hydrology.mapper.read2;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read2.Forecast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ForecastDao {
    List<Forecast> selectForecast(@Param("list") List list, @Param("fymdh") String fymdh, @Param("unitname") Integer unitname, @Param("startDay") String startDay, @Param("endDay") String endDay);
}
