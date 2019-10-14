package gov.gz.hydrology.controller;


import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.User;
import gov.gz.hydrology.entity.write.UserStation;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

	@RequestMapping("")
	public String index(HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

		UserStation userStation = userStationService.selectDefault(user.getUserId());
		map.put("stcd", userStation.getStation().getStcd());
		map.put("station", userStation.getStation());

		List<Station> stationList = userStationService.selectByUserId(user.getUserId());
		map.put("stationList", stationList);
		return "StationView";
	}

	@RequestMapping("{stcd}")
	public String stcd(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
		map.put("stcd", stcd);
		Station station = stationService.selectByPrimaryKey(stcd);
		map.put("station", station);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
		List<Station> stationList = userStationService.selectByUserId(user.getUserId());
		map.put("stationList", stationList);
		return "StationView";
	}
}
