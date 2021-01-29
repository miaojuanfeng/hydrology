package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Plan;

import java.util.List;

public interface PlanService {
    Plan selectById(Integer id);
    List<Plan> selectPlan(Plan plan);
    Plan selectChildPlan(Integer planId, String stcd);
    int insertSelective(Plan plan);
    int insertBatch(List<Plan> plans);
    int updateSelective(Plan plan);
    int deleteById(Integer id);
}
