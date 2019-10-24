package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.Warning;

import java.util.List;
import java.util.Map;

public interface WarningService {
	List<Warning> selectWarning(String stcd);
    Integer selectNotice(String stcd);
	int insertBatch(List<Warning> warnings);
}
