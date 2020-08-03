package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.read.Zq;
import gov.gz.hydrology.entity.read2.Forecast;
import gov.gz.hydrology.entity.write.Grid;
import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.User;
import gov.gz.hydrology.service.common.CommonService;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.read.ZqService;
import gov.gz.hydrology.service.read2.ForecastService;
import gov.gz.hydrology.service.write.*;
import gov.gz.hydrology.utils.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private UserService userService;

	@Autowired
	private StationService stationService;

	@Autowired
    private GridService gridService;

	@Autowired
	private RainfallService rainfallService;

	@Autowired
	private RiverService riverService;

	@Autowired
    private PlanService planService;

	@Autowired
    private CommonService commonService;

	@Autowired
    private ZqService zqService;

	@Autowired
    private ForecastService forecastService;

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
                Integer floodDiff = new BigDecimal(station.getJjLine()).subtract(z).subtract(rainfallAva.divide(new BigDecimal(30), NumberConst.DIGIT, NumberConst.MODE)).intValue();
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
			BigDecimal max = NumberConst.ZERO;
            BigDecimal min = NumberConst.ZERO;
			for (int i=0;i<riverTime.size();i++){
				String time = format.format(riverTime.get(i).getTm());
//				if( time.endsWith(":00") ) {
					timeArr.add(time);
//				}else{
//					timeArr.add("");
//				}
                BigDecimal z = riverTime.get(i).getZ();
				riverArr.add(z);
				if( NumberUtil.lt(max, z) ){
					max = z;
				}
				if( NumberUtil.gt(min, z) || NumberUtil.et(min, NumberConst.ZERO) ){
					min = z;
				}
			}
//			if( max < station.getJjLine() ){
//				max = station.getJjLine() + 1;
//			}
//			if( min > station.getJbLine() ){
//				min = station.getJbLine();
//			}
            BigDecimal jbLine = new BigDecimal(station.getJbLine());
            BigDecimal jjLine = new BigDecimal(station.getJjLine());
            if( NumberUtil.lt(max, jjLine) ){
			    max = jjLine;
            }
			map.put("timeArr", timeArr);
			map.put("riverArr", riverArr);
			map.put("max", max.add(NumberConst.ONE).intValue());
			map.put("min", min.intValue()-1);
			map.put("jbLine", jbLine);
			map.put("jjLine", jjLine);
		}else if( id == 7 ){
//            List<BigDecimal> forcastArr = new ArrayList<>();
//            forcastArr.add(new BigDecimal("0.1"));
//            forcastArr.add(new BigDecimal("0.2"));
//            forcastArr.add(new BigDecimal("0.3"));
//            forcastArr.add(new BigDecimal("0.4"));
//            map.put("forcastArr", forcastArr);
//            map.put("stationProgress", commonService.stationProgress(stcd, step));
            map.put("rainfallMax", 0);
            map.put("riverMax", 0);
            map.put("riverMin", 0);
            map.put("jjLine", 0);
        }
		return "Iframe"+id;
	}

    public String plusDay(Integer num, String newDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currdate = null;
        try {
            currdate = format.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(currdate);
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = format.format(currdate);
        return enddate;
    }

	@GetMapping("calc")
	public String postCalc(HttpServletRequest request, ModelMap map, Plan p, String forecastTime, String affectTime, Integer day, Integer type, Integer unitname,
                           @RequestParam(value="step",defaultValue="1",required=false) Integer step) {
	    JSONObject retval = new JSONObject();

        Plan plan = null;
        if( CommonConst.STCD_FENKENG.equals(p.getStcd().trim()) ){
            if( step == 1 ){
                plan = planService.selectChildPlan(p.getId(), CommonConst.STCD_NINGDU);
            }else if( step == 2 ){
                plan = planService.selectChildPlan(p.getId(), CommonConst.STCD_SHICHENG);
            }else{
                plan = planService.selectById(p.getId());
            }
        }else{
            plan = planService.selectById(p.getId());
        }
        if( plan != null ){
            String stcd = plan.getStcd();

            Station station = stationService.selectByPrimaryKey(stcd);
            map.put("station", station);
            if( type == 2 ) {
                map.put("jjLine", station.getJjLine());
            }else{
                map.put("jjLine", -10);
            }

            plan.setSM(p.getSM());
            plan.setCI(p.getCI());
            plan.setCS(p.getCS());
            plan.setL(p.getL());
            if( p.getKE() != null ) {
                plan.setKE(p.getKE());
            }
            if( p.getXE() != null ) {
                plan.setXE(p.getXE());
            }

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




            /**
             * 预测雨量数据
             */
            Map<String, BigDecimal> forecastMap = new TreeMap<>();
            String startDay = forecastTime;
            String endDay = DateUtil.date2str(DateUtil.addDay(DateUtil.str2date(forecastTime, "yyyy-MM-dd HH:mm:ss"), day), "yyyy-MM-dd HH:mm:ss");
            // 实测雨量
            if( unitname == 0 ) {
                List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, endDay, startDay);
                for (Rainfall rainfall : rainfalls) {
                    forecastMap.put(rainfall.getDate(), rainfall.getRainfall());
                }

                Date sDay = DateUtil.str2date(startDay, "yyyy-MM-dd HH:mm:ss");
                Date eDay = DateUtil.str2date(endDay, "yyyy-MM-dd HH:mm:ss");
                Date iDay = eDay;
                while (!iDay.before(sDay)) {
                    String key = DateUtil.date2str(iDay, "yyyy-MM-dd HH:mm:ss");
                    if( !forecastMap.containsKey(key) ){
                        forecastMap.put(key, NumberConst.ZERO);
                    }
                    iDay = DateUtil.addHour(iDay, -1);
                }
//                int a = 1;
                // 欧洲台或日本台
            }else{
                List<Grid> gridList = gridService.selectByStcd(stcd);
                List<String> gridId = new ArrayList<>();
                for (Grid grid : gridList){
                    gridId.add(grid.getGridId());
                }

                String fymdh = DateUtil.date2str(DateUtil.addDay(new Date(), -1)) + " 20:00:00";
                List<Forecast> forecastList = forecastService.selectForecast(gridId, fymdh, unitname, startDay, endDay);

                if( forecastList.size() > 0 ) {
                    for (Forecast forecast : forecastList) {
                        forecastMap.put(DateUtil.date2str(forecast.getYmdh(), "yyyy-MM-dd HH:mm:ss"), forecast.getRn());
                    }

                    Date sDay = DateUtil.str2date(startDay, "yyyy-MM-dd HH:mm:ss");
                    Date eDay = DateUtil.str2date(endDay, "yyyy-MM-dd HH:mm:ss");
                    Date iDay = eDay;
                    BigDecimal n = forecastList.get(forecastList.size()-1).getRn();
//                    Date d = forecastList.get(forecastList.size()-1).getYmdh();
                    Integer c = 0;
                    while (!iDay.before(sDay)) {
                        String key = DateUtil.date2str(iDay, "yyyy-MM-dd HH:mm:ss");
                        if( forecastMap.containsKey(key) ){
                            n = forecastMap.get(key);
                            c = 0;
                        }else{
                            c++;
                            if( c >= 3 ){
                                n = NumberConst.ZERO;
                            }
                        }
                        if( iDay.after(forecastList.get(forecastList.size()-1).getYmdh()) ){
                            n = NumberConst.ZERO;
                        }
                        forecastMap.put(key, n);
                        iDay = DateUtil.addHour(iDay, -1);
                    }
//                    Integer a = 1;
                }
            }

//            List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, plusDay(day, forecastTime), affectTime);
            List<Rainfall> rainfalls = rainfallService.selectRainfallRange(stcdId, forecastTime, affectTime);

            // 拼接数据
            for(String key : forecastMap.keySet()){
                Rainfall r = new Rainfall();
                r.setDate(key);
                r.setRainfall(forecastMap.get(key));
                rainfalls.add(r);
            }

            List<BigDecimal> rainfallArr = new ArrayList<>();
            List<String> timeArr = new ArrayList<>();
            BigDecimal rainfallMax = NumberConst.ZERO;
//            System.out.println("start");
            for (int i = 0; i < rainfalls.size(); i++) {
                BigDecimal r = rainfalls.get(i).getRainfall().setScale(2, NumberConst.MODE);
                String t = rainfalls.get(i).getDate().substring(0, 16);
                rainfallArr.add(r);
                timeArr.add(t);
                if( NumberUtil.gt(r, rainfallMax) ){
                    rainfallMax = r;
                }
//                System.out.println(r);
            }
//            System.out.println("end");
            map.put("rainfallMax", rainfallMax.multiply(new BigDecimal("3.5")).intValue());
            map.put("timeArr", timeArr);
            map.put("rainfallArr", rainfallArr);



//            for(BigDecimal r : rainfallArr){
//                System.out.println(r);
//            }



            // 没数据不要显示为0
            List<River> rivers = new ArrayList<>();
            List<BigDecimal> riverArr = new ArrayList<>();
            BigDecimal riverMax = new BigDecimal(station.getJjLine());
            BigDecimal riverMin = NumberConst.ZERO;
            if( type == 1 ) {
                rivers = riverService.selectRiverQRange(stcd, plusDay(day, forecastTime), affectTime);
                for (int i = 0; i < rivers.size(); i++) {
                    River r = rivers.get(i);
                    BigDecimal q = r.getQ() != null ? r.getQ().setScale(2, NumberConst.MODE) : NumberConst.ZERO;
                    riverArr.add(q);
                    if( NumberUtil.gt(q, riverMax) ){
                        riverMax = q;
                    }
                    if( NumberUtil.lt(q, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
                        riverMin = q;
                    }
                }
                map.put("forecastText", "流量");
                map.put("forecastUnit", "流量(m³/s)");
                map.put("color", "#FF5722");
            }else{
                rivers = riverService.selectRiverZRange(stcd, plusDay(day, forecastTime), affectTime);
                for (int i = 0; i < rivers.size(); i++) {
                    River r = rivers.get(i);
                    BigDecimal z = r.getZ() != null ? r.getZ().setScale(2, NumberConst.MODE) : NumberConst.ZERO;
                    riverArr.add(z);
                    if( NumberUtil.gt(z, riverMax) ){
                        riverMax = z;
                    }
                    if( NumberUtil.lt(z, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
                        riverMin = z;
                    }
                }
                map.put("forecastText", "水位");
                map.put("forecastUnit", "水位(m)");
                map.put("color", "#009688");
            }
            map.put("riverArr", riverArr);

            List<BigDecimal> forecastArr = new ArrayList<>();
            if( !CommonConst.STCD_FENKENG.equals(p.getStcd().trim()) ) {
                forecastArr = doCalc(plan, rainfallArr, null, null);
            }else{
                if( step == 1 ){
                    forecastArr = doCalc(plan, rainfallArr, plan.getKE(), plan.getXE());
                    FORECAST_STEP_ONE = StepFiveUtil.getQt(forecastArr, plan.getKE(), plan.getXE());
                }else if( step == 2 ){
                    forecastArr = doCalc(plan, rainfallArr, plan.getKE(), plan.getXE());
                    FORECAST_STEP_TWO = StepFiveUtil.getQt(forecastArr, plan.getKE(), plan.getXE());
                }else if( step == 3 ){
                    forecastArr = doCalc(plan, rainfallArr, null, null);
                    //
                    for (int i=0; i<forecastArr.size(); i++){
                        BigDecimal v = FORECAST_STEP_ONE.get(i).add(FORECAST_STEP_TWO.get(i).add(forecastArr.get(i)));
                        forecastArr.set(i, v);
                    }
                }
            }
            for (int i = 0; i < forecastArr.size(); i++) {
                BigDecimal r = forecastArr.get(i).setScale(2, NumberConst.MODE);
                // 差值法
                if( type == 2 ){
                    Zq zqMin = zqService.selectZqMin(plan.getStcd(), r);
                    Zq zqMax = zqService.selectZqMax(plan.getStcd(), r);
                    if( zqMin == null && zqMax != null ){
                        BigDecimal y = zqMax.getY();
                        r = y;
                    }else if( zqMin != null && zqMax == null ){
                        BigDecimal y = zqMin.getY();
                        r = y;
                    }else if( zqMin != null && zqMax != null ){
                        BigDecimal x = r;
                        BigDecimal x1 = zqMin.getX();
                        BigDecimal y1 = zqMin.getY();
                        BigDecimal x2 = zqMax.getX();
                        BigDecimal y2 = zqMax.getY();
                        BigDecimal y = null;
                        if( x2.equals(x1) ) {
                            y = y1;
                        }else{
                            y = y1.add(x.subtract(x1).multiply(y2.subtract(y1)).divide(x2.subtract(x1), NumberConst.DIGIT, NumberConst.MODE)).setScale(2, NumberConst.MODE);
                        }
                        r = y;
                    }else{
                        System.out.println("程序错误");
                    }
                }
                forecastArr.set(i, r);
                if( NumberUtil.gt(r, riverMax) ){
                    riverMax = r;
                }
                if( NumberUtil.lt(r, riverMin) || NumberUtil.et(riverMin, NumberConst.ZERO) ){
                    riverMin = r;
                }
            }
            map.put("forecastArr", forecastArr);
            map.put("riverMax", riverMax.add(riverMax.subtract(riverMin).multiply(new BigDecimal("0.5"))).add(NumberConst.ONE).intValue());
            map.put("riverMin", riverMin.intValue()-1);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommonConst.SESSION_KEY_USER);
        user.setUserLevel(user.getUserLevel()+1);
        userService.addUserLevel(user);
		return "Iframe7";
	}

	private List<BigDecimal> doCalc(Plan plan, List<BigDecimal> rainfallP, BigDecimal KE, BigDecimal XE){
	    StepCommonUtil.init(plan);
	    StepOneUtil.init(plan);
	    StepTwoUtil.init(plan);
	    StepThreeUtil.init(plan);
	    StepFourUtil.init(plan);

	    List<BigDecimal> QTR_List = new ArrayList<>();
        Integer len = rainfallP.size();

        if( KE != null ) {
            if (NumberUtil.gt(new BigDecimal(plan.getL()), KE)) {
                len += plan.getL();
            } else {
                len += KE.intValue();
            }
        }else{
            len += plan.getL();
        }
        for (int i = 0; i<len; i++){
            QTR_List.add(new BigDecimal(-999));
        }
        BigDecimal initQTR = null;
        BigDecimal lastQTR = NumberConst.ZERO;
        for (int i=0; i<rainfallP.size();i++) {

            StepCommonUtil.setP(rainfallP.get(i));

//            System.out.print("p="+rainfallP.get(i)+" ");

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
//            System.out.println("======================"+(i+1)+"======================");
        }
        for(int i=0;i<QTR_List.size();i++){
            if( NumberUtil.et(QTR_List.get(i), new BigDecimal(-999)) ){
                QTR_List.set(i, lastQTR);
            }
        }
        return QTR_List;

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

    @GetMapping("test")
    public void test(){
        List<River> rivers = riverService.selectRiverQRange("62303350", "2018-06-13", "2018-06-03");
        rivers.size();
	}
}
