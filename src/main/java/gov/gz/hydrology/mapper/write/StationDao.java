package gov.gz.hydrology.mapper.write;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import gov.gz.hydrology.entity.write.Station;

import java.util.List;
import java.util.Map;

@Mapper
public interface StationDao {
	Station selectByPrimaryKey(@Param("stcd") String stcd);
	List<Station> selectStationByType(@Param("type") String type);
	List<Map> selectChildStationByStcd(@Param("stcd") String stcd);
	List<Map> selectQtStation(@Param("stcd") String stcd);
}
