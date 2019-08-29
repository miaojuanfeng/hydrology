package gov.gz.hydrology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.gz.hydrology.utils.DateUtil;

@Controller
@RequestMapping("cms/calc")
public class CalcController {

	@RequestMapping("index")
	public String index(ModelMap map) {
		map.put("date", DateUtil.getDate());
		return "CalcView";
	}
}
