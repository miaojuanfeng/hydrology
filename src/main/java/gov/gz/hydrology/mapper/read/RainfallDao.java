package gov.gz.hydrology.mapper.read;

import gov.gz.hydrology.entity.write.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RainfallDao {
    Station selectByPrimaryKey(@Param("stcd") String stcd);
}
