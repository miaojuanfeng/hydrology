package gov.gz.hydrology.service.common.impl;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.Warning;
import gov.gz.hydrology.service.common.DataService;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.write.*;
import gov.gz.hydrology.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private CacheRainfallTotalService cacheRainfallTotalService;

    @Autowired
    private RainfallService rainfallService;

    @Autowired
    private StationService stationService;

    @Autowired
    private RiverService riverService;

    @Autowired
    private CacheRiverTimeService cacheRiverTimeService;

    @Autowired
    private CacheRainfallDailyService cacheRainfallDailyService;

    @Autowired
    private WarningService warningService;

    @Override
    public void rainfallTotal() {
        Date now = new Date();
        String statDay = null;
        String endDay = null;
        Integer hour = Integer.valueOf(DateUtil.date2str(now, "H"));
        if( hour >= 8 ) {
            statDay = DateUtil.date2str(now, "yyyy-MM-dd 08:00:00");
            endDay = DateUtil.date2str(DateUtil.addDay(now, 1), "yyyy-MM-dd 08:00:00");
        }else{
            statDay = DateUtil.date2str(DateUtil.addDay(now, -1), "yyyy-MM-dd 08:00:00");
            endDay = DateUtil.date2str(now, "yyyy-MM-dd 08:00:00");
        }
        for (String stcd : CommonConst.STCD_STATION) {
            List<Map> stations = stationService.selectChildStationByStcd(stcd);
            List<String> stcdId = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                stcdId.add(String.valueOf(stations.get(i).get("stcd")));
            }
            List<Rainfall> rainfallTotal = rainfallService.selectRainfallTotal(stcdId, statDay, endDay);

            cacheRainfallTotalService.deleteByStcd(stcd);
            List<Rainfall> copyRainfallTotal = new ArrayList<>();
            for (int i = 0; i < rainfallTotal.size(); i++) {
                for (int j = 0; j < stations.size(); j++) {
                    if (rainfallTotal.get(i).getStcd().equals(String.valueOf(stations.get(j).get("stcd")))) {
                        rainfallTotal.get(i).setStname(String.valueOf(stations.get(j).get("stname")));
                        break;
                    }
                }
                rainfallTotal.get(i).setStcd(stcd);
                copyRainfallTotal.add(rainfallTotal.get(i));
                if (copyRainfallTotal.size() >= 500 || i == rainfallTotal.size() - 1) {
                    cacheRainfallTotalService.insertBatch(copyRainfallTotal);
                    copyRainfallTotal.clear();
                }
            }
        }
    }

    @Override
    public void rainfallDaily() {
        Date now = new Date();
        String statDay = null;
        String endDay = null;
        Integer hour = Integer.valueOf(DateUtil.date2str(now, "H"));
        if( hour >= 8 ) {
            statDay = DateUtil.date2str(DateUtil.addDay(now, -5), "yyyy-MM-dd 08:00:00");
            endDay = DateUtil.date2str(DateUtil.addDay(now, 1), "yyyy-MM-dd 08:00:00");
        }else{
            statDay = DateUtil.date2str(DateUtil.addDay(now, -6), "yyyy-MM-dd 08:00:00");
            endDay = DateUtil.date2str(now, "yyyy-MM-dd 08:00:00");
        }
        for (String stcd : CommonConst.STCD_STATION) {
            List<Map> stations = stationService.selectChildStationByStcd(stcd);
            List<String> stcdId = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                stcdId.add(String.valueOf(stations.get(i).get("stcd")));
            }
            List<Rainfall> rainfallDaily = rainfallService.selectRainfallDaily(stcdId, statDay, endDay);

            cacheRainfallDailyService.deleteByStcd(stcd);
            List<Rainfall> copyRainfallDaily = new ArrayList<>();
            Map<String, BigDecimal> rainfallMap = new TreeMap<>();
            for (int i = 0; i < rainfallDaily.size(); i++) {
                Date date = DateUtil.str2date(rainfallDaily.get(i).getDate(), "yyyy-MM-dd HH:mm:ss");
                Date line = DateUtil.str2date(rainfallDaily.get(i).getDate().substring(0, 10) + " 08:00:00", "yyyy-MM-dd HH:mm:ss");
                String index = null;
                if( date.after(line) ){
                    index = DateUtil.date2str(date, "MM/dd");
                }else{
                    index = DateUtil.date2str(DateUtil.addDay(date, -1), "MM/dd");
                }

                BigDecimal r = rainfallMap.get(index);
                if( r == null ){
                    r = NumberConst.ZERO;
                }
                r = r.add(rainfallDaily.get(i).getRainfall());
                rainfallMap.put(index, r);
            }
            for(String key : rainfallMap.keySet()){
                Rainfall rainfall = new Rainfall();
                rainfall.setStcd(stcd);
                rainfall.setDate(key);
                rainfall.setRainfall(rainfallMap.get(key).divide(new BigDecimal(stcdId.size()), NumberConst.DIGIT, NumberConst.MODE));
                copyRainfallDaily.add(rainfall);
            }
            if (copyRainfallDaily.size() >= 0 ) {
                cacheRainfallDailyService.insertBatch(copyRainfallDaily);
                copyRainfallDaily.clear();
            }
        }
    }

    @Override
    public void riverTime() {
        Date now = new Date();
        String statDay = DateUtil.date2str(DateUtil.addDay(now, -3), "yyyy-MM-dd 00:00:00");
        String endDay = DateUtil.date2str(now, "yyyy-MM-dd HH:mm:ss");
        for (String stcd : CommonConst.STCD_STATION) {
            List<River> riverTime = riverService.selectRiverTime(stcd, statDay, endDay);
            Station station = stationService.selectByPrimaryKey(stcd);

            cacheRiverTimeService.deleteByStcd(stcd);
            List<River> copyRiverTime = new ArrayList<>();
            List<Warning> warningList = new ArrayList<>();
            for (int i = 0; i < riverTime.size(); i++) {
                River r = riverTime.get(i);
                copyRiverTime.add(r);
                if (copyRiverTime.size() >= 500 || i == riverTime.size() - 1) {
                    if( copyRiverTime.size() > 0 ) {
                        cacheRiverTimeService.insertBatch(copyRiverTime);
                        copyRiverTime.clear();
                    }
                }
                // 加报
                if( r.getZ().compareTo(new BigDecimal(station.getJbLine())) == 1 ){
                    Warning warning = new Warning();
                    warning.setStcd(stcd);
                    warning.setTm(r.getTm());
                    warning.setZ(r.getZ());
                    warning.setType(CommonConst.TYPE_JB_LINE);
                    warningList.add(warning);
                }
                // 警戒
                if( r.getZ().compareTo(new BigDecimal(station.getJjLine())) == 1 ){
                    Warning warning = new Warning();
                    warning.setStcd(stcd);
                    warning.setTm(r.getTm());
                    warning.setZ(r.getZ());
                    warning.setType(CommonConst.TYPE_JJ_LINE);
                    warningList.add(warning);
                }
                if( warningList.size() >= 100 || i == riverTime.size() - 1){
                    if( warningList.size() > 0 ) {
                        warningService.insertBatch(warningList);
                        warningList.clear();
                    }
                }
            }
        }
    }
}
