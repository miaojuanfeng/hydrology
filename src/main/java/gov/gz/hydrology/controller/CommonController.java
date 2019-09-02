package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.service.write.StationService;
import gov.gz.hydrology.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("cms/common")
public class CommonController {

    @Autowired
    private StationService stationService;

    @RequestMapping("station")
    @ResponseBody
    public String station(@RequestParam("type") String type) {
        JSONObject retval = new JSONObject();
        JSONArray temp = new JSONArray();
        List<Station> stationList = stationService.getStationByType(type);
        for (Station station : stationList){
            JSONObject t = new JSONObject();
            t.put("userStcd", station.getUserStcd());
            t.put("stname", station.getStname());
            temp.add(t);
        }
        retval.put("code", CommonConst.HTTP_OK);
        retval.put("data", temp);
        return retval.toString();
    }

}
