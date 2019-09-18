package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.write.CacheRiverTime;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface CacheRiverTimeService {
	void insertBatch(List<CacheRiverTime> cacheRiverTimes);
}
