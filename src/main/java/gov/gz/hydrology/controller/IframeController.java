package gov.gz.hydrology.controller;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cms/iframe")
public class IframeController {

	@Autowired
	private StationService stationService;

	@Autowired
	private RainfallService rainfallService;

	@RequestMapping("{id}")
	public String index(ModelMap map, @PathVariable("id") Integer id, @RequestParam(value="stcd", required=false) String stcd) {
		if( id == 1 ) {
			Station station = stationService.selectByPrimaryKey(stcd);
			map.put("station", station);
		}else if( id == 2 ){
			map.put("hour", new SimpleDateFormat("H时").format(new Date()));
			List<Map> stations = stationService.selectChildStationByStcd(stcd);
			List<String> stcdId = new ArrayList<>();
			for(int i=0;i<stations.size();i++){
				stcdId.add(String.valueOf(stations.get(i).get("stcd")));
			}
			List<Rainfall> rainfallTotal = rainfallService.selectRainfallTotal(stcdId);
			//
			List<String> stationArr = new ArrayList<>();
			List<BigDecimal> rainfallArr = new ArrayList<>();
			for (int i=0;i<stations.size();i++){
				stationArr.add(String.valueOf(stations.get(i).get("stname")));
				rainfallArr.add(new BigDecimal(0));
				String s = String.valueOf(stations.get(i).get("stcd"));
				for (int j=0;j<rainfallTotal.size();j++){
					if( rainfallTotal.get(j).getStcd().equals(s) ){
						rainfallArr.set(i, rainfallTotal.get(j).getRainfallTotal());
						break;
					}
				}
			}
			map.put("stationArr", stationArr);
			map.put("rainfallArr", rainfallArr);
		}
		return "Iframe"+id;
	}
}
