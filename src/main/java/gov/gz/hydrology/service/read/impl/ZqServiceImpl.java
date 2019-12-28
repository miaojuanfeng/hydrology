package gov.gz.hydrology.service.read.impl;

import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.read.Zq;
import gov.gz.hydrology.mapper.read.RiverDao;
import gov.gz.hydrology.mapper.read.ZqDao;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.read.ZqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ZqServiceImpl implements ZqService {

    @Autowired
    private ZqDao zqDao;

    @Override
    public Zq selectZqMin(String stcd, BigDecimal q) {
        return zqDao.selectZqMin(stcd, q);
    }

    @Override
    public Zq selectZqMax(String stcd, BigDecimal q) {
        return zqDao.selectZqMax(stcd, q);
    }
}
