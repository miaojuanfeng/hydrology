package gov.gz.hydrology.controller;

import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.gz.hydrology.utils.DateUtil;

import java.util.List;

@Controller
@RequestMapping("cms/calc")
public class CalcController {

	@Autowired
	private StationService stationService;

	@RequestMapping("index")
	public String index(ModelMap map) {
		map.put("date", DateUtil.getDate());
		List<Station> stationList = stationService.getStationByType("基本站");
		map.put("stationList", stationList);
		return "CalcView";
	}


}
