package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.mapper.write.CacheRainfallDailyDao;
import gov.gz.hydrology.mapper.write.CacheRainfallTotalDao;
import gov.gz.hydrology.service.write.CacheRainfallDailyService;
import gov.gz.hydrology.service.write.CacheRainfallTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheRainfallTotalServiceImpl implements CacheRainfallTotalService {

    @Autowired
    private CacheRainfallTotalDao cacheRainfallTotalDao;

    @Override
    public void insertBatch(List<Rainfall> rainfalls) {
        cacheRainfallTotalDao.insertBatch(rainfalls);
    }

    @Override
    public void deleteByStcd(String stcd) {
        cacheRainfallTotalDao.deleteByStcd(stcd);
    }

    @Override
    public List<Rainfall> selectByStcd(String stcd) {
        return cacheRainfallTotalDao.selectByStcd(stcd);
    }
}
