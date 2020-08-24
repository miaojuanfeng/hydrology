package gov.gz.hydrology.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.entity.write.Plan;
import gov.gz.hydrology.entity.write.Station;
import gov.gz.hydrology.entity.write.Warning;
import gov.gz.hydrology.service.common.CommonService;
import gov.gz.hydrology.service.write.PlanService;
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

    @Autowired
    private PlanService planService;

    @Autowired
    private CommonService commonService;

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

    @RequestMapping("plan")
    @ResponseBody
    public String plan(@RequestParam("stcd") String stcd) {
        JSONObject retval = new JSONObject();
        JSONArray temp = new JSONArray();
        List<Plan> planList = planService.selectPlan(stcd);
        for (Plan plan : planList){
            JSONObject t = new JSONObject();
            t.put("id", plan.getId());
            t.put("name", plan.getName());
            temp.add(t);
        }
        retval.put("code", CommonConst.HTTP_OK);
        retval.put("data", temp);
        return retval.toString();
    }

    @RequestMapping("plan/detail")
    @ResponseBody
    public String planDetail(@RequestParam("id") Integer id) {
        JSONObject retval = new JSONObject();
        JSONObject temp = new JSONObject();
        Plan plan = planService.selectById(id);
        if ( plan != null ){
            temp.put("SM", plan.getSM());
            temp.put("CI", plan.getCI());
            temp.put("CS", plan.getCS());
            temp.put("L", plan.getL());
            temp.put("KE", plan.getKE());
            temp.put("XE", plan.getXE());
            temp.put("WU0", plan.getWU0());
            temp.put("WL0", plan.getWL0());
            temp.put("WD0", plan.getWD0());
            temp.put("S0", plan.getS0());
            temp.put("FR0", plan.getFR0());
            temp.put("QRs0", plan.getQRS0());
            temp.put("QRss0", plan.getQRSS0());
            temp.put("QRg0", plan.getQRG0());
        }
        retval.put("code", CommonConst.HTTP_OK);
        retval.put("data", temp);
        return retval.toString();
    }

    @RequestMapping("plan/child")
    @ResponseBody
    public String planChild(@RequestParam("id") Integer id, @RequestParam("stcd") String stcd) {
        JSONObject retval = new JSONObject();
        JSONObject temp = new JSONObject();
        Plan plan = planService.selectChildPlan(id, stcd);
        if ( plan != null ){
            temp.put("SM", plan.getSM());
            temp.put("CI", plan.getCI());
            temp.put("CS", plan.getCS());
            temp.put("L", plan.getL());
            temp.put("KE", plan.getKE());
            temp.put("XE", plan.getXE());
            temp.put("WU0", plan.getWU0());
            temp.put("WL0", plan.getWL0());
            temp.put("WD0", plan.getWD0());
            temp.put("S0", plan.getS0());
            temp.put("FR0", plan.getFR0());
            temp.put("QRs0", plan.getQRS0());
            temp.put("QRss0", plan.getQRSS0());
            temp.put("QRg0", plan.getQRG0());
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
            t.put("stname", warning.getStname());
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

    @RequestMapping("notice")
    @ResponseBody
    public String notice(@RequestParam(value="stcd",required=false) String stcd) {
        JSONObject retval = new JSONObject();
        JSONObject temp = new JSONObject();
        Integer count = warningService.selectNotice(stcd);
        count = count > 10 ? 10 : count;
        temp.put("count", count);
        retval.put("code", CommonConst.HTTP_OK);
        retval.put("data", temp);
        return retval.toString();
    }

    @RequestMapping("nav")
    @ResponseBody
    public String nav(@RequestParam("stcd") String stcd) {
        return commonService.stationProgress(stcd.trim(), 1);
    }
}
