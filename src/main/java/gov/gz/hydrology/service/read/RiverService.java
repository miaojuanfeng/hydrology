package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.read.Rainfall;

import java.util.List;

public interface RiverService {
	List<Rainfall> selectRainfallTime(List<String> list);
}
