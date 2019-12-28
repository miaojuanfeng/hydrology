package gov.gz.hydrology.service.read;

import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.read.Zq;

import java.math.BigDecimal;
import java.util.List;

public interface ZqService {
	Zq selectZqMin(String stcd, BigDecimal q);
	Zq selectZqMax(String stcd, BigDecimal q);
}
