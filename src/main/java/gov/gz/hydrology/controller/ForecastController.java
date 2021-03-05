package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.constant.ModelTypeEnum;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read2.Forecast;
import gov.gz.hydrology.entity.write.*;
import gov.gz.hydrology.service.common.CommonService;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.read2.ForecastService;
import gov.gz.hydrology.service.write.*;
import gov.gz.hydrology.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("forecast")
public class ForecastController {

	@Autowired
	private StationService stationService;

	@Autowired
	private UserStationService userStationService;

	@Autowired
    private PlanService planService;

	@Autowired
    private PlanStationService planStationService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private RainfallService rainfallService;

	@Autowired
	private ForecastService forecastService;

	@Autowired
	private GridService gridService;

	@Autowired
	private RiverService riverService;

	@GetMapping("calc")
	public String getCalc(ModelMap map) {
//		map.put("date", DateUtil.getDate());
//		List<Station> stationList = stationService.selectStationByType("基本站");
//		map.put("stationList", stationList);
//		List<Plan> planList = planService.selectPlan(CommonConst.STCD_STATION[0]);
//		map.put("planList", planList);
//		if( planList.size() > 0 ){
//			Plan plan = planService.selectById(planList.get(0).getId());
//			map.put("plan", plan);
//		}
//		map.put("stationProgress", commonService.stationProgress(CommonConst.STCD_NINGDU, 1));
//		Date date = new Date();
////		map.put("forecastTime", DateUtil.date2str(date, "yyyy-MM-dd HH:00:00"));
////		map.put("affectTime", DateUtil.date2str(DateUtil.addMonth(date, -1), "yyyy-MM-dd HH:00:00"));
//		map.put("forecastTime", "2020-04-04 08:00:00");
//		map.put("affectTime", "2020-04-01 08:00:00");

		return "CalcView";
	}

//	@RequestMapping("result")
//	public String result(HttpServletRequest request, ModelMap map) {
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		UserStation userStation = userStationService.selectDefault(user.getUserId());
//		map.put("stcd", userStation.getStation().getStcd());
//		map.put("station", userStation.getStation());
//
//		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//		map.put("stationList", stationList);
//		return "ResultView";
//	}

//	@RequestMapping("result/{stcd}")
//	public String stcdResult(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
//		map.put("stcd", stcd);
//		Station station = stationService.selectByPrimaryKey(stcd);
//		map.put("station", station);
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//		map.put("stationList", stationList);
//		return "ResultView";
//	}

	@RequestMapping(value="result/data",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String dataResult() {
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		JSONObject t = new JSONObject();
		int count = 100;
		for(int i=1;i<=count;i++) {
			t.put("id", i);
			t.put("name", "宁都站");
			t.put("peakTime", "2019-08-20 20:12:22");
			t.put("peakFlow", "128立方米/秒");
			t.put("username", "陈济天");
			t.put("time", "2019-08-19 20:12:22");
			temp.add(t);
		}
		retval.put("code", 0);
		retval.put("count", count);
		retval.put("data", temp);
		return retval.toString();
	}

	@RequestMapping("plan")
	public String plan(HttpServletRequest request, ModelMap map) {
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		UserStation userStation = userStationService.selectDefault(user.getUserId());
//		map.put("stcd", userStation.getStation().getStcd());
//		map.put("station", userStation.getStation());
//
//		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//		map.put("stationList", stationList);
		return "PlanView";
	}

//	@GetMapping("plan/{stcd}")
//	public String getPlan(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
//		map.put("stcd", stcd);
//		Station station = stationService.selectByPrimaryKey(stcd);
//		map.put("station", station);
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//		map.put("stationList", stationList);
//		return "PlanView";
//	}

//	@PostMapping(value="plan/{stcd}",produces="text/plain;charset=UTF-8")
//	@ResponseBody
//	public String postPlan(@PathVariable("stcd") String stcd) {
//		JSONObject retval = new JSONObject();
//		JSONArray temp = new JSONArray();
//		List<Plan> plans = planService.selectPlan(stcd);
//        int count = plans.size();
//        int number = 0;
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for(Plan plan : plans) {
//			JSONObject t = new JSONObject();
//			t.put("number", ++number);
//            t.put("id", plan.getId());
//			t.put("stname", plan.getStname());
//			t.put("name", plan.getName());
//			t.put("planModel", "新安江模型");
//			t.put("username", plan.getUserName());
//			t.put("time", format.format(plan.getCreateTime()));
//			temp.add(t);
//		}
//		retval.put("code", 0);
//		retval.put("count", count);
//		retval.put("data", temp);
//		return retval.toString();
//	}

	@GetMapping("plan/insert/{stcd}")
	public String getInsertPlan(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
//	    HttpSession session = request.getSession();
//		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//		Station station = stationService.selectByPrimaryKey(stcd);
//		map.put("station", station);
//
//		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//		map.put("stationList", stationList);
//
//        List<Map> qtStationList = stationService.selectQtStation(stcd);
//        for(Map qtStation : qtStationList){
//            String qtStcd = String.valueOf(qtStation.get("PO_STCD"));
//            List<Plan> qtPlan = planService.selectPlan(qtStcd);
//            qtStation.put("plan", qtPlan);
//        }
//        map.put("qtStationList", qtStationList);

//		List<Map> childStationList = stationService.selectChildStationByStcd(stcd);
//        map.put("childStationList", childStationList);

		return "PlanInsertView";
	}

    @PostMapping("plan/insert")
    @ResponseBody
    public String postInsertPlan(HttpServletRequest request, ModelMap map, Plan plan, String[] qtStcd, Integer[] childPlanId, BigDecimal[] ke, BigDecimal[] xe) {
        JSONObject retval = new JSONObject();

	    HttpSession session = request.getSession();
        User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

        plan.setCreateUser(user.getUserId());
        plan.setCreateTime(new Date());
        planService.insertSelective(plan);

        List<PlanStation> planStationList = new ArrayList<>();
        if( qtStcd != null ) {
            for (int i = 0; i < qtStcd.length; i++) {
                PlanStation planStation = new PlanStation();
                planStation.setPoStcd(qtStcd[i]);
                planStation.setChildPlanId(childPlanId[i]);
                planStation.setKe(ke[i]);
                planStation.setXe(xe[i]);
                planStation.setPlanId(plan.getId());
                planStationList.add(planStation);
            }
        }
        if( planStationList.size() > 0 ){
            planStationService.insertBatch(planStationList);
        }

        return retval.toString();
    }

//    @GetMapping("plan/update/{id}")
//    public String getInsertPlan(HttpServletRequest request, ModelMap map, @PathVariable("id") Integer id) {
//        HttpSession session = request.getSession();
//        User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
//
//        List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
//        map.put("stationList", stationList);
//
//        Plan plan = planService.selectById(id);
//        map.put("plan", plan);
//
//        Station station = stationService.selectByPrimaryKey(plan.getStcd());
//        map.put("station", station);
//
//        List<PlanStation> planStationList = planStationService.selectByPlan(plan.getId());
//
////		List<Map> qtStationList = stationService.selectQtStation(plan.getStcd());
////        for(Map qtStation : qtStationList){
////            for(PlanStation planStation : planStationList){
////                if( String.valueOf(qtStation.get("PO_STCD")).equals(planStation.getPoStcd()) ){
////					qtStation.put("ke", planStation.getKe());
////					qtStation.put("xe", planStation.getXe());
////
////					String qtStcd = String.valueOf(qtStation.get("PO_STCD"));
////					List<Plan> qtPlan = planService.selectPlan(qtStcd);
////					qtStation.put("plan", qtPlan);
////
////                    break;
////                }
////            }
////        }
////        map.put("qtStationList", qtStationList);
//
//		List<Map> qtStationList = new ArrayList<>();
//		for(PlanStation planStation : planStationList){
//			Map qtStation = new HashMap();
//			qtStation.put("PO_STCD", planStation.getPoStcd());
//			qtStation.put("childPlanId", planStation.getChildPlanId());
//			qtStation.put("ke", planStation.getKe());
//			qtStation.put("xe", planStation.getXe());
//			//
//			Station s = stationService.selectByPrimaryKey(planStation.getPoStcd());
//			qtStation.put("stname", s.getStname());
//			//
//			List<Plan> planList = planService.selectPlan(planStation.getPoStcd());
//			qtStation.put("plan", planList);
//			for( Plan p : planList ){
//				if( p.getId().equals(planStation.getChildPlanId()) ){
//					qtStation.put("planName", p.getName());
//					break;
//				}
//			}
//			//
//			qtStationList.add(qtStation);
//		}
//		map.put("qtStationList", qtStationList);
//
//		return "PlanInsertView";
//    }

    @PostMapping("plan/update")
    @ResponseBody
    public String postUpdatePlan(HttpServletRequest request, ModelMap map, Plan plan, String[] qtStcd, Integer[] childPlanId, BigDecimal[] ke, BigDecimal[] xe, BigDecimal[] dt) {
        JSONObject retval = new JSONObject();

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

        planService.updateSelective(plan);

        List<PlanStation> planStationList = new ArrayList<>();
        if( qtStcd != null ) {
            for (int i = 0; i < qtStcd.length; i++) {
                PlanStation planStation = new PlanStation();
                planStation.setPoStcd(qtStcd[i]);
                planStation.setChildPlanId(childPlanId[i]);
                planStation.setKe(ke[i]);
                planStation.setXe(xe[i]);
                planStation.setPlanId(plan.getId());
                planStationList.add(planStation);
            }
        }
        planStationService.deleteByPlan(plan.getId());
        if( planStationList.size() > 0 ){
            planStationService.insertBatch(planStationList);
        }

        return retval.toString();
    }

    @PostMapping("plan/delete")
    @ResponseBody
    public String postDeletePlan(HttpServletRequest request, ModelMap map, @RequestParam("id") Integer id) {
        JSONObject retval = new JSONObject();

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

        planStationService.deleteByPlan(id);
        planService.deleteById(id);

        return retval.toString();
    }

	/**
	 * 新版在此
	 */
	@GetMapping("home")
	public String home(ModelMap map) {
		map.put("date", DateUtil.getDate());
		List<Station> stations = stationService.selectStationByType("基本站");
		map.put("stations", stations);
//		List<Plan> planList = planService.selectPlan(CommonConst.STCD_STATION[0]);
//		map.put("planList", planList);
//		if( planList.size() > 0 ){
//			Plan plan = planService.selectById(planList.get(0).getId());
//			map.put("plan", plan);
//		}
//		map.put("stationProgress", commonService.stationProgress(CommonConst.STCD_NINGDU, 1));
		Date date = new Date();
//		map.put("forecastTime", DateUtil.date2str(date, "yyyy-MM-dd HH:00:00"));
//		map.put("affectTime", DateUtil.date2str(DateUtil.addMonth(date, -1), "yyyy-MM-dd HH:00:00"));
		map.put("forecastTime", "2019-06-04 08:00:00");
		map.put("affectTime", "2019-06-01 08:00:00");

		return "views/home/forecast";
	}

	private JSONObject getRetval(String stcd){
        JSONObject retval = new JSONObject();
        retval.put("timeArr", CommonUtil.forecast.getListTime(stcd));
        retval.put("P", CommonUtil.forecast.getListP(stcd));
        retval.put("R", CommonUtil.forecast.getListR(stcd));
        retval.put("QTRR", CommonUtil.forecast.getListQTRR(stcd));
        retval.put("resultArr", CommonUtil.forecast.getListResult(stcd));
        return retval;
    }

    /**
     * 单站结果
     * @return
     */
    @PostMapping("station")
    @ResponseBody
    public String station(
            @RequestParam("stcd") String stcd
    ) {
        return getRetval(stcd).toString();
    }

	/**
	 * 预报结果
	 * @return
	 */
	@PostMapping("compute")
	@ResponseBody
	public String compute(
			@RequestParam("type") Integer type,
			@RequestParam("stcd") String stcd,
			@RequestParam("forecastTime") String forecastTime,
			@RequestParam("affectTime") String affectTime,
			@RequestParam("day") Integer day,
			@RequestParam("unit") Integer unit,
			@RequestParam("data") String data
	) {
		JSONArray model = JSONArray.parseArray(data);
		List<BigDecimal> result = modelStation(forecastTime, affectTime, day, unit, model);
		return getRetval(stcd).toString();


//			/**
//			 * 获取子站id
//			 */
//			List<String> stcdIds = new ArrayList<>();
//			List<Map> stationList = stationService.selectChildStationByStcd(stcd);
//			for (int i = 0; i < stationList.size(); i++) {
//				stcdIds.add(String.valueOf(stationList.get(i).get("stcd")));
//			}
//			/**
//			 * 实测雨量
//			 */
//			Map<String, BigDecimal> forecastMap = new TreeMap<>();
//			String startDay = forecastTime;
//			String endDay = DateUtil.date2str(DateUtil.addDay(DateUtil.str2date(forecastTime, CommonConst.DATETIME_FORMAT), day), CommonConst.DATETIME_FORMAT);
//			if( CommonConst.FUTURE_RAINFALL_MEASURE.equals(unit) ) {
//				List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdIds, endDay, startDay);
//				for (Rainfall rainfall : rainfalls) {
//					forecastMap.put(rainfall.getDate(), rainfall.getRainfall());
//				}
//
//				Date sDay = DateUtil.str2date(startDay, CommonConst.DATETIME_FORMAT);
//				Date eDay = DateUtil.str2date(endDay, CommonConst.DATETIME_FORMAT);
//				Date iDay = eDay;
//				while (!iDay.before(sDay)) {
//					String key = DateUtil.date2str(iDay, CommonConst.DATETIME_FORMAT);
//					if( !forecastMap.containsKey(key) ){
//						forecastMap.put(key, NumberConst.ZERO);
//					}
//					iDay = DateUtil.addHour(iDay, -1);
//				}
//			/**
//			 * 欧洲台或日本台
//			 */
//			}else{
//				List<Grid> gridList = gridService.selectByStcd(stcd);
//				List<String> gridId = new ArrayList<>();
//				for (Grid grid : gridList){
//					gridId.add(grid.getGridId());
//				}
//
//				String fymdh = DateUtil.date2str(DateUtil.addDay(new Date(), -1)) + " 20:00:00";
//				List<Forecast> forecastList = forecastService.selectForecast(gridId, fymdh, unit, startDay, endDay);
//
//				if( forecastList.size() > 0 ) {
//					for (Forecast forecast : forecastList) {
//						forecastMap.put(DateUtil.date2str(forecast.getYmdh(), CommonConst.DATETIME_FORMAT), forecast.getRn());
//					}
//
//					Date sDay = DateUtil.str2date(startDay, CommonConst.DATETIME_FORMAT);
//					Date eDay = DateUtil.str2date(endDay, CommonConst.DATETIME_FORMAT);
//					Date iDay = eDay;
//					BigDecimal n = forecastList.get(forecastList.size()-1).getRn();
//	//                    Date d = forecastList.get(forecastList.size()-1).getYmdh();
//					Integer c = 0;
//					while (!iDay.before(sDay)) {
//						String key = DateUtil.date2str(iDay, CommonConst.DATETIME_FORMAT);
//						if( forecastMap.containsKey(key) ){
//							n = forecastMap.get(key);
//							c = 0;
//						}else{
//							c++;
//							if( c >= 3 ){
//								n = NumberConst.ZERO;
//							}
//						}
//						if( iDay.after(forecastList.get(forecastList.size()-1).getYmdh()) ){
//							n = NumberConst.ZERO;
//						}
//						forecastMap.put(key, n);
//						iDay = DateUtil.addHour(iDay, -1);
//					}
//	//                    Integer a = 1;
//				}
//			}
//			//            List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, plusDay(day, forecastTime), affectTime);
//			List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdIds, forecastTime, affectTime);
//
//			// 拼接数据
//			for(String key : forecastMap.keySet()){
//				Rainfall r = new Rainfall();
//				r.setDate(key);
//				r.setRainfall(forecastMap.get(key));
//				rainfalls.add(r);
//			}
//
//			List<BigDecimal> listRainfall = new ArrayList<>();
//			List<String> listTime = new ArrayList<>();
//			BigDecimal rainfallMax = NumberConst.ZERO;
//	//            System.out.println("start");
//			for (Rainfall rainfall : rainfalls) {
//				BigDecimal r = rainfall.getRainfall().setScale(2, NumberConst.MODE);
//				String t = rainfall.getDate().substring(0, 16);
//				listRainfall.add(r);
//				listTime.add(t);
//				if( NumberUtil.gt(r, rainfallMax) ){
//					rainfallMax = r;
//				}
//	//                System.out.println(r);
//			}
//	//            System.out.println("end");
//			retval.put("rainfallMax", rainfallMax.multiply(new BigDecimal("3.5")).intValue());
//			retval.put("timeArr", listTime);
//			retval.put("rainfallArr", listRainfall);
//
////			CommonUtil.listP = listRainfall;
//
//	//            for(BigDecimal r : rainfallArr){
//	//                System.out.println(r);
//	//            }
//
//
//
//			// 没数据不要显示为0
//			List<River> rivers = new ArrayList<>();
//			List<BigDecimal> riverArr = new ArrayList<>();
////			BigDecimal riverMax = new BigDecimal(station.getJjLine());
//BigDecimal riverMax = new BigDecimal(122);
//			BigDecimal riverMin = NumberConst.ZERO;
//			if( CommonConst.FORECAST_LIULIANG.equals(type) ) {
//				rivers = riverService.selectRiverQRange(stcd, plusDay(day, forecastTime), affectTime);
//				for (int i = 0; i < rivers.size(); i++) {
//					River r = rivers.get(i);
//					BigDecimal q = r.getQ() != null ? r.getQ().setScale(2, NumberConst.MODE) : NumberConst.ZERO;
//					riverArr.add(q);
//					if( NumberUtil.gt(q, riverMax) ){
//						riverMax = q;
//					}
//					if( NumberUtil.lt(q, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
//						riverMin = q;
//					}
//				}
//				retval.put("forecastText", "流量");
//				retval.put("forecastUnit", "流量(m³/s)");
//				retval.put("color", "#FF5722");
//			}else{
//				rivers = riverService.selectRiverZRange(stcd, plusDay(day, forecastTime), affectTime);
//				for (int i = 0; i < rivers.size(); i++) {
//					River r = rivers.get(i);
//					BigDecimal z = r.getZ() != null ? r.getZ().setScale(2, NumberConst.MODE) : NumberConst.ZERO;
//					riverArr.add(z);
//					if( NumberUtil.gt(z, riverMax) ){
//						riverMax = z;
//					}
//					if( NumberUtil.lt(z, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
//						riverMin = z;
//					}
//				}
//				retval.put("forecastText", "水位");
//				retval.put("forecastUnit", "水位(m)");
//				retval.put("color", "#009688");
//			}
//			retval.put("riverArr", riverArr);
//
////			List<BigDecimal> forecastArr = new ArrayList<>();
////			if( !CommonConst.STCD_FENKENG.equals(p.getStcd().trim()) ) {
////				forecastArr = doCalc(plan, listRainfall, null, null);
////			}else{
////				if( step == 1 ){
////					forecastArr = doCalc(plan, listRainfall, plan.getKE(), plan.getXE());
////	//                    FORECAST_STEP_ONE = StepFiveUtil.getQT(forecastArr, plan.getKE(), plan.getXE());
////				}else if( step == 2 ){
////					forecastArr = doCalc(plan, listRainfall, plan.getKE(), plan.getXE());
////	//                    FORECAST_STEP_TWO = StepFiveUtil.getQT(forecastArr, plan.getKE(), plan.getXE());
////				}else if( step == 3 ){
////					forecastArr = doCalc(plan, listRainfall, null, null);
////					//
////					for (int i=0; i<forecastArr.size(); i++){
////						BigDecimal v = FORECAST_STEP_ONE.get(i).add(FORECAST_STEP_TWO.get(i).add(forecastArr.get(i)));
////						forecastArr.set(i, v);
////					}
////				}
////			}
////			for (int i = 0; i < forecastArr.size(); i++) {
////				BigDecimal r = forecastArr.get(i).setScale(2, NumberConst.MODE);
////				// 差值法
////				if( CommonConst.FORECAST_SHUIWEI.equals(type) ){
////					Zq zqMin = zqService.selectZqMin(plan.getStcd(), r);
////					Zq zqMax = zqService.selectZqMax(plan.getStcd(), r);
////					if( zqMin == null && zqMax != null ){
////						BigDecimal y = zqMax.getY();
////						r = y;
////					}else if( zqMin != null && zqMax == null ){
////						BigDecimal y = zqMin.getY();
////						r = y;
////					}else if( zqMin != null && zqMax != null ){
////						BigDecimal x = r;
////						BigDecimal x1 = zqMin.getX();
////						BigDecimal y1 = zqMin.getY();
////						BigDecimal x2 = zqMax.getX();
////						BigDecimal y2 = zqMax.getY();
////						BigDecimal y = null;
////						if( x2.equals(x1) ) {
////							y = y1;
////						}else{
////							y = y1.add(x.subtract(x1).multiply(y2.subtract(y1)).divide(x2.subtract(x1), NumberConst.DIGIT, NumberConst.MODE)).setScale(2, NumberConst.MODE);
////						}
////						r = y;
////					}else{
////						System.out.println("程序错误");
////					}
////				}
////				forecastArr.set(i, r);
////				if( NumberUtil.gt(r, riverMax) ){
////					riverMax = r;
////				}
////				if( NumberUtil.lt(r, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
////					riverMin = r;
////				}
////			}
////			retval.put("forecastArr", forecastArr);
//			retval.put("riverMax", riverMax.add(riverMax.subtract(riverMin).multiply(new BigDecimal("0.5"))).add(NumberConst.ONE).intValue());
//			retval.put("riverMin", riverMin.intValue()-1);
	}

	private Map getP(String stcd, String forecastTime, String affectTime, Integer day, Integer unit) {
		Map retval = new HashMap<>();
		/**
		 * 获取子站id
		 */
		List<String> stcdIds = new ArrayList<>();
		List<Map> stationList = stationService.selectChildStationByStcd(stcd);
		for (int i = 0; i < stationList.size(); i++) {
			stcdIds.add(String.valueOf(stationList.get(i).get("stcd")));
		}
		/**
		 * 实测雨量
		 */
		Map<String, BigDecimal> forecastMap = new TreeMap<>();
		String startDay = forecastTime;
		String endDay = DateUtil.date2str(DateUtil.addDay(DateUtil.str2date(forecastTime, CommonConst.DATETIME_FORMAT), day), CommonConst.DATETIME_FORMAT);
		if( CommonConst.FUTURE_RAINFALL_MEASURE.equals(unit) ) {
			List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdIds, endDay, startDay);
			for (Rainfall rainfall : rainfalls) {
				forecastMap.put(rainfall.getDate(), rainfall.getRainfall());
			}

			Date sDay = DateUtil.str2date(startDay, CommonConst.DATETIME_FORMAT);
			Date eDay = DateUtil.str2date(endDay, CommonConst.DATETIME_FORMAT);
			Date iDay = eDay;
			while (!iDay.before(sDay)) {
				String key = DateUtil.date2str(iDay, CommonConst.DATETIME_FORMAT);
				if( !forecastMap.containsKey(key) ){
					forecastMap.put(key, NumberConst.ZERO);
				}
				iDay = DateUtil.addHour(iDay, -1);
			}
			/**
			 * 欧洲台或日本台
			 */
		}else{
			List<Grid> gridList = gridService.selectByStcd(stcd);
			List<String> gridId = new ArrayList<>();
			for (Grid grid : gridList){
				gridId.add(grid.getGridId());
			}

			String fymdh = DateUtil.date2str(DateUtil.addDay(new Date(), -1)) + " 20:00:00";
			List<Forecast> forecastList = forecastService.selectForecast(gridId, fymdh, unit, startDay, endDay);

			if( forecastList.size() > 0 ) {
				for (Forecast forecast : forecastList) {
					forecastMap.put(DateUtil.date2str(forecast.getYmdh(), CommonConst.DATETIME_FORMAT), forecast.getRn());
				}

				Date sDay = DateUtil.str2date(startDay, CommonConst.DATETIME_FORMAT);
				Date eDay = DateUtil.str2date(endDay, CommonConst.DATETIME_FORMAT);
				Date iDay = eDay;
				BigDecimal n = forecastList.get(forecastList.size()-1).getRn();
				//                    Date d = forecastList.get(forecastList.size()-1).getYmdh();
				Integer c = 0;
				while (!iDay.before(sDay)) {
					String key = DateUtil.date2str(iDay, CommonConst.DATETIME_FORMAT);
					if( forecastMap.containsKey(key) ){
						n = forecastMap.get(key);
						c = 0;
					}else{
						c++;
						if( c >= 3 ){
							n = NumberConst.ZERO;
						}
					}
					if( iDay.after(forecastList.get(forecastList.size()-1).getYmdh()) ){
						n = NumberConst.ZERO;
					}
					forecastMap.put(key, n);
					iDay = DateUtil.addHour(iDay, -1);
				}
				//                    Integer a = 1;
			}
		}
		//            List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, plusDay(day, forecastTime), affectTime);
		List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdIds, forecastTime, affectTime);

		// 拼接数据
		for(String key : forecastMap.keySet()){
			Rainfall r = new Rainfall();
			r.setDate(key);
			r.setRainfall(forecastMap.get(key));
			rainfalls.add(r);
		}

		List<BigDecimal> listRainfall = new ArrayList<>();
		List<String> listTime = new ArrayList<>();
		BigDecimal rainfallMax = NumberConst.ZERO;
		for (Rainfall rainfall : rainfalls) {
			BigDecimal r = rainfall.getRainfall().setScale(2, NumberConst.MODE);
			String t = rainfall.getDate().substring(0, 16);
			listRainfall.add(r);
			listTime.add(t);
			if( NumberUtil.gt(r, rainfallMax) ){
				rainfallMax = r;
			}
		}
		/**
		 * 测试代码开始
		 */
//		listRainfall.clear();
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0"));
//		listRainfall.add(new BigDecimal("1.62"));
//		listRainfall.add(new BigDecimal("0.2"));
//		listRainfall.add(new BigDecimal("0.08"));
//        listRainfall.add(new BigDecimal("0.48"));
//        listRainfall.add(new BigDecimal("0.25"));
//        listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0"));
//        listRainfall.add(new BigDecimal("0.13"));
//        listRainfall.add(new BigDecimal("0.33"));
//        listRainfall.add(new BigDecimal("0.23"));
//        listRainfall.add(new BigDecimal("0.48"));
//        listRainfall.add(new BigDecimal("0.36"));
//        listRainfall.add(new BigDecimal("3.90"));
//        listRainfall.add(new BigDecimal("4.54"));
//        listRainfall.add(new BigDecimal("1.34"));
//        listRainfall.add(new BigDecimal("0.17"));
//        listRainfall.add(new BigDecimal("0.13"));
//        listRainfall.add(new BigDecimal("0.17"));
//        listRainfall.add(new BigDecimal("0.00"));
//        listRainfall.add(new BigDecimal("0.03"));
//        listRainfall.add(new BigDecimal("4.19"));
//        listRainfall.add(new BigDecimal("10.26"));
//        listRainfall.add(new BigDecimal("8.01"));
//        listRainfall.add(new BigDecimal("2.74"));
//        listRainfall.add(new BigDecimal("2.33"));
//        listRainfall.add(new BigDecimal("1.01"));
//        listRainfall.add(new BigDecimal("0.46"));
        /**
         * 测试代码结束
         */
		retval.put("rainfallMax", rainfallMax.multiply(new BigDecimal("3.5")).intValue());
		retval.put("timeList", listTime);
		retval.put("rainfallList", listRainfall);

		return retval;
	}

	private List<BigDecimal> modelStation(String forecastTime, String affectTime, Integer day, Integer unit, JSONArray model){
		List<BigDecimal> retval = new ArrayList<>();
	    List<List<BigDecimal>> result = new ArrayList<>();
	    for( int i = 0; i < model.size(); i++ ){
            List<BigDecimal> childList = new ArrayList<>();
            JSONObject m = model.getJSONObject(i);
            String stcd = m.getString("stcd");
			/**
			 * 子站输出数据
			 */
			if( m.containsKey("children") && m.getJSONArray("children").size() > 0 ) {
                childList = modelStation(forecastTime, affectTime, day, unit, m.getJSONArray("children"));
			}
			/**
			 * 子站数据和本站数据做运算
			 */
			List<BigDecimal> listR = new ArrayList<>();
			List<BigDecimal> listP = new ArrayList<>();
			List<String> listTime = new ArrayList<>();
			List<BigDecimal> listQTRR = new ArrayList<>();
			Integer rainfallMax = 0;

			System.out.println("start: " + System.currentTimeMillis());
			Map rainfallMap = getP(m.getString("stcd"), forecastTime, affectTime, day, unit);
			System.out.println("enddd: " + System.currentTimeMillis());

			listP = (List<BigDecimal>)rainfallMap.get("rainfallList");
			listTime = (List<String>)rainfallMap.get("timeList");
			rainfallMax = (Integer)rainfallMap.get("rainfallMax");

			CommonUtil.forecast.setListP(stcd, listP);
            CommonUtil.forecast.setListTime(stcd, listTime);

			/**
			 * 产流
			 */
			Integer modelClId = m.getInteger("modelClId");
			Plan planCl = setPlan(modelClId, m.getJSONObject("planCl"), m.getBigDecimal("ke"), m.getBigDecimal("xe"));
			if( ModelTypeEnum.XAJ.getId().equals(modelClId) ) {
				listR = XinanRiverUtil.getR(planCl, listP, ModelTypeEnum.XAJ.getId().equals(m.getInteger("modelHlId")) ? CommonConst.RETURN_TYPE_QTR : CommonConst.RETURN_TYPE_R);
			}else if( ModelTypeEnum.LINE.getId().equals(modelClId) ){
				listR = EmpiricalUnitUtil.getR(planCl, listP);
			}else if( ModelTypeEnum.API.getId().equals(modelClId) ) {
				listR = ApiModelUtil.getR(planCl, listP);
			}

            CommonUtil.forecast.setListR(stcd, listR);

			/**
			 * 汇流
			 */
			Integer modelHlId = m.getInteger("modelHlId");
			Plan planHl = setPlan(modelHlId, m.getJSONObject("planHl"), m.getBigDecimal("ke"), m.getBigDecimal("xe"));
			if( ModelTypeEnum.XAJ.getId().equals(modelHlId) ) {
				listQTRR = XinanRiverUtil.getQTRR(planHl, listR);
			}else if( ModelTypeEnum.LINE.getId().equals(modelHlId) ){
				listQTRR = EmpiricalUnitUtil.getQTRR(planHl, listR);
			}else if( ModelTypeEnum.API.getId().equals(modelHlId) ) {
				listQTRR = ApiModelUtil.getQTRR(planHl, listR);
			}

            CommonUtil.forecast.setListQTRR(stcd, listQTRR);

            /**
             * 合并子站流量
             */
            if( childList.size() > 0 ) {
                listQTRR = addList(childList, listQTRR);
            }
            /**
             * 根据站的类型选择马斯京根或调洪演算
             */
            List<BigDecimal> listResult = new ArrayList<>();
            listResult = CommonUtil.getQT(m.getBigDecimal("ke"), m.getBigDecimal("xe"), listR, listQTRR);
		    result.add(listResult);
            /**
             *
             */
            CommonUtil.forecast.setListResult(stcd, listResult);
		}
		/**
		 * 返回本站结果
		 */
        for(int i = 0; i < result.size(); i++){
            if( retval.size() == 0 ){
                retval = result.get(0);
                continue;
            }
            retval = addList(retval, result.get(i));
        }
		return retval;
	}

	private List<BigDecimal> addList(List<BigDecimal> list1, List<BigDecimal> list2){
	    List<BigDecimal> retval = new ArrayList<>();
	    if( list1.size() == list2.size() ) {
            for (int i = 0; i < list1.size(); i++) {
                retval.add(list1.get(i).add(list2.get(i)));
            }
        }
        return retval;
    }

	private Plan setPlan(Integer model, JSONObject p, BigDecimal KE, BigDecimal XE){
		Plan plan = new Plan();
		if( ModelTypeEnum.XAJ.getId().equals(model) ) {
			plan.setF(p.getBigDecimal("F"));
			plan.setK(p.getBigDecimal("K"));
			plan.setIM(p.getBigDecimal("IM"));
			plan.setWUM(p.getBigDecimal("WUM"));
			plan.setWLM(p.getBigDecimal("WLM"));
			plan.setWDM(p.getBigDecimal("WDM"));
			plan.setB(p.getBigDecimal("B"));
			plan.setC(p.getBigDecimal("C"));
			plan.setKSS(p.getBigDecimal("KSS"));
			plan.setKG(p.getBigDecimal("KG"));
			plan.setSM(p.getBigDecimal("SM"));
			plan.setEX(p.getBigDecimal("EX"));
			plan.setCI(p.getBigDecimal("CI"));
			plan.setCG(p.getBigDecimal("CG"));
			plan.setCS(p.getBigDecimal("CS"));
			plan.setL(p.getInteger("L"));
			plan.setT(p.getBigDecimal("T"));
			plan.setKE(KE);
			plan.setXE(XE);
			plan.setWU0(p.getBigDecimal("WU0"));
			plan.setWL0(p.getBigDecimal("WL0"));
			plan.setWD0(p.getBigDecimal("WD0"));
			plan.setS0(p.getBigDecimal("S0"));
			plan.setFR0(p.getBigDecimal("FR0"));
			plan.setQRS0(p.getBigDecimal("QRS0"));
			plan.setQRSS0(p.getBigDecimal("QRSS0"));
			plan.setQRG0(p.getBigDecimal("QRG0"));
		}else if( ModelTypeEnum.LINE.getId().equals(model) ){
			plan.setKE(KE);
			plan.setXE(XE);
			plan.setPA(p.getBigDecimal("PA"));
		}else if( ModelTypeEnum.API.getId().equals(model) ) {
			plan.setKR(p.getBigDecimal("KR"));
			plan.setIM(p.getBigDecimal("IM"));
			plan.setIMM(p.getBigDecimal("IMM"));
			plan.setPA(p.getBigDecimal("PA"));
			plan.setNA(p.getBigDecimal("NA"));
			plan.setNU(p.getBigDecimal("NU"));
			plan.setKG(p.getBigDecimal("KG"));
			plan.setKU(p.getBigDecimal("KU"));
			plan.setAREA(p.getBigDecimal("AREA"));
		}
		return plan;
	}

	private List<BigDecimal> doCalc(Plan plan, List<BigDecimal> rainfallP, BigDecimal KE, BigDecimal XE){
//	    StepCommonUtil.init(plan);
//	    StepOneUtil.init(plan);
//	    StepTwoUtil.init(plan);
//	    StepThreeUtil.init(plan);
//	    StepFourUtil.init(plan);

		List<BigDecimal> QTR_List = new ArrayList<>();
		Integer len = rainfallP.size();

		if( KE != null ) {
			if (NumberUtil.gt(new BigDecimal(plan.getL()), KE)) {
				len += plan.getL();
			} else {
				len += KE.intValue();
			}
		}else{
			len += plan.getL();
		}
		for (int i = 0; i<len; i++){
			QTR_List.add(new BigDecimal(-999));
		}
		BigDecimal initQTR = null;
		BigDecimal lastQTR = NumberConst.ZERO;
		for (int i=0; i<rainfallP.size();i++) {

//            StepCommonUtil.setP(rainfallP.get(i));

//            System.out.print("p="+rainfallP.get(i)+" ");

//            StepOneUtil.getResult();
//            System.out.println(StepTwoUtil.getEKx());
//            System.out.println(StepTwoUtil.getEKy());
//            System.out.println(StepTwoUtil.getEKz());
//            System.out.println("------");
//            System.out.println(StepTwoUtil.getWUx2());
//            System.out.println(StepTwoUtil.getWLx2());
//            System.out.println(StepTwoUtil.getWDx2());
//            StepTwoUtil.getResult();
//            StepThreeUtil.getResult();
//            StepFourUtil.getResult();

			if( initQTR == null ){
//                initQTR = StepFourUtil.QTR;
			}
			if( i<plan.getL() ){
				QTR_List.set(i, initQTR);
			}
//            QTR_List.set(plan.getL() + i, StepFourUtil.QTR);

//            lastQTR = StepFourUtil.QTR;
//            System.out.println("======================"+(i+1)+"======================");
		}
		for(int i=0;i<QTR_List.size();i++){
			if( NumberUtil.et(QTR_List.get(i), new BigDecimal(-999)) ){
				QTR_List.set(i, lastQTR);
			}
		}
		return QTR_List;

		//System.out.println(StepTwoUtil.getWUx1());
		//System.out.println(StepTwoUtil.getWLx1());
		//System.out.println(StepTwoUtil.getWDx1());
	}

	private String plusDay(Integer num, String newDate){
		SimpleDateFormat format = new SimpleDateFormat(CommonConst.DATETIME_FORMAT);
		Date currdate = null;
		try {
			currdate = format.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(currdate);
		ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
		currdate = ca.getTime();
		String enddate = format.format(currdate);
		return enddate;
	}
}
