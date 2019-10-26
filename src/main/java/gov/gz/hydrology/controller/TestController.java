package gov.gz.hydrology.controller;

import gov.gz.hydrology.utils.StepCommonUtil;
import gov.gz.hydrology.utils.StepOneUtil;
import gov.gz.hydrology.utils.StepTwoUtil;

public class TestController {

    public static void main(String[] args){
        StepOneUtil.getResult();
//        System.out.println(StepTwoUtil.getEKx());
        System.out.println(StepTwoUtil.getWUx2());
        System.out.println(StepTwoUtil.getWLx2());
        System.out.println(StepTwoUtil.getWDx2());
        StepTwoUtil.getResult();
        System.out.println(StepTwoUtil.WU);
        System.out.println(StepTwoUtil.WL);
        System.out.println(StepTwoUtil.WD);
        //System.out.println(StepTwoUtil.getWUx1());
        //System.out.println(StepTwoUtil.getWLx1());
        //System.out.println(StepTwoUtil.getWDx1());
    }
}
