package gov.gz.hydrology.service.write;

import gov.gz.hydrology.entity.write.Staff;
import gov.gz.hydrology.entity.write.Station;

import java.util.List;

public interface StaffService {
	Staff selectByPrimaryKey(String pho);
}
