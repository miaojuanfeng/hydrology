package gov.gz.hydrology.controller;


import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.write.UserStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.utils.DateUtil;

import java.util.List;

@Controller
@RequestMapping("cms/station")
public class StationController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private UserStationService userStationService;

	@Autowired
	private RainfallService rainfallService;
	
	@RequestMapping("{stcd}")
	public String index(ModelMap map, @PathVariable("stcd") String stcd) {
		map.put("stcd", stcd);
		Station station = stationService.selectByPrimaryKey(stcd);
		map.put("station", station);
		List<Station> stationList = userStationService.selectByUserId("16607978866");
		map.put("stationList", stationList);
		return "StationView";
	}
}
