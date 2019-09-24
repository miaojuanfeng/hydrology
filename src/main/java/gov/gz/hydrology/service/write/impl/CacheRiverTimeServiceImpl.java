package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.mapper.write.CacheRiverTimeDao;
import gov.gz.hydrology.service.write.CacheRiverTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheRiverTimeServiceImpl implements CacheRiverTimeService {

    @Autowired
    private CacheRiverTimeDao cacheRiverTimeDao;

    @Override
    public void insertBatch(List<River> rivers) {
        cacheRiverTimeDao.insertBatch(rivers);
    }

    @Override
    public void deleteByStcd(String stcd) {
        cacheRiverTimeDao.deleteByStcd(stcd);
    }

    @Override
    public List<River> selectRiverTime(String stcd) {
        return cacheRiverTimeDao.selectRiverTime(stcd);
    }
}
