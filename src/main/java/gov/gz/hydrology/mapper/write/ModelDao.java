package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ModelDao {
	Model selectById(@Param("id") Integer id);
	int insertSelective(Model model);
	int updateSelective(Model model);
	int deleteById(@Param("id") Integer id);
}
