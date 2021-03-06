package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.gz.hydrology.mapper.write.StationDao;
import gov.gz.hydrology.service.write.StationService;

import java.util.List;
import java.util.Map;

@Service
public class StationServiceImpl implements StationService{
	
	@Autowired
	private StationDao stationDao;

	public Station selectByPrimaryKey(String stcd) {
		return stationDao.selectByPrimaryKey(stcd);
	}

	@Override
	public List<Station> selectStationByType(String type) {
		return stationDao.selectStationByType(type);
	}

	@Override
	public List<Map> selectChildStationByStcd(String stcd) {
		return stationDao.selectChildStationByStcd(stcd);
	}

	@Override
	public List<Map> selectQtStation(String stcd) {
		return stationDao.selectQtStation(stcd);
	}

}
