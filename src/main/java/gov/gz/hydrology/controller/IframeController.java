package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.common.CommonService;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.write.*;
import gov.gz.hydrology.utils.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("cms/iframe")
public class IframeController {

    public static List<BigDecimal> FORECAST_STEP_ONE = new ArrayList<>();
    public static List<BigDecimal> FORECAST_STEP_TWO = new ArrayList<>();

	@Autowired
	private StationService stationService;

	@Autowired
	private RainfallService rainfallService;

	@Autowired
	private RiverService riverService;

	@Autowired
    private PlanService planService;

	@Autowired
    private CommonService commonService;

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
            River river = riverService.selectRiverLast(stcd);
            BigDecimal z = river.getZ();
            Integer ava = 0;
            if( rainfallTotal.size() > 0 ) {
                BigDecimal rainfallAva = rainfallSum.divide(new BigDecimal(rainfallTotal.size()), NumberConst.DIGIT, NumberConst.MODE);
                Integer floodDiff = z.add(rainfallAva.divide(new BigDecimal(50), NumberConst.DIGIT, NumberConst.MODE)).subtract(new BigDecimal(station.getJjLine())).intValue();
                if( floodDiff < 1 ){
                    ava = 80;
                } else if (floodDiff < 2) {
                    ava = 60;
                } else {
                    ava = 10;
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
//            List<BigDecimal> forcastArr = new ArrayList<>();
//            forcastArr.add(new BigDecimal("0.1"));
//            forcastArr.add(new BigDecimal("0.2"));
//            forcastArr.add(new BigDecimal("0.3"));
//            forcastArr.add(new BigDecimal("0.4"));
//            map.put("forcastArr", forcastArr);
//            map.put("stationProgress", commonService.stationProgress(stcd, step));
            map.put("rainfallMax", 0);
        }
		return "Iframe"+id;
	}

    public String plusDay(int num,String newDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date  currdate = null;
        try {
            currdate = format.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("现在的日期是：" + currdate);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = format.format(currdate);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }

	@GetMapping("calc")
	public String postCalc(ModelMap map, Plan p, String forecastTime, String affectTime, Integer day, Integer type,
                           @RequestParam(value="step",defaultValue="1",required=false) Integer step) {
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

        ///// 这里要查询一下PlanStation中的Plan
        Plan plan = planService.selectById(p.getId());
        if( plan != null ){
            String stcd = plan.getStcd();
            if( CommonConst.STCD_FENKENG.equals(stcd) ){
                if( step == 1 ){
                    stcd = CommonConst.STCD_NINGDU;
                }else if( step == 2 ){
                    stcd = CommonConst.STCD_SHICHENG;
                }else if( step == 3 ){
                    stcd = CommonConst.STCD_FENKENG;
                }
            }

            Station station = stationService.selectByPrimaryKey(stcd);
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

            List<Map> stations = stationService.selectChildStationByStcd(stcd);
            List<String> stcdId = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                stcdId.add(String.valueOf(stations.get(i).get("stcd")));
            }

//            List<String> stcdId = new ArrayList<>();
//            stcdId.add("62302350");
//            stcdId.add("62321000");
//            stcdId.add("62321010");
//            stcdId.add("62321020");
//            stcdId.add("62321030");
//            stcdId.add("62321045");
//            stcdId.add("62321050");
//            stcdId.add("62321055");
//            stcdId.add("62321065");
//            stcdId.add("62321070");
//            stcdId.add("62321085");
//            stcdId.add("62321100");
//            stcdId.add("62323620");

            List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, plusDay(day, forecastTime), affectTime);
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
            map.put("rainfallMax", rainfallMax.multiply(new BigDecimal("3")).intValue());
            map.put("timeArr", timeArr);
            map.put("rainfallArr", rainfallArr);



//            for(BigDecimal r : rainfallArr){
//                System.out.println(r);
//            }



            //
            List<River> rivers = new ArrayList<>();
            List<BigDecimal> riverArr = new ArrayList<>();
            BigDecimal riverMax = NumberConst.ZERO;
            if( type == 1 ) {
                rivers = riverService.selectRiverQRange(stcd, plusDay(day, forecastTime), affectTime);
                for (int i = 0; i < rivers.size(); i++) {
                    BigDecimal r = rivers.get(i).getQ().setScale(2, NumberConst.MODE);
                    riverArr.add(r);
                    if( NumberUtil.gt(r, riverMax) ){
                        riverMax = r;
                    }
                }
            }else{
                rivers = riverService.selectRiverZRange(stcd, plusDay(day, forecastTime), affectTime);
                for (int i = 0; i < rivers.size(); i++) {
                    BigDecimal r = rivers.get(i).getZ().setScale(2, NumberConst.MODE);
                    riverArr.add(r);
                    if( NumberUtil.gt(r, riverMax) ){
                        riverMax = r;
                    }
                }
            }
            map.put("riverArr", riverArr);

            List<BigDecimal> forecastArr = new ArrayList<>();
            if( !CommonConst.STCD_FENKENG.equals(plan.getStcd()) ) {
                forecastArr = doCalc(plan, rainfallArr, false, null, null);
            }else{
                if( step == 1 ){
                    forecastArr = doCalc(plan, rainfallArr, true, plan.getKE(), plan.getXE());
                    FORECAST_STEP_ONE = forecastArr;
                }else if( step == 2 ){
                    forecastArr = doCalc(plan, rainfallArr, true, plan.getKE(), plan.getXE());
                    FORECAST_STEP_TWO = forecastArr;
                }else if( step == 3 ){
                    forecastArr = doCalc(plan, rainfallArr, false, null, null);
                    //
                    for (int i=0; i<forecastArr.size(); i++){
                        BigDecimal v = FORECAST_STEP_ONE.get(i).add(FORECAST_STEP_TWO.get(i).add(forecastArr.get(i)));
                        forecastArr.set(i, v);
                    }
                }
            }
            for (int i = 0; i < forecastArr.size(); i++) {
                BigDecimal r = forecastArr.get(i).setScale(2, NumberConst.MODE);
                forecastArr.set(i, r);
                if( NumberUtil.gt(r, riverMax) ){
                    riverMax = r;
                }
            }
            map.put("forecastArr", forecastArr);
            map.put("riverMax", riverMax.multiply(new BigDecimal("1.5")).intValue());
            map.put("stationProgress", commonService.stationProgress(plan.getStcd(), step));
        }
		return "Iframe7";
	}

	private List<BigDecimal> doCalc(Plan plan, List<BigDecimal> rainfallP, boolean needQt, BigDecimal KE, BigDecimal XE){
	    StepCommonUtil.init(plan);
	    StepOneUtil.init(plan);
	    StepTwoUtil.init(plan);
	    StepThreeUtil.init(plan);
	    StepFourUtil.init(plan);

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
        return StepFiveUtil.getQt(QTR_List, KE, XE);

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
