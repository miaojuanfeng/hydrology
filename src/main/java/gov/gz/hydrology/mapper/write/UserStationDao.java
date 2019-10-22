package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.UserStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserStationDao {
	List<UserStation> selectByUserId(@Param("userId") String userId);
	UserStation selectDefault(@Param("userId") String userId);
	int insertBatch(List<UserStation> userStations);
	int deleteByUserId(@Param("userId") String userId);
}
