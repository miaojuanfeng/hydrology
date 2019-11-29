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
    public List<River> selectRiverTime(String stcd) {
        return riverDao.selectRiverTime(stcd);
    }

    @Override
    public List<River> selectRiverRange(String stcd, String forecastTime, String affectTime) {
        return riverDao.selectRiverRange(stcd, forecastTime, affectTime);
    }
}
