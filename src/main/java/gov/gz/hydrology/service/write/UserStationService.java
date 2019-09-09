package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Station;

import java.util.List;

public interface UserStationService {
	List<Station> selectByUserId(String userId);
}
