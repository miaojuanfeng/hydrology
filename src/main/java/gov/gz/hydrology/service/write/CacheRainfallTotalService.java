package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.read.Rainfall;

import java.util.List;

public interface CacheRainfallTotalService {
	void insertBatch(List<Rainfall> rainfalls);
	void deleteByStcd(String stcd);
	List<Rainfall> selectByStcd(String stcd);
}
