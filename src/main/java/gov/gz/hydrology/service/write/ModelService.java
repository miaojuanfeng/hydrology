package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Model;

public interface ModelService {
    Model selectById(Integer id);
    int insertSelective(Model model);
    int updateSelective(Model model);
    int deleteById(Integer id);
}
