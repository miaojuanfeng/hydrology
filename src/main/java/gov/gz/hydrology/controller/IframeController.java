package gov.gz.hydrology.controller;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.write.CacheRainfallDailyService;
import gov.gz.hydrology.service.write.CacheRainfallTotalService;
import gov.gz.hydrology.service.write.CacheRiverTimeService;
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

	@Autowired
	private RiverService riverService;

	@Autowired
	private CacheRiverTimeService cacheRiverTimeService;

	@Autowired
	private CacheRainfallDailyService cacheRainfallDailyService;

	@Autowired
	private CacheRainfallTotalService cacheRainfallTotalService;

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


			cacheRainfallTotalService.deleteByStcd(stcd);
			List<Rainfall> copyRainfallTotal = new ArrayList<>();
			for (int i=0;i<rainfallTotal.size();i++){
				rainfallTotal.get(i).setStcd(stcd);
				copyRainfallTotal.add(rainfallTotal.get(i));
				if(copyRainfallTotal.size()>=500 || i==rainfallTotal.size()-1) {
					cacheRainfallTotalService.insertBatch(copyRainfallTotal);
					copyRainfallTotal.clear();
				}
			}


			//
			List<String> stationArr = new ArrayList<>();
			List<BigDecimal> rainfallArr = new ArrayList<>();
			for (int i=0;i<stations.size();i++){
				stationArr.add(String.valueOf(stations.get(i).get("stname")));
				rainfallArr.add(new BigDecimal(0));
				String s = String.valueOf(stations.get(i).get("stcd"));
				for (int j=0;j<rainfallTotal.size();j++){
					if( rainfallTotal.get(j).getStcd().equals(s) ){
						rainfallArr.set(i, rainfallTotal.get(j).getRainfall());
						break;
					}
				}
			}
			map.put("stationArr", stationArr);
			map.put("rainfallArr", rainfallArr);
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
				dateArr.add(rainfallDaily.get(i).getDate());
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
			map.put("timeArr", timeArr);
			map.put("riverArr", riverArr);
			map.put("max", max);
			map.put("min", min);
		}
		return "Iframe"+id;
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
