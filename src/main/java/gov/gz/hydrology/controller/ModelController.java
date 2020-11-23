package gov.gz.hydrology.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.entity.write.Model;
import gov.gz.hydrology.entity.write.ModelStation;
import gov.gz.hydrology.service.write.ModelService;
import gov.gz.hydrology.service.write.ModelStationService;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("views/model")
public class ModelController {
	
	@Autowired
	private StationService stationService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private ModelStationService modelStationService;

	@GetMapping("list")
	public String list(ModelMap map) {
		return "views/model/list";
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
	public String insert(@RequestParam("name") String name, @RequestParam("data") String data) {
		JSONObject retval = new JSONObject();

		Model model = new Model();
		model.setName(name);
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
		map.put("data", m.toString());
		return "views/model/insert";
	}

	@PostMapping("insert/{modelId}")
	@ResponseBody
	@Transactional
	public String insert(@RequestParam("name") String name, @RequestParam("data") String data, @PathVariable("modelId") Integer modelId) {
		JSONObject retval = new JSONObject();

		Model model = new Model();
		model.setId(modelId);
		model.setName(name);
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
				station.put("stcd", modelStation.get("MO_STCD"));
				station.put("stname", modelStation.get("STNAME"));
				station.put("planId", modelStation.get("PLAN_ID"));
				station.put("plan", modelStation.get("PLAN"));
				station.put("clId", modelStation.get("HL_ID"));
				station.put("cl", modelStation.get("CL"));
				station.put("hlId", modelStation.get("HL_ID"));
				station.put("hl", modelStation.get("HL"));
				station.put("ke", modelStation.get("KE"));
				station.put("xe", modelStation.get("XE"));
				station.put("id", modelStation.get("ID"));
				station.put("spread", true);
				JSONArray children = getModelStationData((String)modelStation.get("MO_STCD"), modelStationList);
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
			modelStation.setMoStcd(station.getString("stcd"));
			modelStation.setPlanId(station.getInteger("planId"));
			modelStation.setKe(station.getBigDecimal("ke"));
			modelStation.setXe(station.getBigDecimal("xe"));
			modelStation.setClId(station.getInteger("clId"));
			modelStation.setHlId(station.getInteger("hlId"));
			modelStation.setFaStcd(fatherId);
			retval.add(modelStation);

			JSONArray children = station.getJSONArray("children");
			if( children != null && children.size() > 0 ){
				retval.addAll(setModelStationData(modelId, station.getString("stcd"), children));
			}
		}
		return retval;
	}
}
