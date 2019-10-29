package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.PlanStation;
import gov.gz.hydrology.entity.write.Warning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanStationDao {
	List<PlanStation> selectByPlan(@Param("planId") Integer planId);
	int deleteByPlan(@Param("planId") Integer planId);
	int insertBatch(List<PlanStation> planStations);
}
