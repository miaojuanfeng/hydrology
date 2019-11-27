package gov.gz.hydrology.mapper.read;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RiverDao {
    List<River> selectRiverTime(@Param("stcd") String stcd);
    List<River> selectRiverRange(@Param("stcd") String stcd);
}
