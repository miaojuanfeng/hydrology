package gov.gz.hydrology.service.read;

import java.util.List;

public interface ZvarlService {
	void selectList(String stcd, String date, List Z_CUR, List V_CUR);
}
