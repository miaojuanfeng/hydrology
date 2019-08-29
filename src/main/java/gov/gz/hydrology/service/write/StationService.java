package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Station;

public interface StationService {
	Station selectByPrimaryKey(String userStcd);
}
