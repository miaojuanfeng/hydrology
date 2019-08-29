package gov.gz.hydrology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cms/iframe")
public class IframeController {

	@RequestMapping("{id}")
	public String index(ModelMap map, @PathVariable("id") Integer id, @RequestParam(value="station", required=false) Integer station) {
		if( id == 1 ) {
			if( station == 1 ) {
				map.put("img", "1.png");
			}else if( station == 2 ) {
				map.put("img", "2.png");
			}else if( station == 3 ) {
				map.put("img", "3.png");
			}
		}
		return "Iframe"+id;
	}
}
