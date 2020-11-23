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
import java.util.List;

@Controller
@RequestMapping("views/plan")
public class PlanController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private PlanService planService;

	@GetMapping("list")
	public String list(ModelMap map) {
		return "views/plan/list";
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		return "views/plan/insert";
	}

	@PostMapping(value="ajax/{stcd}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String ajax(@PathVariable("stcd") String stcd) {
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

    @PostMapping(value="getByStation", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getByStation(@RequestParam("stcd") String stcd) {
        JSONArray retval = new JSONArray();
        List<Plan> plans = planService.selectPlan(stcd);
        for(Plan plan : plans) {
            JSONObject t = new JSONObject();
            t.put("id", plan.getId());
            t.put("name", plan.getName());
            retval.add(t);
        }
        return retval.toString();
    }
}
