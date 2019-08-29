package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.write.Station;

public interface RainfallService {
	Station selectByPrimaryKey(String stcd);
}
