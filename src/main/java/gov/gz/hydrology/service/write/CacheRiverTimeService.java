package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.write.CacheRiverTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface CacheRiverTimeService {
	void insertBatch(List<River> rivers);
	void deleteByStcd(String stcd);
	List<River> selectRiverTime(String stcd);
}
