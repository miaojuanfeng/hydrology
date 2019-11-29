package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.write.*;
import gov.gz.hydrology.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	private RiverService riverService;

	@Autowired
    private PlanService planService;

	@Autowired
	private CacheRiverTimeService cacheRiverTimeService;

	@Autowired
	private CacheRainfallDailyService cacheRainfallDailyService;

	@Autowired
	private CacheRainfallTotalService cacheRainfallTotalService;

	@RequestMapping("{id}")
	public String index(ModelMap map, @PathVariable("id") Integer id, @RequestParam(value="stcd",required=false) String stcd) {
		if( id == 1 ) {
			Station station = stationService.selectByPrimaryKey(stcd);
			map.put("station", station);

            List<Rainfall> rainfallTotal = cacheRainfallTotalService.selectByStcd(stcd);
            BigDecimal rainfallSum = new BigDecimal(0);
            for (int i=0;i<rainfallTotal.size();i++){
                rainfallSum.add(rainfallTotal.get(i).getRainfall());
            }
            Integer ava = 0;
            if( rainfallTotal.size() > 0 ) {
                Integer rainfallAva = rainfallSum.divide(new BigDecimal(rainfallTotal.size()), NumberConst.DIGIT, NumberConst.MODE).intValue();
                if (rainfallAva < 50) {
                    ava = 10;
                } else if (rainfallAva < 70) {
                    ava = 20;
                } else if (rainfallAva < 90) {
                    ava = 30;
                } else if (rainfallAva < 110) {
                    ava = 40;
                } else if (rainfallAva < 130) {
                    ava = 50;
                } else if (rainfallAva < 150) {
                    ava = 60;
                } else if (rainfallAva < 170) {
                    ava = 70;
                } else if (rainfallAva < 190) {
                    ava = 80;
                } else if (rainfallAva < 210) {
                    ava = 90;
                } else {
                    ava = 100;
                }
            }
            map.put("ava", ava);
		}else if( id == 2 ){
			map.put("hour", new SimpleDateFormat("H时").format(new Date()));
//			List<Map> stations = stationService.selectChildStationByStcd(stcd);
//			List<String> stcdId = new ArrayList<>();
//			for(int i=0;i<stations.size();i++){
//				stcdId.add(String.valueOf(stations.get(i).get("stcd")));
//			}
//			List<Rainfall> rainfallTotal = rainfallService.selectRainfallTotal(stcdId);
            List<Rainfall> rainfallTotal = cacheRainfallTotalService.selectByStcd(stcd);
//
//			//
//
//
//			cacheRainfallTotalService.deleteByStcd(stcd);
//			List<Rainfall> copyRainfallTotal = new ArrayList<>();
//			for (int i=0;i<rainfallTotal.size();i++){
//                for (int j=0;j<stations.size();j++) {
//                    if( rainfallTotal.get(i).getStcd().equals(String.valueOf(stations.get(j).get("stcd"))) ){
//                        rainfallTotal.get(i).setStname(String.valueOf(stations.get(j).get("stname")));
//                        break;
//                    }
//                }
//                rainfallTotal.get(i).setStcd(stcd);
//                copyRainfallTotal.add(rainfallTotal.get(i));
//                if (copyRainfallTotal.size() >= 500 || i == rainfallTotal.size() - 1) {
//                    cacheRainfallTotalService.insertBatch(copyRainfallTotal);
//                    copyRainfallTotal.clear();
//                }
//			}


			//
			List<String> stationArr = new ArrayList<>();
			List<BigDecimal> rainfallArr = new ArrayList<>();
			Integer max = Integer.MIN_VALUE;
			for (int i=0;i<rainfallTotal.size();i++){
				stationArr.add(rainfallTotal.get(i).getStname());
				rainfallArr.add(rainfallTotal.get(i).getRainfall());
				if( max < rainfallTotal.get(i).getRainfall().intValue() ){
					max = rainfallTotal.get(i).getRainfall().intValue() + 10;
				}
			}
			map.put("stationArr", stationArr);
			map.put("rainfallArr", rainfallArr);
			map.put("max", max);
		}else if( id == 4 ){
            Station station = stationService.selectByPrimaryKey(stcd);
            map.put("station", station);
            //
//            List<Map> stations = stationService.selectChildStationByStcd(stcd);
//            List<String> stcdId = new ArrayList<>();
//            for(int i=0;i<stations.size();i++){
//                stcdId.add(String.valueOf(stations.get(i).get("stcd")));
//            }
//            List<Rainfall> rainfallDaily = rainfallService.selectRainfallDaily(stcdId);
			List<Rainfall> rainfallDaily = cacheRainfallDailyService.selectByStcd(stcd);
			//

//			cacheRainfallDailyService.deleteByStcd(stcd);
//			List<Rainfall> copyRainfallDaily = new ArrayList<>();
//			for (int i=0;i<rainfallDaily.size();i++){
//				rainfallDaily.get(i).setStcd(stcd);
//				copyRainfallDaily.add(rainfallDaily.get(i));
//				if(copyRainfallDaily.size()>=500 || i==rainfallDaily.size()-1) {
//					cacheRainfallDailyService.insertBatch(copyRainfallDaily);
//					copyRainfallDaily.clear();
//				}
//			}

			//
			List<String> dateArr = new ArrayList<>();
			List<BigDecimal> rainfallArr = new ArrayList<>();
			for (int i=0;i<rainfallDaily.size();i++){
				dateArr.add(rainfallDaily.get(i).getDate().substring(0,5));
				rainfallArr.add(rainfallDaily.get(i).getRainfall());
//				String s = String.valueOf(stations.get(i).get("stcd"));
//				for (int j=0;j<rainfallDaily.size();j++){
//					if( rainfallDaily.get(j).getStcd().equals(s) ){
//						rainfallArr.set(i, rainfallDaily.get(j).getRainfall());
//						break;
//					}
//				}
			}
			map.put("dateArr", dateArr);
			map.put("rainfallArr", rainfallArr);
        }else if( id == 5 ){
			Station station = stationService.selectByPrimaryKey(stcd);
			map.put("station", station);
			//
//			List<River> riverTime = riverService.selectRiverTime(stcd);
			List<River> riverTime = cacheRiverTimeService.selectRiverTime(stcd);
			//
//			cacheRiverTimeService.deleteByStcd(stcd);
//			List<River> copyRiverTime = new ArrayList<>();
//			for (int i=0;i<riverTime.size();i++){
//				copyRiverTime.add(riverTime.get(i));
//				if(copyRiverTime.size()>=500 || i==riverTime.size()-1) {
//					cacheRiverTimeService.insertBatch(copyRiverTime);
//					copyRiverTime.clear();
//				}
//			}
			//
			List<String> timeArr = new ArrayList<>();
			List<BigDecimal> riverArr = new ArrayList<>();
			SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
			Integer max = Integer.MIN_VALUE;
			Integer min = Integer.MAX_VALUE;
			for (int i=0;i<riverTime.size();i++){
				String time = format.format(riverTime.get(i).getTm());
//				if( time.endsWith(":00") ) {
					timeArr.add(time);
//				}else{
//					timeArr.add("");
//				}
				riverArr.add(riverTime.get(i).getZ());
				if( max < riverTime.get(i).getZ().intValue() ){
					max = riverTime.get(i).getZ().add(new BigDecimal(1)).intValue();
				}
				if( min > riverTime.get(i).getZ().intValue() ){
					min = riverTime.get(i).getZ().intValue();
				}
			}
//			if( max < station.getJjLine() ){
//				max = station.getJjLine() + 1;
//			}
//			if( min > station.getJbLine() ){
//				min = station.getJbLine();
//			}
			map.put("timeArr", timeArr);
			map.put("riverArr", riverArr);
			map.put("max", max);
			map.put("min", min);
			map.put("jbLine", station.getJbLine());
			map.put("jjLine", station.getJjLine());
		}else if( id == 7 ){
            List<BigDecimal> forcastArr = new ArrayList<>();
            forcastArr.add(new BigDecimal("0.1"));
            forcastArr.add(new BigDecimal("0.2"));
            forcastArr.add(new BigDecimal("0.3"));
            forcastArr.add(new BigDecimal("0.4"));
            map.put("forcastArr", forcastArr);
        }
		return "Iframe"+id;
	}

	@GetMapping("calc")
	public String postCalc(ModelMap map, Plan p, String forecastTime, String affectTime, String day) {
		JSONObject retval = new JSONObject();
//        map.put("date", DateUtil.getDate());
//        List<Station> stationList = stationService.selectStationByType("基本站");
//        map.put("stationList", stationList);
//        List<Plan> planList = planService.selectPlan(CommonConst.STCD_STATION[0]);
//        map.put("planList", planList);
//        if( planList.size() > 0 ){
//            Plan plan = planService.selectById(planList.get(0).getId());
//            map.put("plan", plan);
//        }
        Plan plan = planService.selectById(p.getId());
        if( plan != null ){
            Station station = stationService.selectByPrimaryKey(plan.getStcd());
            map.put("station", station);
            map.put("jjLine", station.getJjLine());

            plan.setSM(p.getSM());
            plan.setCI(p.getCI());
            plan.setCS(p.getCS());
            plan.setL(p.getL());
            plan.setKE(p.getKE());
            plan.setXE(p.getXE());

            plan.setWU0(p.getWU0());
            plan.setWL0(p.getWL0());
            plan.setWD0(p.getWD0());
            plan.setS0(p.getS0());
            plan.setFR0(p.getFR0());
            plan.setQRs0(p.getQRs0());
            plan.setQRss0(p.getQRss0());
            plan.setQRg0(p.getQRg0());

            List<Map> stations = stationService.selectChildStationByStcd(plan.getStcd());
            List<String> stcdId = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                stcdId.add(String.valueOf(stations.get(i).get("stcd")));
            }
            List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, forecastTime, affectTime);
            List<BigDecimal> rainfallArr = new ArrayList<>();
            List<String> timeArr = new ArrayList<>();
            BigDecimal rainfallMax = NumberConst.ZERO;
            for (int i = 0; i < rainfalls.size(); i++) {
                BigDecimal r = rainfalls.get(i).getRainfall().setScale(2, NumberConst.MODE);
                String t = rainfalls.get(i).getDate().substring(0, 16);
                rainfallArr.add(r);
                timeArr.add(t);
                if( NumberUtil.gt(r, rainfallMax) ){
                    rainfallMax = r;
                }
            }
            map.put("rainfallMax", rainfallMax.multiply(new BigDecimal(2)).intValue()+1);
            map.put("timeArr", timeArr);
            map.put("rainfallArr", rainfallArr);
            //
            List<River> rivers = riverService.selectRiverRange(plan.getStcd(), forecastTime, affectTime);
            List<BigDecimal> riverArr = new ArrayList<>();
            BigDecimal riverMax = NumberConst.ZERO;
            for (int i = 0; i < rivers.size(); i++) {
                BigDecimal r = rivers.get(i).getZ().setScale(2, NumberConst.MODE);
                riverArr.add(r);
                if( NumberUtil.gt(r, riverMax) ){
                    riverMax = r;
                }
            }
            map.put("riverArr", riverArr);

            List<BigDecimal> forcastArr = doCalc(plan, rainfallArr, false);
            for (int i = 0; i < forcastArr.size(); i++) {
                BigDecimal r = forcastArr.get(i).setScale(2, NumberConst.MODE);
                forcastArr.set(i, r);
                if( NumberUtil.gt(r, riverMax) ){
                    riverMax = r;
                }
            }
            map.put("forcastArr", forcastArr);
            map.put("riverMax", riverMax.multiply(new BigDecimal(2)).intValue()+1);
        }
		return "Iframe7";
	}

	private List<BigDecimal> doCalc(Plan plan, List<BigDecimal> rainfallP, boolean needQt){
	    StepCommonUtil.init(plan);
	    StepOneUtil.init(plan);
	    StepTwoUtil.init(plan);
	    StepThreeUtil.init(plan);
	    StepFourUtil.init(plan);
	    StepFiveUtil.init(plan);

	    List<BigDecimal> QTR_List = new ArrayList<>();
        Integer len = rainfallP.size();
        if( NumberUtil.gt(new BigDecimal(plan.getL()), plan.getKE()) ){
            len += plan.getL();
        }else{
            len += plan.getKE().intValue();
        }
        for (int i = 0; i<len; i++){
            QTR_List.add(new BigDecimal(0));
        }
        BigDecimal initQTR = null;
        BigDecimal lastQTR = NumberConst.ZERO;
        for (int i=0; i<rainfallP.size();i++) {

            StepCommonUtil.setP(rainfallP.get(i));

            StepOneUtil.getResult();
//            System.out.println(StepTwoUtil.getEKx());
//            System.out.println(StepTwoUtil.getEKy());
//            System.out.println(StepTwoUtil.getEKz());
//            System.out.println("------");
//            System.out.println(StepTwoUtil.getWUx2());
//            System.out.println(StepTwoUtil.getWLx2());
//            System.out.println(StepTwoUtil.getWDx2());
            StepTwoUtil.getResult();
            StepThreeUtil.getResult();
            StepFourUtil.getResult();

            if( initQTR == null ){
                initQTR = StepFourUtil.QTR;
            }
            if( i<plan.getL() ){
                QTR_List.set(i, initQTR);
            }
            QTR_List.set(plan.getL() + i, StepFourUtil.QTR);
            lastQTR = StepFourUtil.QTR;
            System.out.println("======================"+(i+1)+"======================");
        }
        for(int i=0;i<QTR_List.size();i++){
            if( QTR_List.get(i).intValue() == 0 ){
                QTR_List.set(i, lastQTR);
            }
        }
        if( !needQt ){
            return QTR_List;
        }
        return StepFiveUtil.getQt(QTR_List);

        //System.out.println(StepTwoUtil.getWUx1());
        //System.out.println(StepTwoUtil.getWLx1());
        //System.out.println(StepTwoUtil.getWDx1());
    }


//	private BigDecimal getMaxValue(BigDecimal maxValue){
//		switch ( maxValue.subtract(new BigDecimal(maxValue.intValue())).compareTo(new BigDecimal(0.5)) ){
//			case 1:
//				return new BigDecimal(maxValue.intValue()).add(new BigDecimal(1));
//			case -1:
//				return new BigDecimal(maxValue.intValue()).add(new BigDecimal(0.5));
//			default:
//				return new BigDecimal(maxValue.intValue());
//		}
//	}
}
