package gov.gz.hydrology.controller;


import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.read.RainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.utils.DateUtil;

@Controller
@RequestMapping("cms/station")
public class StationController {
	
	@Autowired
	private StationService stationService;
	@Autowired
	private RainfallService rainfallService;
	
	@RequestMapping("{id}")
	public String index(ModelMap map, @PathVariable("id") Integer id) {
		map.put("date", DateUtil.getDate());
		map.put("station", id);
		return "StationView";
	}
}
