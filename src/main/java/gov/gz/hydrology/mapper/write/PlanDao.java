package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanDao {
	Plan selectById(@Param("id") Integer id);
	List<Plan> selectPlan(Plan plan);
	Plan selectChildPlan(@Param("planId") Integer planId, @Param("stcd") String stcd);
	int insertSelective(Plan plan);
	int insertBatch(List<Plan> plans);
	int updateSelective(Plan plan);
	int deleteById(@Param("id") Integer id);
}
