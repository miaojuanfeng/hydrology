package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.gz.hydrology.utils.DateUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("cms/forecast")
public class CalcController {

	@Autowired
	private StationService stationService;

	@RequestMapping("calc")
	public String calc(ModelMap map) {
		map.put("date", DateUtil.getDate());
		List<Station> stationList = stationService.selectStationByType("基本站");
		map.put("stationList", stationList);
		return "CalcView";
	}

	@RequestMapping("result/{id}")
	public String listResult(ModelMap map, @PathVariable("id") Integer id) {
		map.put("date", DateUtil.getDate());
		map.put("station", id);
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

	@RequestMapping("plan/{id}")
	public String listPlan(ModelMap map, @PathVariable("id") Integer id) {
		map.put("date", DateUtil.getDate());
		map.put("station", id);
		return "PlanView";
	}

	@RequestMapping(value="plan/data",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String dataPlan() {
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		JSONObject t = new JSONObject();
		int count = 100;
		for(int i=1;i<=count;i++) {
			t.put("id", i);
			t.put("name", "宁都站");
			t.put("planName", "方案"+i);
			t.put("planModel", "模型"+i);
			t.put("username", "陈济天");
			t.put("time", "2019-08-19 20:12:22");
			temp.add(t);
		}
		retval.put("code", 0);
		retval.put("count", count);
		retval.put("data", temp);
		return retval.toString();
	}

	@RequestMapping("plan/insert")
	public String insertPlan(ModelMap map) {
		map.put("date", DateUtil.getDate());
		return "PlanInsertView";
	}
}
