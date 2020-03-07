package gov.gz.hydrology.service.read2.impl;

import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read2.Forecast;
import gov.gz.hydrology.mapper.read.RainfallDao;
import gov.gz.hydrology.mapper.read2.ForecastDao;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read2.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

    @Autowired
    private ForecastDao forecastDao;

    @Override
    public List<Forecast> selectForecast(List list, String fymdh, Integer unitname, String startDay, String endDay) {
        return forecastDao.selectForecast(list, fymdh, unitname, startDay, endDay);
    }
}
