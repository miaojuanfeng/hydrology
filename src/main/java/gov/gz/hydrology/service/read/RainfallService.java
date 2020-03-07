package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.write.Station;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RainfallService {
	List<Rainfall> selectRainfallTotal(List<String> list, String startDay, String endDay);
	List<Rainfall> selectRainfallDaily(List<String> list, String startDay, String endDay);
	List<Rainfall> selectRainfallRange(List<String> list, String forecastTime, String affectTime);
}
