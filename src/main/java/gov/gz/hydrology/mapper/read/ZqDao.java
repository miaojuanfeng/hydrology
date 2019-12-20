package gov.gz.hydrology.mapper.read;

import gov.gz.hydrology.entity.read.Zq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ZqDao {
    Zq selectZqMin(@Param("stcd") String stcd);
    Zq selectZqMax(@Param("stcd") String stcd);
}
