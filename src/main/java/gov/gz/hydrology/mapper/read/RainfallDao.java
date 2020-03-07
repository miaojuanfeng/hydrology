package gov.gz.hydrology.mapper.read;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.write.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface RainfallDao {
    List<Rainfall> selectRainfallTotal(@Param("list") List list, @Param("startDay") String startDay, @Param("endDay") String endDay);
    List<Rainfall> selectRainfallDaily(@Param("list") List list, @Param("startDay") String startDay, @Param("endDay") String endDay);
    List<Rainfall> selectRainfallRange(@Param("list") List list, @Param("forecastTime") String forecastTime, @Param("affectTime") String affectTime);
}
