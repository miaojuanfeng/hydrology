package gov.gz.hydrology.mapper.write;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import gov.gz.hydrology.entity.write.Station;

@Mapper
public interface StationDao {
	Station selectByPrimaryKey(@Param("userStcd") String userStcd);
}
