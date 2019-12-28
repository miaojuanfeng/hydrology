package gov.gz.hydrology.mapper.read;

import gov.gz.hydrology.entity.read.Zq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface ZqDao {
    Zq selectZqMin(@Param("stcd") String stcd, @Param("q") BigDecimal q);
    Zq selectZqMax(@Param("stcd") String stcd, @Param("q") BigDecimal q);
}
