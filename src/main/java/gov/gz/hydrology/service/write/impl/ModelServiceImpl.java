package gov.gz.hydrology.service.write.impl;

import gov.gz.hydrology.entity.write.Model;
import gov.gz.hydrology.mapper.write.ModelDao;
import gov.gz.hydrology.service.write.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
	
	@Autowired
	private ModelDao modelDao;


	@Override
	public Model selectById(Integer id) {
		return modelDao.selectById(id);
	}

	@Override
	public List selectModel() {
		return modelDao.selectModel();
	}

	@Override
	public List selectModel(String stcd) {
		return modelDao.selectModel(stcd);
	}

	@Override
	public int insertSelective(Model model) {
		return modelDao.insertSelective(model);
	}

	@Override
	public int updateSelective(Model model) {
		return modelDao.updateSelective(model);
	}

	@Override
	public int deleteById(Integer id) {
		return modelDao.deleteById(id);
	}
}
