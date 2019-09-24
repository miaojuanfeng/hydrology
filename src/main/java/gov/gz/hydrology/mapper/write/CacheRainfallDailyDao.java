package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CacheRainfallDailyDao {
    void insertBatch(@Param("list") List<Rainfall> rainfalls);
    void deleteByStcd(@Param("stcd") String stcd);
    List<Rainfall> selectByStcd(@Param("stcd") String stcd);
}
