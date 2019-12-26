package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.entity.write.Warning;

import java.util.List;

public interface PlanService {
    Plan selectById(Integer id);
    List<Plan> selectPlan(String stcd);
    Plan selectChildPlan(Integer planId, String stcd);
    int insertSelective(Plan plan);
    int insertBatch(List<Plan> plans);
    int updateSelective(Plan plan);
    int deleteById(Integer id);
}
