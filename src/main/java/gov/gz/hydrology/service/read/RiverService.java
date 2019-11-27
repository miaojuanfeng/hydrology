package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;

import java.util.List;

public interface RiverService {
	List<River> selectRiverTime(String stcd);
	List<River> selectRiverRange(String stcd);
}
