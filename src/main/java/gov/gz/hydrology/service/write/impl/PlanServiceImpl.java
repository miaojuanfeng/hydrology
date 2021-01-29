package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.entity.write.Warning;
import gov.gz.hydrology.mapper.write.PlanDao;
import gov.gz.hydrology.mapper.write.WarningDao;
import gov.gz.hydrology.service.write.PlanService;
import gov.gz.hydrology.service.write.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanDao planDao;

	@Override
	public Plan selectById(Integer id) {
		return planDao.selectById(id);
	}

	@Override
	public List<Plan> selectPlan(Plan plan) {
		return planDao.selectPlan(plan);
	}

	@Override
	public Plan selectChildPlan(Integer planId, String stcd) {
		return planDao.selectChildPlan(planId, stcd);
	}

	@Override
	public int insertSelective(Plan plan) {
		return planDao.insertSelective(plan);
	}

	@Override
	public int insertBatch(List<Plan> plans) {
		return planDao.insertBatch(plans);
	}

	@Override
	public int updateSelective(Plan plan) {
		return planDao.updateSelective(plan);
	}

	@Override
	public int deleteById(Integer id) {
		return planDao.deleteById(id);
	}
}
