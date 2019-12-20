package gov.gz.hydrology.service.read.impl;

import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.read.Zq;
import gov.gz.hydrology.mapper.read.RiverDao;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.read.ZqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZqServiceImpl implements ZqService {

    @Autowired
    private ZqService zqService;

    @Override
    public Zq selectZqMin(String stcd) {
        return zqService.selectZqMin(stcd);
    }

    @Override
    public Zq selectZqMax(String stcd) {
        return zqService.selectZqMax(stcd);
    }
}
