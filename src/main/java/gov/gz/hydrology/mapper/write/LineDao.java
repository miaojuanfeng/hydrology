package gov.gz.hydrology.mapper.write;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LineDao {
    List selectList(@Param("stcd") String stcd);
}
