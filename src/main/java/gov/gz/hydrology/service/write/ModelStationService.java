package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.ModelStation;

import java.util.List;

public interface ModelStationService {
    List selectByModel(Integer modelId);
    int deleteByModel(Integer modelId);
    int insertBatch(List<ModelStation> modelStations);
}
