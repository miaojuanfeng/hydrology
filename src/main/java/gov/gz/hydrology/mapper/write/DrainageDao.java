package gov.gz.hydrology.mapper.write;

import gov.gz.hydrology.entity.write.Drainage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DrainageDao {
    List<Drainage> selectList(@Param("stcd") String stcd);
}
