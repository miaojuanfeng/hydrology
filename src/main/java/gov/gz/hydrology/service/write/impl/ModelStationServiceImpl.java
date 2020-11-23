package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.ModelStation;
import gov.gz.hydrology.entity.write.PlanStation;
import gov.gz.hydrology.mapper.write.ModelStationDao;
import gov.gz.hydrology.service.write.ModelStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelStationServiceImpl implements ModelStationService {
	
	@Autowired
	private ModelStationDao modelStationDao;


	@Override
	public List selectByModel(Integer modelId) {
		return modelStationDao.selectByModel(modelId);
	}

	@Override
	public int deleteByModel(Integer modelId) {
		return modelStationDao.deleteByModel(modelId);
	}

	@Override
	public int insertBatch(List<ModelStation> modelStations) {
		return modelStationDao.insertBatch(modelStations);
	}
}
