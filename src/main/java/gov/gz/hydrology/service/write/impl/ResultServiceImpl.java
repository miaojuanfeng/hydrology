package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Warning;
import gov.gz.hydrology.mapper.write.ResultDao;
import gov.gz.hydrology.mapper.write.WarningDao;
import gov.gz.hydrology.service.write.ResultService;
import gov.gz.hydrology.service.write.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private ResultDao resultDao;



}
