package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.User;

import java.util.List;

public interface StationService {
	Station selectByPrimaryKey(String userStcd);
	List<Station> getStationByType(String type);
}
