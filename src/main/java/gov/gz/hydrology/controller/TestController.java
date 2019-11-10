package gov.gz.hydrology.controller;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.utils.StepCommonUtil;
import gov.gz.hydrology.utils.StepOneUtil;
import gov.gz.hydrology.utils.StepThreeUtil;
import gov.gz.hydrology.utils.StepTwoUtil;

public class TestController {

    public static void main(String[] args){
//        System.out.println(StepCommonUtil.getPE());
//        System.out.println(StepCommonUtil.getPE());
//        System.out.println(StepCommonUtil.getPE());
        for (int i=0; i<NumberConfig.testP.size();i++) {
            NumberConfig.indexP = i;


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



            System.out.println("======================"+(i+1)+"======================");
        }

        //System.out.println(StepTwoUtil.getWUx1());
        //System.out.println(StepTwoUtil.getWLx1());
        //System.out.println(StepTwoUtil.getWDx1());
    }
}
