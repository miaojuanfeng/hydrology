package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.write.CacheRiverTime;
import gov.gz.hydrology.mapper.read.RiverDao;
import gov.gz.hydrology.mapper.write.CacheRiverTimeDao;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.write.CacheRiverTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheRiverTimeServiceImpl implements CacheRiverTimeService {

    @Autowired
    private CacheRiverTimeDao cacheRiverTimeDao;

    @Override
    public void insertBatch(List<CacheRiverTime> cacheRiverTimes) {
        cacheRiverTimeDao.insertBatch(cacheRiverTimes);
    }
}
