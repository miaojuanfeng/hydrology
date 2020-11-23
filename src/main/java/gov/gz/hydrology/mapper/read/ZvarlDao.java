package gov.gz.hydrology.mapper.read;

import gov.gz.hydrology.entity.read.Zvarl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZvarlDao {
    Zvarl selectLastTime(@Param("stcd") String stcd, @Param("date") String date);
    List<Zvarl> selectList(@Param("stcd") String stcd, @Param("date") String date);
}
