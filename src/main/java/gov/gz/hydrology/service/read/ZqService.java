package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.read.Zq;

import java.util.List;

public interface ZqService {
	Zq selectZqMin(String stcd);
	Zq selectZqMax(String stcd);
}
