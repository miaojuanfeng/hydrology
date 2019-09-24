package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.read.River;

import java.util.List;

public interface CacheRiverTimeService {
	void insertBatch(List<River> rivers);
	void deleteByStcd(String stcd);
	List<River> selectRiverTime(String stcd);
}
