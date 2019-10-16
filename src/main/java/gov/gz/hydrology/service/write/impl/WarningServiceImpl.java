package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.Warning;
import gov.gz.hydrology.mapper.write.StationDao;
import gov.gz.hydrology.mapper.write.WarningDao;
import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.service.write.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WarningServiceImpl implements WarningService {
	
	@Autowired
	private WarningDao warningDao;


	@Override
	public List<Warning> selectWarning(String stcd) {
		return warningDao.selectWarning(stcd);
	}

	@Override
	public int insertBatch(List<Warning> warnings) {
		return warningDao.insertBatch(warnings);
	}
}
