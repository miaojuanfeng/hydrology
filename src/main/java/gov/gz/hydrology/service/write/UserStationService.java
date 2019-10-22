package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.UserStation;

import java.util.List;

public interface UserStationService {
	List<UserStation> selectByUserId(String userId);
	UserStation selectDefault(String userId);
	int insertBatch(List<UserStation> userStations);
	int deleteByUserId(String userId);
}
