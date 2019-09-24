package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;

import java.util.List;

public interface CacheRainfallDailyService {
	void insertBatch(List<Rainfall> rainfalls);
	void deleteByStcd(String stcd);
	List<Rainfall> selectByStcd(String stcd);
}
