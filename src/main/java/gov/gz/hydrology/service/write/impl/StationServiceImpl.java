package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.gz.hydrology.mapper.write.StationDao;
import gov.gz.hydrology.service.write.StationService;

import java.util.List;

@Service
public class StationServiceImpl implements StationService{
	
	@Autowired
	private StationDao stationDao;

	public Station selectByPrimaryKey(String userStcd) {
		return stationDao.selectByPrimaryKey(userStcd);
	}

	@Override
	public List<Station> getStationByType(String type) {
		return stationDao.getStationByType(type);
	}

}
