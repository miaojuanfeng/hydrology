package gov.gz.hydrology.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.utils.DateUtil;
import gov.gz.hydrology.utils.StepCommonUtil;
import gov.gz.hydrology.utils.StepFiveUtil;
import gov.gz.hydrology.utils.StepFourUtil;
import gov.gz.hydrology.utils.StepOneUtil;
import gov.gz.hydrology.utils.StepThreeUtil;
import gov.gz.hydrology.utils.StepTwoUtil;

@Controller
@RequestMapping("cms/step")
public class StepController {

	@RequestMapping("index")
	public String index(ModelMap map) {
		map.put("date", DateUtil.getDate());
		
		map.put("para", getPara());
		map.put("stepCommon", getStepCommon());
		map.put("stepOne", getStepOne());
		map.put("stepTwo", getStepTwo());
		map.put("stepThree", getStepThree());
		map.put("stepFour", getStepFour());
		map.put("stepFive", getStepFive());
		
		return "StepView";
	}
	
	private Map<String, Object> getPara(){
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("WU0", NumberConfig.WU0);
		para.put("WL0", NumberConfig.WL0);
		para.put("WD0", NumberConfig.WD0);
		para.put("QRSS", NumberConfig.QRSS);
		para.put("QRG", NumberConfig.QRG);
		para.put("WUM", NumberConfig.WUM);
		para.put("WLM", NumberConfig.WLM);
		para.put("WDM", NumberConfig.WDM);
		para.put("B", NumberConfig.B);
		para.put("K", NumberConfig.K);
		para.put("C", NumberConfig.C);
		para.put("SM", NumberConfig.SM);
		para.put("EX", NumberConfig.EX);
		para.put("KSS", NumberConfig.KSS);
		para.put("KG", NumberConfig.KG);
		para.put("IM", NumberConfig.IM);
		para.put("XE", NumberConfig.XE);
		para.put("KE", NumberConfig.KE);
		para.put("DT", NumberConfig.DT);
		return para;
	}
	
	private Map<String, Object> getStepCommon(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("Ek", StepCommonUtil.getEk());
		data.put("PE", StepCommonUtil.getPE());
		return data;
	}
	
	private Map<String, Object> getStepOne(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("W0", StepOneUtil.getW0());
		data.put("Wm", StepOneUtil.getWm());
		data.put("Wmm", StepOneUtil.getWmm());
		data.put("A", StepOneUtil.getA());
		
		StepOneUtil.getResult();
		data.put("Rd", StepOneUtil.Rd);
		data.put("R", StepOneUtil.R);
		return data;
	}
	
	private Map<String, Object> getStepTwo(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("PEx", StepTwoUtil.getPEx());
		data.put("WUx1", StepTwoUtil.getWUx1());
		data.put("PEy", StepTwoUtil.getPEy());
		data.put("WLx1", StepTwoUtil.getWLx1());
		data.put("PEz", StepTwoUtil.getPEz());
		data.put("WDx1", StepTwoUtil.getWDx1());
		
		data.put("EKx", StepTwoUtil.getEKx());
		data.put("WUx2", StepTwoUtil.getWUx2());
		data.put("EKy", StepTwoUtil.getEKy());
		data.put("WLx2", StepTwoUtil.getWLx2());
		data.put("EKz", StepTwoUtil.getEKz());
		data.put("WDx2", StepTwoUtil.getWDx2());
		
		StepTwoUtil.getResult();
		data.put("WU", StepTwoUtil.WU);
		data.put("WL", StepTwoUtil.WL);
		data.put("WD", StepTwoUtil.WD);
		return data;
	}
	
	private Map<String, Object> getStepThree(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("FR", StepThreeUtil.getFR());
		data.put("SMMF", StepThreeUtil.getSMMF());
		data.put("AU", StepThreeUtil.getAU());
		data.put("Rs", StepThreeUtil.Rs);
		
		StepThreeUtil.getResult();
		data.put("Rss", StepThreeUtil.Rss);
		data.put("Rg", StepThreeUtil.Rg);
		data.put("S", StepThreeUtil.S);
		data.put("Sup", StepThreeUtil.Sup);
		return data;
	}
	
	private Map<String, Object> getStepFour(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("F", StepFourUtil.getF());
		data.put("Qs", StepFourUtil.getQs());
		data.put("Qss", StepFourUtil.getQss());
		data.put("Qg", StepFourUtil.getQg());
		
		StepFourUtil.getResult();
		data.put("Qe", StepFourUtil.Qe);
		data.put("Qeup", StepFourUtil.Qeup);
		data.put("Qssup", StepFourUtil.Qssup);
		data.put("Qgup", StepFourUtil.Qgup);
		return data;
	}
	
	private Map<String, Object> getStepFive(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("C0", StepFiveUtil.getC0());
		data.put("C1", StepFiveUtil.getC1());
		data.put("C2", StepFiveUtil.getC2());
		data.put("Qt", StepFiveUtil.getQt());
		return data;
	}
}
