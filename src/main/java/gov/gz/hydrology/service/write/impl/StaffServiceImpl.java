package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Staff;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.mapper.write.StaffDao;
import gov.gz.hydrology.mapper.write.StationDao;
import gov.gz.hydrology.service.write.StaffService;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffDao staffDao;

	public Staff selectByPrimaryKey(String pho) {
		return staffDao.selectByPrimaryKey(pho);
	}

}
