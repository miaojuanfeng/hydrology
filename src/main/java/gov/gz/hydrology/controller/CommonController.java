package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.Warning;
import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.service.write.WarningService;
import gov.gz.hydrology.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("cms/common")
public class CommonController {

    @Autowired
    private StationService stationService;

    @Autowired
    private WarningService warningService;

    @RequestMapping("station")
    @ResponseBody
    public String station(@RequestParam("type") String type) {
        JSONObject retval = new JSONObject();
        JSONArray temp = new JSONArray();
        List<Station> stationList = stationService.selectStationByType(type);
        for (Station station : stationList){
            JSONObject t = new JSONObject();
            t.put("stcd", station.getStcd());
            t.put("stname", station.getStname());
            temp.add(t);
        }
        retval.put("code", CommonConst.HTTP_OK);
        retval.put("data", temp);
        return retval.toString();
    }

    @RequestMapping("warning")
    @ResponseBody
    public String warning(@RequestParam(value="stcd",required=false) String stcd) {
        JSONObject retval = new JSONObject();
        JSONArray temp = new JSONArray();
        List<Warning> warningList = warningService.selectWarning(stcd);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Warning warning : warningList){
            JSONObject t = new JSONObject();
            t.put("stname", warning.getStation().getStname());
            t.put("z", warning.getZ());
            t.put("tm", fmt.format(warning.getTm()));
            if( warning.getType().equals(CommonConst.TYPE_JB_LINE) ){
                t.put("type", "加报");
            }else if( warning.getType().equals(CommonConst.TYPE_JJ_LINE) ){
                t.put("type", "警戒");
            }
            temp.add(t);
        }
        retval.put("code", CommonConst.HTTP_OK);
        retval.put("data", temp);
        return retval.toString();
    }
}
