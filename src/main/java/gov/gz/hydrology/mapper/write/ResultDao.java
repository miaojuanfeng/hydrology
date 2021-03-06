package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Warning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResultDao {
	List<Warning> selectWarning(@Param("stcd") String stcd);
	Integer selectNotice(@Param("stcd") String stcd);
	int insertBatch(List<Warning> warnings);
}
