package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.entity.write.Warning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanDao {
	List<Plan> selectPlan(@Param("stcd") String stcd);
	int insertSelective(Plan plan);
	int insertBatch(List<Plan> plans);
	int updateSelective(Plan plan);
}
