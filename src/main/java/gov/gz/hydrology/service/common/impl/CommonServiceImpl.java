package gov.gz.hydrology.service.common.impl;

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
}
