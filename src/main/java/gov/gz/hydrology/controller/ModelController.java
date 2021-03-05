package gov.gz.hydrology.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.ModelTypeEnum;
import gov.gz.hydrology.entity.write.Model;
import gov.gz.hydrology.entity.write.ModelStation;
import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.service.write.ModelService;
import gov.gz.hydrology.service.write.ModelStationService;
import gov.gz.hydrology.service.write.PlanService;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("model")
public class ModelController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private PlanService planService;

	@Autowired
	private ModelStationService modelStationService;

	@GetMapping("list")
	public String list() {
		return "views/model/list";
	}

	@PostMapping("list")
	@ResponseBody
	public String list(ModelMap map) {
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		List<Model> models = modelService.selectModel();
		int count = models.size();
		int number = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Model model : models) {
			JSONObject t = new JSONObject();
			t.put("number", ++number);
			t.put("id", model.getId());
			t.put("name", model.getName());
			t.put("username", "ABC");
			t.put("createTime", format.format(new Date()));
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
		map.put("data", new JSONArray().toString());
		return "views/model/insert";
	}

	@PostMapping("insert")
	@ResponseBody
	@Transactional
	public String insert(@RequestParam("name") String name, @RequestParam("stcd") String stcd, @RequestParam("data") String data) {
		JSONObject retval = new JSONObject();

		Model model = new Model();
		model.setName(name);
		model.setStcd(stcd);
		modelService.insertSelective(model);

//		JSONArray temp = JSONArray.parseArray(data);
		List<ModelStation> modelStationList = setModelStationData(model.getId(), "0", JSONArray.parseArray(data));
		if( modelStationList.size() > 0 ) {
			modelStationService.insertBatch(modelStationList);
		}
		return retval.toString();
	}

	@GetMapping("insert/{modelId}")
	public String insert(ModelMap map, @PathVariable("modelId") Integer modelId) {
		map.put("stations", stationService.selectStationByType("基本站"));
		Model model = modelService.selectById(modelId);
		List t = modelStationService.selectByModel(modelId);
		JSONArray m = getModelStationData("0", t);
		map.put("id", model.getId());
		map.put("name", model.getName());
		map.put("stcd", model.getStcd());
		map.put("data", m.toString());
		return "views/model/insert";
	}

	@PostMapping("insert/{modelId}")
	@ResponseBody
	@Transactional
	public String insert(@RequestParam("name") String name, @RequestParam("stcd") String stcd, @RequestParam("data") String data, @PathVariable("modelId") Integer modelId) {
		JSONObject retval = new JSONObject();

		Model model = new Model();
		model.setId(modelId);
		model.setName(name);
		model.setStcd(stcd);
		modelService.updateSelective(model);

//		JSONArray temp = JSONArray.parseArray(data);
		modelStationService.deleteByModel(modelId);
		List<ModelStation> modelStationList = setModelStationData(model.getId(), "0", JSONArray.parseArray(data));
		if( modelStationList.size() > 0 ) {
			modelStationService.insertBatch(modelStationList);
		}
		return retval.toString();
	}

	private JSONArray getModelStationData(String fatherId, List<Map> modelStationList){
		JSONArray retval = new JSONArray();
		for (int i = 0; i < modelStationList.size(); i++){
			Map modelStation = modelStationList.get(i);
			String m = (String)modelStation.get("FA_STCD");
			if( fatherId.trim().equals((String)((String) modelStation.get("FA_STCD")).trim()) ){
				JSONObject station = new JSONObject();
				station.put("title", modelStation.get("STNAME"));
				station.put("stcd", String.valueOf(modelStation.get("STCD")).trim());
				station.put("stname", modelStation.get("STNAME"));
				station.put("modelClId", modelStation.get("MODEL_CL"));
				station.put("modelClName", ModelTypeEnum.getText((Integer)modelStation.get("MODEL_CL")));
				station.put("planClId", modelStation.get("PLAN_CL_ID"));
				station.put("planClName", modelStation.get("PLAN_CL_NAME"));
				station.put("modelHlId", modelStation.get("MODEL_HL"));
				station.put("modelHlName", ModelTypeEnum.getText((Integer)modelStation.get("MODEL_HL")));
				station.put("planHlId", modelStation.get("PLAN_HL_ID"));
				station.put("planHlName", modelStation.get("PLAN_HL_NAME"));
				station.put("ke", modelStation.get("KE"));
				station.put("xe", modelStation.get("XE"));
				station.put("id", modelStation.get("ID"));
				station.put("spread", true);
				JSONArray children = getModelStationData((String)modelStation.get("STCD"), modelStationList);
				if( children.size() > 0 ){
					station.put("children", children);
				}
				retval.add(station);
			}
		}
		return retval;
	}

	private List<ModelStation> setModelStationData(Integer modelId, String fatherId, JSONArray data){
		List<ModelStation> retval = new ArrayList<>();
		for (int i = 0; i < data.size(); i++){
			JSONObject station = (JSONObject) data.get(i);
			ModelStation modelStation = new ModelStation();
			modelStation.setModelId(modelId);
			modelStation.setStcd(station.getString("stcd"));

			modelStation.setKe(station.getBigDecimal("ke"));
			modelStation.setXe(station.getBigDecimal("xe"));
			modelStation.setModelCl(station.getInteger("modelClId"));
			modelStation.setPlanCl(station.getInteger("planClId"));
			modelStation.setModelHl(station.getInteger("modelHlId"));
			modelStation.setPlanHl(station.getInteger("planHlId"));
			modelStation.setFaStcd(fatherId);
			retval.add(modelStation);

			JSONArray children = station.getJSONArray("children");
			if( children != null && children.size() > 0 ){
				retval.addAll(setModelStationData(modelId, station.getString("stcd"), children));
			}
		}
		return retval;
	}

	@PostMapping(value="getByStation", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getByStation(@RequestParam("stcd") String stcd) {
		JSONArray retval = new JSONArray();
		List<Model> models = modelService.selectModel(stcd);
		for(Model model : models) {
			JSONObject t = new JSONObject();
			t.put("id", model.getId());
			t.put("name", model.getName());
			retval.add(t);
		}
		return retval.toString();
	}

	@PostMapping(value="getArea", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getArea(@RequestParam("modelId") Integer modelId) {
		JSONArray modelStationList = getModelStationData("0", modelStationService.selectByModel(modelId));
		setModelStationPlan(modelStationList);
		return modelStationList.toString();
	}

	private void setModelStationPlan(JSONArray modelStationList){
		for (int i = 0; i < modelStationList.size(); i++){
			JSONObject modelStation = (JSONObject) modelStationList.get(i);
			if( modelStation.containsKey("children") && modelStation.getJSONArray("children").size() > 0 ) {
				setModelStationPlan(modelStation.getJSONArray("children"));
			}
			modelStation.put("planCl", setPlan(planService.selectById(modelStation.getInteger("planClId"))));
			modelStation.put("planHl", setPlan(planService.selectById(modelStation.getInteger("planHlId"))));
//			Plan plan = planService.selectById(modelStation.getInteger("planId"));
//			Map m = new HashMap();
//			m.put("name", plan.getName());
//			m.put("sm", plan.getSM());
//			m.put("ci", plan.getCI());
//			m.put("cs", plan.getCS());
//			m.put("l", plan.getL());
//			m.put("wu0", plan.getWU0());
//			m.put("wl0", plan.getWL0());
//			m.put("wd0", plan.getWD0());
//			m.put("s0", plan.getS0());
//			m.put("fr0", plan.getFR0());
//			m.put("qrs0", plan.getQRS0());
//			m.put("qrss0", plan.getQRSS0());
//			m.put("qrg0", plan.getQRG0());
//			modelStation.put("plan", m);
		}
	}

	private Map setPlan(Plan plan) {
		Map m = new HashMap();
 		if( plan.getModel().equals(1) ){
			m.put("ID", plan.getId());
			m.put("STCD", plan.getStcd());
			m.put("NAME", plan.getName());
			m.put("MODEL", plan.getModel());
			m.put("WU0", plan.getWU0());
			m.put("WL0", plan.getWL0());
			m.put("WD0", plan.getWD0());
			m.put("WUM", plan.getWUM());
			m.put("WLM", plan.getWLM());
			m.put("WDM", plan.getWDM());
			m.put("B", plan.getB());
			m.put("K", plan.getK());
			m.put("C", plan.getC());
			m.put("SM", plan.getSM());
			m.put("EX", plan.getEX());
			m.put("KSS", plan.getKSS());
			m.put("KG", plan.getKG());
			m.put("IM", plan.getIM());
			m.put("CI", plan.getCI());
			m.put("CG", plan.getCG());
			m.put("CS", plan.getCS());
			m.put("L", plan.getL());
			m.put("T", plan.getT());
			m.put("F", plan.getF());
			m.put("S0", plan.getS0());
			m.put("FR0", plan.getFR0());
			m.put("QRS0", plan.getQRS0());
			m.put("QRSS0", plan.getQRSS0());
			m.put("QRG0", plan.getQRG0());
		}else if( plan.getModel().equals(2) ){
			m.put("ID", plan.getId());
			m.put("STCD", plan.getStcd());
			m.put("NAME", plan.getName());
			m.put("MODEL", plan.getModel());
			m.put("PA", plan.getPA());
		}else if( plan.getModel().equals(3) ){
			m.put("ID", plan.getId());
			m.put("STCD", plan.getStcd());
			m.put("NAME", plan.getName());
			m.put("MODEL", plan.getModel());
			m.put("PA", plan.getPA());
			m.put("KR", plan.getKR());
			m.put("IM", plan.getIM());
			m.put("IMM", plan.getIMM());
			m.put("NA", plan.getNA());
			m.put("NU", plan.getNU());
			m.put("KG", plan.getKG());
			m.put("KU", plan.getKU());
			m.put("AREA", plan.getAREA());
		}
		return m;
	}
}
