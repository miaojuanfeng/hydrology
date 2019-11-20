package gov.gz.hydrology.controller;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.utils.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestController {

//    public static void main(String[] args){
////        System.out.println(StepCommonUtil.getPE());
////        System.out.println(StepCommonUtil.getPE());
////        System.out.println(StepCommonUtil.getPE());
//        List<BigDecimal> QTR_List = new ArrayList<>();
//        Integer len = NumberConfig.testP.size();
//        if( NumberUtil.gt(new BigDecimal(NumberConfig.L), NumberConfig.KE) ){
//            len += NumberConfig.L;
//        }else{
//            len += NumberConfig.KE.intValue();
//        }
//        for (int i = 0; i<len; i++){
//            QTR_List.add(new BigDecimal(0));
//        }
//        BigDecimal initQTR = null;
//        BigDecimal lastQTR = NumberConst.ZERO;
//        for (int i=0; i<NumberConfig.testP.size();i++) {
//            NumberConfig.indexP = i;
//
//
//            StepOneUtil.getResult();
////            System.out.println(StepTwoUtil.getEKx());
////            System.out.println(StepTwoUtil.getEKy());
////            System.out.println(StepTwoUtil.getEKz());
////            System.out.println("------");
////            System.out.println(StepTwoUtil.getWUx2());
////            System.out.println(StepTwoUtil.getWLx2());
////            System.out.println(StepTwoUtil.getWDx2());
//            StepTwoUtil.getResult();
//            StepThreeUtil.getResult();
//            StepFourUtil.getResult();
//
//            if( initQTR == null ){
//                initQTR = StepFourUtil.QTR;
//            }
//            if( i<NumberConfig.L ){
//                QTR_List.set(i, initQTR);
//            }
//            QTR_List.set(NumberConfig.L + i, StepFourUtil.QTR);
//            lastQTR = StepFourUtil.QTR;
//            System.out.println("======================"+(i+1)+"======================");
//        }
//        for(int i=0;i<QTR_List.size();i++){
//            if( QTR_List.get(i).intValue() == 0 ){
//                QTR_List.set(i, lastQTR);
//            }
//        }
//        StepFiveUtil.getQt(QTR_List);
//
//        //System.out.println(StepTwoUtil.getWUx1());
//        //System.out.println(StepTwoUtil.getWLx1());
//        //System.out.println(StepTwoUtil.getWDx1());
//    }
}
