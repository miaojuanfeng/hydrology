package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Station;
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

	public List<Station> selectByUserId(String userId) {
		return userStationDao.selectByUserId(userId);
	}

}
