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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
			map.put("img", station.getFileCd());
		}else if( id == 2 ){
			map.put("hour", new SimpleDateFormat("H时").format(new Date()));
			List<String> stcdIds = stationService.selectChildStcdByStcd("62303500");
			List<Rainfall> rainfalls = rainfallService.selectRainfallTotal(stcdIds);
			int a = 1;
		}
		return "Iframe"+id;
	}
}
