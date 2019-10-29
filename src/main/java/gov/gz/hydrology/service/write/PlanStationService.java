package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.PlanStation;
import gov.gz.hydrology.entity.write.Warning;

import java.util.List;

public interface PlanStationService {
    List<PlanStation> selectByPlan(Integer planId);
    int deleteByPlan(Integer planId);
    int insertBatch(List<PlanStation> planStations);
}
