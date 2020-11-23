package gov.gz.hydrology.controller;


import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.write.*;
import gov.gz.hydrology.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("station")
public class StationController {

	public static final String BASE_PATH = "/XAJ";
	
	@Autowired
	private StationService stationService;

	@Autowired
	private UserStationService userStationService;

	@Autowired
	private RiverService riverService;

	@Autowired
	private RainfallService rainfallService;

	@Autowired
	private CacheRiverTimeService cacheRiverTimeService;

	@Autowired
	private CacheRainfallDailyService cacheRainfallDailyService;

	@Autowired
	private CacheRainfallTotalService cacheRainfallTotalService;

	@GetMapping("{stcd}")
	public String station(ModelMap map, @PathVariable("stcd") String stcd) {
//		map.put("basePath", BASE_PATH);
//		map.put("stcd", stcd);
        map.put("chart1", chart1(stcd));
        map.put("chart2", chart2(stcd));
        map.put("chart3", chart3(stcd));
		map.put("chart4", chart4(stcd));
		return "views/home/station";
	}

    private JSONObject chart1(String stcd) {
        JSONObject retval = new JSONObject();

        retval.put("hour", new SimpleDateFormat("H时").format(new Date()));

        List<Rainfall> rainfallTotal = cacheRainfallTotalService.selectByStcd(stcd);

        List<String> stationArr = new ArrayList<>();
        List<BigDecimal> rainfallArr = new ArrayList<>();
        Integer max = Integer.MIN_VALUE;
        for (int i=0;i<rainfallTotal.size();i++){
            stationArr.add(rainfallTotal.get(i).getStname());
            rainfallArr.add(rainfallTotal.get(i).getRainfall());
            if( max < rainfallTotal.get(i).getRainfall().intValue() ){
                max = rainfallTotal.get(i).getRainfall().intValue() + 10;
            }
        }
        retval.put("stationArr", stationArr);
        retval.put("rainfallArr", rainfallArr);
        retval.put("max", max);

        return retval;
    }

	private JSONObject chart2(String stcd) {
		JSONObject retval = new JSONObject();

		Station station = stationService.selectByPrimaryKey(stcd);
		retval.put("station", station);

		List<River> riverTime = cacheRiverTimeService.selectRiverTime(stcd);

		List<String> timeArr = new ArrayList<>();
		List<BigDecimal> riverArr = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
		BigDecimal max = NumberConst.ZERO;
		BigDecimal min = NumberConst.ZERO;
		for (int i = 0; i < riverTime.size(); i++) {
			String time = format.format(riverTime.get(i).getTm());
			timeArr.add(time);
			BigDecimal z = riverTime.get(i).getZ();
			riverArr.add(z);
			if (NumberUtil.lt(max, z)) {
				max = z;
			}
			if (NumberUtil.gt(min, z) || NumberUtil.et(min, NumberConst.ZERO)) {
				min = z;
			}
		}

		BigDecimal jbLine = new BigDecimal(station.getJbLine());
		BigDecimal jjLine = new BigDecimal(station.getJjLine());
		if (NumberUtil.lt(max, jjLine)) {
			max = jjLine;
		}
		retval.put("timeArr", timeArr);
		retval.put("riverArr", riverArr);
		retval.put("max", max.add(NumberConst.ONE).intValue());
		retval.put("min", min.intValue() - 1);
		retval.put("jbLine", jbLine);
		retval.put("jjLine", jjLine);

		return retval;
	}

	private JSONObject chart3(String stcd) {
		JSONObject retval = new JSONObject();

		Station station = stationService.selectByPrimaryKey(stcd);
		retval.put("station", station);

		List<Rainfall> rainfallTotal = cacheRainfallTotalService.selectByStcd(stcd);
		BigDecimal rainfallSum = new BigDecimal(0);
		for (int i=0;i<rainfallTotal.size();i++){
			rainfallSum.add(rainfallTotal.get(i).getRainfall());
		}
		River river = riverService.selectRiverLast(stcd);
		BigDecimal z = river.getZ();
		Integer ava = 0;
		if( rainfallTotal.size() > 0 ) {
			BigDecimal rainfallAva = rainfallSum.divide(new BigDecimal(rainfallTotal.size()), NumberConst.DIGIT, NumberConst.MODE);
			Integer floodDiff = new BigDecimal(station.getJjLine()).subtract(z).subtract(rainfallAva.divide(new BigDecimal(30), NumberConst.DIGIT, NumberConst.MODE)).intValue();
			if( floodDiff < 1 ){
				ava = 80;
			} else if (floodDiff < 2) {
				ava = 60;
			} else {
				ava = 10;
			}
		}
		retval.put("ava", ava);

		return retval;
	}

	private JSONObject chart4(String stcd){
		JSONObject retval = new JSONObject();

		Station station = stationService.selectByPrimaryKey(stcd);
		retval.put("station", station);

		List<Rainfall> rainfallDaily = cacheRainfallDailyService.selectByStcd(stcd);

		List<String> dateArr = new ArrayList<>();
		List<BigDecimal> rainfallArr = new ArrayList<>();
		for (int i=0;i<rainfallDaily.size();i++){
			dateArr.add(rainfallDaily.get(i).getDate().substring(0,5));
			rainfallArr.add(rainfallDaily.get(i).getRainfall());
		}
		retval.put("dateArr", dateArr);
		retval.put("rainfallArr", rainfallArr);

		return retval;
	}

//	@RequestMapping("")
//	public String index(HttpServletRequest request, ModelMap map) {
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		UserStation userStation = userStationService.selectDefault(user.getUserId());
//		map.put("stcd", userStation.getStation().getStcd());
//		map.put("station", userStation.getStation());
//
//		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//		map.put("stationList", stationList);
//		return "StationView";
//	}
//
//	@RequestMapping("{stcd}")
//	public String stcd(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
//		map.put("stcd", stcd);
//		Station station = stationService.selectByPrimaryKey(stcd);
//		map.put("station", station);
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//		map.put("stationList", stationList);
//		return "StationView";
//	}
}
