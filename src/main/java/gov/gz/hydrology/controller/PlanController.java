package gov.gz.hydrology.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gov.gz.hydrology.utils.DateUtil;

@Controller
@RequestMapping("cms/plan")
public class PlanController {

	@RequestMapping("{id}")
	public String index(ModelMap map, @PathVariable("id") Integer id) {
		map.put("date", DateUtil.getDate());
		map.put("station", id);
		return "PlanView";
	}
	
	@RequestMapping(value="data",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String data() {
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
	
	@RequestMapping("insert")
	public String insert(ModelMap map) {
		map.put("date", DateUtil.getDate());
		return "PlanInsertView";
	}
}
