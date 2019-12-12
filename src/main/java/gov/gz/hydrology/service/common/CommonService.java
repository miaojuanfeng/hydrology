package gov.gz.hydrology.service.common;

public interface CommonService {

    String userLevel(Integer level);

    String levelProgress(Integer level);

    String stationProgress(String stcd, Integer step);
}
