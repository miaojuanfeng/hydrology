package gov.gz.hydrology.service.read.impl;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.mapper.read.RainfallDao;
import gov.gz.hydrology.service.read.RainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RainfallServiceImpl implements RainfallService {

    @Autowired
    private RainfallDao rainfallDao;

    @Override
    public List<Rainfall> selectRainfallTotal(List<String> list) {
        return rainfallDao.selectRainfallTotal(list);
    }

    @Override
    public List<Rainfall> selectRainfallDaily(List<String> list) {
        return rainfallDao.selectRainfallDaily(list);
    }

    @Override
    public List<Rainfall> selectRainfallRange(List<String> list, String forecastTime, String affectTime) {
        return rainfallDao.selectRainfallRange(list, forecastTime, affectTime);
    }
}
