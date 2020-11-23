package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.ModelStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModelStationDao {
	List selectByModel(@Param("modelId") Integer modelId);
	int deleteByModel(@Param("modelId") Integer modelId);
	int insertBatch(List<ModelStation> modelStations);
}
