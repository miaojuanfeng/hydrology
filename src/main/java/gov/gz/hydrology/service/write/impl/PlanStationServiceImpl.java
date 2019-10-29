package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.PlanStation;
import gov.gz.hydrology.entity.write.Warning;
import gov.gz.hydrology.mapper.write.PlanStationDao;
import gov.gz.hydrology.mapper.write.WarningDao;
import gov.gz.hydrology.service.write.PlanStationService;
import gov.gz.hydrology.service.write.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanStationServiceImpl implements PlanStationService {
	
	@Autowired
	private PlanStationDao planStationDao;


	@Override
	public List<PlanStation> selectByPlan(Integer planId) {
		return planStationDao.selectByPlan(planId);
	}

	@Override
	public int deleteByPlan(Integer planId) {
		return planStationDao.deleteByPlan(planId);
	}

	@Override
	public int insertBatch(List<PlanStation> planStations) {
		return planStationDao.insertBatch(planStations);
	}
}
