package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.write.*;
import gov.gz.hydrology.service.common.CommonService;
import gov.gz.hydrology.service.write.PlanService;
import gov.gz.hydrology.service.write.PlanStationService;
import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.service.write.UserStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import gov.gz.hydrology.utils.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("cms/forecast")
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

	@GetMapping("calc")
	public String getCalc(ModelMap map) {
		map.put("date", DateUtil.getDate());
		List<Station> stationList = stationService.selectStationByType("基本站");
		map.put("stationList", stationList);
		List<Plan> planList = planService.selectPlan(CommonConst.STCD_STATION[0]);
		map.put("planList", planList);
		if( planList.size() > 0 ){
			Plan plan = planService.selectById(planList.get(0).getId());
			map.put("plan", plan);
		}
		map.put("stationProgress", commonService.stationProgress(CommonConst.STCD_NINGDU, 1));
		Date date = new Date();
//		map.put("forecastTime", DateUtil.date2str(date, "yyyy-MM-dd HH:00:00"));
//		map.put("affectTime", DateUtil.date2str(DateUtil.addMonth(date, -1), "yyyy-MM-dd HH:00:00"));
		map.put("forecastTime", "2020-04-04 08:00:00");
		map.put("affectTime", "2020-04-01 08:00:00");

		return "CalcView";
	}

	@RequestMapping("result")
	public String result(HttpServletRequest request, ModelMap map) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

		UserStation userStation = userStationService.selectDefault(user.getUserId());
		map.put("stcd", userStation.getStation().getStcd());
		map.put("station", userStation.getStation());

		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
		map.put("stationList", stationList);
		return "ResultView";
	}

	@RequestMapping("result/{stcd}")
	public String stcdResult(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
		map.put("stcd", stcd);
		Station station = stationService.selectByPrimaryKey(stcd);
		map.put("station", station);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
		map.put("stationList", stationList);
		return "ResultView";
	}

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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

		UserStation userStation = userStationService.selectDefault(user.getUserId());
		map.put("stcd", userStation.getStation().getStcd());
		map.put("station", userStation.getStation());

		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
		map.put("stationList", stationList);
		return "PlanView";
	}

	@GetMapping("plan/{stcd}")
	public String getPlan(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
		map.put("stcd", stcd);
		Station station = stationService.selectByPrimaryKey(stcd);
		map.put("station", station);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);
		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
		map.put("stationList", stationList);
		return "PlanView";
	}

	@PostMapping(value="plan/{stcd}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String postPlan(@PathVariable("stcd") String stcd) {
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		List<Plan> plans = planService.selectPlan(stcd);
        int count = plans.size();
        int number = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Plan plan : plans) {
			JSONObject t = new JSONObject();
			t.put("number", ++number);
            t.put("id", plan.getId());
			t.put("stname", plan.getStname());
			t.put("name", plan.getName());
			t.put("planModel", "新安江模型");
			t.put("username", plan.getUserName());
			t.put("time", format.format(plan.getCreateTime()));
			temp.add(t);
		}
		retval.put("code", 0);
		retval.put("count", count);
		retval.put("data", temp);
		return retval.toString();
	}

	@GetMapping("plan/insert/{stcd}")
	public String getInsertPlan(HttpServletRequest request, ModelMap map, @PathVariable("stcd") String stcd) {
	    HttpSession session = request.getSession();
		User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

		Station station = stationService.selectByPrimaryKey(stcd);
		map.put("station", station);

		List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
		map.put("stationList", stationList);

        List<Map> qtStationList = stationService.selectQtStation(stcd);
        for(Map qtStation : qtStationList){
            String qtStcd = String.valueOf(qtStation.get("PO_STCD"));
            List<Plan> qtPlan = planService.selectPlan(qtStcd);
            qtStation.put("plan", qtPlan);
        }
        map.put("qtStationList", qtStationList);

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

        plan.setUserId(user.getUserId());
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

    @GetMapping("plan/update/{id}")
    public String getInsertPlan(HttpServletRequest request, ModelMap map, @PathVariable("id") Integer id) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(CommonConst.SESSION_KEY_USER);

        List<UserStation> stationList = userStationService.selectByUserId(user.getUserId());
        map.put("stationList", stationList);

        Plan plan = planService.selectById(id);
        map.put("plan", plan);

        Station station = stationService.selectByPrimaryKey(plan.getStcd());
        map.put("station", station);

        List<PlanStation> planStationList = planStationService.selectByPlan(plan.getId());

//		List<Map> qtStationList = stationService.selectQtStation(plan.getStcd());
//        for(Map qtStation : qtStationList){
//            for(PlanStation planStation : planStationList){
//                if( String.valueOf(qtStation.get("PO_STCD")).equals(planStation.getPoStcd()) ){
//					qtStation.put("ke", planStation.getKe());
//					qtStation.put("xe", planStation.getXe());
//
//					String qtStcd = String.valueOf(qtStation.get("PO_STCD"));
//					List<Plan> qtPlan = planService.selectPlan(qtStcd);
//					qtStation.put("plan", qtPlan);
//
//                    break;
//                }
//            }
//        }
//        map.put("qtStationList", qtStationList);

		List<Map> qtStationList = new ArrayList<>();
		for(PlanStation planStation : planStationList){
			Map qtStation = new HashMap();
			qtStation.put("PO_STCD", planStation.getPoStcd());
			qtStation.put("childPlanId", planStation.getChildPlanId());
			qtStation.put("ke", planStation.getKe());
			qtStation.put("xe", planStation.getXe());
			//
			Station s = stationService.selectByPrimaryKey(planStation.getPoStcd());
			qtStation.put("stname", s.getStname());
			//
			List<Plan> planList = planService.selectPlan(planStation.getPoStcd());
			qtStation.put("plan", planList);
			for( Plan p : planList ){
				if( p.getId().equals(planStation.getChildPlanId()) ){
					qtStation.put("planName", p.getName());
					break;
				}
			}
			//
			qtStationList.add(qtStation);
		}
		map.put("qtStationList", qtStationList);

		return "PlanInsertView";
    }

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
}
