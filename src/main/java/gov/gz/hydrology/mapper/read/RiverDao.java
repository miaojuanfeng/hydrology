package gov.gz.hydrology.mapper.read;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RiverDao {
    List<River> selectRiverTime(@Param("stcd") String stcd, @Param("startDay") String startDay, @Param("endDay") String endDay);
    List<River> selectRiverQRange(@Param("stcd") String stcd, @Param("forecastTime") String forecastTime, @Param("affectTime") String affectTime);
    List<River> selectRiverZRange(@Param("stcd") String stcd, @Param("forecastTime") String forecastTime, @Param("affectTime") String affectTime);
    River selectRiverLast(@Param("stcd") String stcd);
}
