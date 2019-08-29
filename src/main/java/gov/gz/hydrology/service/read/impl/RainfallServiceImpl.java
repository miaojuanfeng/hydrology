package gov.gz.hydrology.service.read.impl;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.mapper.read.RainfallDao;
import gov.gz.hydrology.service.read.RainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RainfallServiceImpl implements RainfallService {

    @Autowired
    private RainfallDao rainfallDao;

    @Override
    public Station selectByPrimaryKey(String stcd) {
        return rainfallDao.selectByPrimaryKey(stcd);
    }
}
