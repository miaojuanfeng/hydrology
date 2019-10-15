package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.Warning;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarningService {
	List<Station> selectWarning(@Param("stcd") String stcd);
	int insertBatch(List<Warning> warnings);
}
