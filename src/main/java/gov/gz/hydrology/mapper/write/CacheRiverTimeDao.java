package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.write.CacheRiverTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mapper
public interface CacheRiverTimeDao {
    void insertBatch(@Param("list") List<River> rivers);
    void deleteByStcd(@Param("stcd") String stcd);
    List<River> selectRiverTime(@Param("stcd") String stcd);
}
