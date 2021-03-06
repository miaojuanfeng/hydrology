package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.UserStation;
import gov.gz.hydrology.mapper.write.StationDao;
import gov.gz.hydrology.mapper.write.UserStationDao;
import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.service.write.UserStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStationServiceImpl implements UserStationService {
	
	@Autowired
	private UserStationDao userStationDao;

	@Override
	public List<UserStation> selectByUserId(String userId) {
		return userStationDao.selectByUserId(userId);
	}

	@Override
	public UserStation selectDefault(String userId) {
		return userStationDao.selectDefault(userId);
	}

	@Override
	public int insertBatch(List<UserStation> userStations){
		return userStationDao.insertBatch(userStations);
	}

	@Override
	public int deleteByUserId(String userId) {
		return userStationDao.deleteByUserId(userId);
	}
}
