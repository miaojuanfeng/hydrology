package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.mapper.write.CacheRainfallDailyDao;
import gov.gz.hydrology.mapper.write.CacheRiverTimeDao;
import gov.gz.hydrology.service.write.CacheRainfallDailyService;
import gov.gz.hydrology.service.write.CacheRiverTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheRainfallDailyServiceImpl implements CacheRainfallDailyService {

    @Autowired
    private CacheRainfallDailyDao cacheRainfallDailyDao;

    @Override
    public void insertBatch(List<Rainfall> rainfalls) {
        cacheRainfallDailyDao.insertBatch(rainfalls);
    }

    @Override
    public void deleteByStcd(String stcd) {
        cacheRainfallDailyDao.deleteByStcd(stcd);
    }

    @Override
    public List<Rainfall> selectByStcd(String stcd) {
        return cacheRainfallDailyDao.selectByStcd(stcd);
    }
}
