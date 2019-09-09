package gov.gz.hydrology.controller;

import gov.gz.hydrology.entity.read.Rainfall;
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
	public String index(ModelMap map, @PathVariable("id") Integer id, @RequestParam(value="station", required=false) Integer station) {
		if( id == 1 ) {
			if (station == 1) {
				map.put("img", "1.png");
			} else if (station == 2) {
				map.put("img", "2.png");
			} else if (station == 3) {
				map.put("img", "3.png");
			}
		}else if( id == 2 ){
			map.put("hour", new SimpleDateFormat("H时").format(new Date()));
			List<String> stcdIds = stationService.selectChildStcdByStcd("62303500");
			List<Rainfall> rainfalls = rainfallService.selectRainfallTotal(stcdIds);
			int a = 1;
		}else if( id == 3 ){
			if( station == 1 ) {
				map.put("city", "宁都");
			}else if( station == 2 ) {
				map.put("city", "于都");
			}else if( station == 3 ) {
				map.put("city", "石城");
			}
		}
		return "Iframe"+id;
	}
}
