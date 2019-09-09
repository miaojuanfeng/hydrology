package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.write.Station;

import java.util.List;

public interface RainfallService {
	List<Rainfall> selectRainfallTotal(List<String> list);

}
