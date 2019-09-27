package gov.gz.hydrology.service.common.impl;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.read.Rainfall;
import gov.gz.hydrology.entity.read.River;
import gov.gz.hydrology.service.common.DataService;
import gov.gz.hydrology.service.read.RainfallService;
import gov.gz.hydrology.service.read.RiverService;
import gov.gz.hydrology.service.write.CacheRainfallDailyService;
import gov.gz.hydrology.service.write.CacheRainfallTotalService;
import gov.gz.hydrology.service.write.CacheRiverTimeService;
import gov.gz.hydrology.service.write.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public void rainfallTotal() {
        for (String stcd : CommonConst.STCD_STATION) {
            List<Map> stations = stationService.selectChildStationByStcd(stcd);
            List<String> stcdId = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                stcdId.add(String.valueOf(stations.get(i).get("stcd")));
            }
            List<Rainfall> rainfallTotal = rainfallService.selectRainfallTotal(stcdId);

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
        for (String stcd : CommonConst.STCD_STATION) {
            List<Map> stations = stationService.selectChildStationByStcd(stcd);
            List<String> stcdId = new ArrayList<>();
            for (int i = 0; i < stations.size(); i++) {
                stcdId.add(String.valueOf(stations.get(i).get("stcd")));
            }
            List<Rainfall> rainfallDaily = rainfallService.selectRainfallDaily(stcdId);

            cacheRainfallDailyService.deleteByStcd(stcd);
            List<Rainfall> copyRainfallDaily = new ArrayList<>();
            for (int i = 0; i < rainfallDaily.size(); i++) {
                rainfallDaily.get(i).setStcd(stcd);
                copyRainfallDaily.add(rainfallDaily.get(i));
                if (copyRainfallDaily.size() >= 500 || i == rainfallDaily.size() - 1) {
                    cacheRainfallDailyService.insertBatch(copyRainfallDaily);
                    copyRainfallDaily.clear();
                }
            }
        }
    }

    @Override
    public void riverTime() {
        for (String stcd : CommonConst.STCD_STATION) {
            List<River> riverTime = riverService.selectRiverTime(stcd);

            cacheRiverTimeService.deleteByStcd(stcd);
            List<River> copyRiverTime = new ArrayList<>();
            for (int i = 0; i < riverTime.size(); i++) {
                copyRiverTime.add(riverTime.get(i));
                if (copyRiverTime.size() >= 500 || i == riverTime.size() - 1) {
                    cacheRiverTimeService.insertBatch(copyRiverTime);
                    copyRiverTime.clear();
                }
            }
        }
    }
}
