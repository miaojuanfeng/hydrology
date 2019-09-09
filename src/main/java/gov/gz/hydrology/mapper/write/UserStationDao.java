package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserStationDao {
	List<Station> selectByUserId(@Param("userId") String userId);
}
