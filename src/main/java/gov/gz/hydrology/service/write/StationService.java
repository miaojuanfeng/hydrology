package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.User;

import java.util.List;
import java.util.Map;

public interface StationService {
	Station selectByPrimaryKey(String stcd);
	List<Station> selectStationByType(String type);
	List<Map> selectChildStationByStcd(String stcd);
	List<Map> selectQtStation(String stcd);
}
