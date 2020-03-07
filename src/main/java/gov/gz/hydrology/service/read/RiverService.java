package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RiverService {
	List<River> selectRiverTime(String stcd, String statDay, String endDay);
	List<River> selectRiverQRange(String stcd, String forecastTime, String affectTime);
	List<River> selectRiverZRange(String stcd, String forecastTime, String affectTime);
	River selectRiverLast(String stcd);
}
