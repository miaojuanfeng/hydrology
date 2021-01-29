package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Model;

import java.util.List;

public interface ModelService {
    Model selectById(Integer id);
    List selectModel();
    List selectModel(String stcd);
    int insertSelective(Model model);
    int updateSelective(Model model);
    int deleteById(Integer id);
}
