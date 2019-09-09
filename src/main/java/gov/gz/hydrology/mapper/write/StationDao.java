package gov.gz.hydrology.mapper.write;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import gov.gz.hydrology.entity.write.Station;

import java.util.List;

@Mapper
public interface StationDao {
	Station selectByPrimaryKey(@Param("stcd") String stcd);
	List<Station> selectStationByType(@Param("type") String type);
}
