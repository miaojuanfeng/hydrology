package gov.gz.hydrology.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.service.write.PlanService;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("plan")
public class PlanController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private PlanService planService;

	@GetMapping("list")
	public String list() {
		return "views/plan/list";
	}

	@PostMapping("list")
	@ResponseBody
	public String list(Plan plan) {
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		List<Plan> plans = planService.selectPlan(plan);
		int count = plans.size();
		int number = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Plan p : plans) {
			JSONObject t = new JSONObject();
			t.put("number", ++number);
			t.put("id", p.getId());
//			t.put("stname", p.getStname());
			t.put("name", p.getName());
//			t.put("username", p.getUserName());
			t.put("createTime", format.format(p.getCreateTime()));
			temp.add(t);
		}
		retval.put("code", 0);
		retval.put("count", count);
		retval.put("data", temp);
		return retval.toString();
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		map.put("stations", stationService.selectStationByType("基本站"));
		return "views/plan/insert";
	}

	@PostMapping("insert")
	@ResponseBody
	public String insert(
			@RequestParam("name") String name,
			@RequestParam("stcd") String stcd,
			@RequestParam("model") Integer model,
			Plan plan) {
		JSONObject retval = new JSONObject();
		plan.setName(name);
		plan.setStcd(stcd);
		plan.setCreateUser(16607978866L);
		plan.setCreateTime(new Date());
		planService.insertSelective(plan);
		return retval.toString();
	}

    @PostMapping(value="getByStation", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getByStation(Plan plan) {
        JSONArray retval = new JSONArray();
        List<Plan> plans = planService.selectPlan(plan);
        for(Plan p : plans) {
            JSONObject t = new JSONObject();
            t.put("id", p.getId());
            t.put("name", p.getName());
            retval.add(t);
        }
        return retval.toString();
    }
}
