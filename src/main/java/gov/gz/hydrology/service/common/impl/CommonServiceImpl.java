package gov.gz.hydrology.service.common.impl;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.service.common.CommonService;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public String userLevel(Integer level) {
        if( level < 15 ){
            return "预报新手";
        }else if( level < 50 ){
            return "预报精英";
        }else if( level < 100 ){
            return "预报专家";
        }else{
            return "预报大师";
        }
    }

    @Override
    public String levelProgress(Integer level) {
        if( level < 15 ){
            return level + " / 15";
        }else if( level < 50 ){
            return level + " / 50";
        }else if( level < 100 ){
            return level + " / 100";
        }else{
            return level + " / " + level;
        }
    }

    @Override
    public String stationProgress(String stcd) {
        String retval = "";
        if( CommonConst.STCD_NINGDU.equals(stcd) ) {
            retval += "<a class='selected' href='javascript:;'><span>宁都</span></a>";
        }
        if( CommonConst.STCD_SHICHENG.equals(stcd) ) {
            retval += "<a href='javascript:;'><span>宁都</span></a>";
            retval += "<a class='selected' href='javascript:;'><span>石城</span></a>";
        }
        if( CommonConst.STCD_FENKENG.equals(stcd) ) {
            retval += "<a href='javascript:;'><span>宁都</span></a>";
            retval += "<a href='javascript:;'><span>石城</span></a>";
            retval += "<a class='selected' href='javascript:;'><span>汾坑</span></a>";
        }
        return retval;
    }
}
