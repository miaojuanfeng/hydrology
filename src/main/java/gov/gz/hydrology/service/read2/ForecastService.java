package gov.gz.hydrology.service.read2;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read2.Forecast;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForecastService {
	List<Forecast> selectForecast(List list, String fymdh, Integer unitname, String startDay, String endDay);
}
