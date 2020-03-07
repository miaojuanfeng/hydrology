package gov.gz.hydrology.service.read.impl;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.mapper.read.RainfallDao;
import gov.gz.hydrology.mapper.read.RiverDao;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiverServiceImpl implements RiverService {

    @Autowired
    private RiverDao riverDao;

    @Override
    public List<River> selectRiverTime(String stcd, String startDay, String endDay) {
        return riverDao.selectRiverTime(stcd, startDay, endDay);
    }

    @Override
    public List<River> selectRiverQRange(String stcd, String forecastTime, String affectTime) {
        return riverDao.selectRiverQRange(stcd, forecastTime, affectTime);
    }

    @Override
    public List<River> selectRiverZRange(String stcd, String forecastTime, String affectTime) {
        return riverDao.selectRiverZRange(stcd, forecastTime, affectTime);
    }

    @Override
    public River selectRiverLast(String stcd) {
        return riverDao.selectRiverLast(stcd);
    }
}
