package gov.gz.hydrology.utils;

import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年08月12日
 */
public class EmpiricalUnitUtil {

    /**
     * 数据
     */
    private static List<BigDecimal> Pa0 = new ArrayList<>();

    private static List<List<BigDecimal>> R0 = new ArrayList<>();

    private static List<List<BigDecimal>> P0 = new ArrayList<>();

    private static List<Integer> PR_num = new ArrayList<>();

    private static Integer Pa_num = 0;

    /**
     * 以下是输出结果
     */
//    private static List<BigDecimal> listR = new ArrayList<>();

    private static List<BigDecimal> listQu = new ArrayList<>();

//    private static List<BigDecimal> listQTRR = new ArrayList<>();
//
//    private static List<BigDecimal> listQT = new ArrayList<>();

    /**
     * 产流
     * @return
     */
    public static void getR(){
        CommonUtil.listR.clear();

        BigDecimal Pa = CommonUtil.plan.getPa();

        Integer R_num01 = 0;
        Integer R_num02 = 0;

        for (int i = 0; i < Pa_num - 1; i++){
            if (NumberUtil.ge(Pa, Pa0.get(i)) && NumberUtil.le(Pa, Pa0.get(i + 1))) {
                R_num01 = i;
                R_num02 = i + 1;
            }
        }

        BigDecimal P_sum = NumberConst.ZERO;
        List<BigDecimal> P_h = new ArrayList<>();
        List<BigDecimal> R01 = new ArrayList<>();
        List<BigDecimal> R02 = new ArrayList<>();
        List<BigDecimal> R_h = new ArrayList<>();
        for (int i = 0; i < CommonUtil.listP.size(); i++){
            R01.add(NumberConst.ZERO);
            R02.add(NumberConst.ZERO);
            R_h.add(NumberConst.ZERO);
        }
        for (int i = 0; i < CommonUtil.listP.size(); i++){
            P_sum = P_sum.add(CommonUtil.listP.get(i));
            P_h.add(P_sum);
            if( NumberUtil.gt(P_sum, NumberConst.ZERO) ){
                for (int j = 0; j <= PR_num.get(R_num01).intValue(); j++){
                    if( NumberUtil.ge(P_sum, P0.get(R_num01).get(j)) && NumberUtil.le(P_sum, P0.get(R_num01).get(j+1)) ){
                        /**
                         * R01(i) = (P_h(i) - P0(R_num01, j)) / (P0(R_num01, j + 1) - P0(R_num01, j)) * (R0(R_num01, j + 1) - R0(R_num01, j)) + R0(R_num01, j)
                         */
                        BigDecimal temp1 = P_sum.subtract(P0.get(R_num01).get(j));
                        BigDecimal temp2 = P0.get(R_num01).get(j+1).subtract(P0.get(R_num01).get(j));
                        BigDecimal temp3 = R0.get(R_num01).get(j+1).subtract(R0.get(R_num01).get(j));
                        BigDecimal temp4 = R0.get(R_num01).get(j);
                        R01.set(i, temp1.divide(temp2, NumberConst.DIGIT, NumberConst.MODE).multiply(temp3).add(temp4));
                        break;
                    }
                }
                for (int j = 0; j <= PR_num.get(R_num01).intValue(); j++) {
                    if( NumberUtil.ge(P_sum, P0.get(R_num02).get(j)) && NumberUtil.le(P_sum, P0.get(R_num02).get(j+1)) ){
                        /**
                         * R02(i) = (P_h(i) - P0(R_num02, j)) / (P0(R_num02, j + 1) - P0(R_num02, j)) * (R0(R_num02, j + 1) - R0(R_num02, j)) + R0(R_num02, j)
                         */
                        BigDecimal temp1 = P_sum.subtract(P0.get(R_num02).get(j));
                        BigDecimal temp2 = P0.get(R_num02).get(j+1).subtract(P0.get(R_num02).get(j));
                        BigDecimal temp3 = R0.get(R_num02).get(j+1).subtract(R0.get(R_num02).get(j));
                        BigDecimal temp4 = R0.get(R_num02).get(j);
                        R02.set(i, temp1.divide(temp2, NumberConst.DIGIT, NumberConst.MODE).multiply(temp3).add(temp4));
                        break;
                    }
                }
                /**
                 * R_h(i) = (Pa - Pa0(R_num01)) / (Pa0(R_num02) - Pa0(R_num01)) * (R02(i) - R01(i)) + R01(i)
                 */
                BigDecimal temp1 = Pa.subtract(Pa0.get(R_num01));
                BigDecimal temp2 = Pa0.get(R_num02).subtract(Pa0.get(R_num01));
                BigDecimal temp3 = R02.get(i).subtract(R01.get(i));
                BigDecimal temp4 = R01.get(i);
                R_h.set(i, temp1.divide(temp2, NumberConst.DIGIT, NumberConst.MODE).multiply(temp3).add(temp4));
            }
        }
        /**
         * For i = 1 To sum -> R(i) = R_h(i) - R_h(i - 1)
         */
        CommonUtil.listR.add(NumberConst.ZERO);
        for (int i = 1; i < CommonUtil.listP.size(); i++){
            CommonUtil.listR.add(R_h.get(i).subtract(R_h.get(i-1)));
        }
    }

    /**
     * 汇流
     * 算法有误差，需要核实
     * @return
     */
    public static void getQTRR(){
        listQu.clear();
        CommonUtil.listQTRR.clear();
        /**
         * 读单位线
         */
        List<BigDecimal> lines = TempUtil.getLine();
        for (BigDecimal line : lines){
            listQu.add(line.divide(new BigDecimal("10")));
        }
        /**
         * 汇流
         * 这里如果sum+24，则会出现下标越界问题
         */
        for (int i = 0; i < CommonUtil.listR.size() + listQu.size(); i++){
            CommonUtil.listQTRR.add(NumberConst.ZERO);
        }
        for (int i = 0; i < CommonUtil.listR.size(); i++){
            for (int j = 1; j < listQu.size(); j++){
                CommonUtil.listQTRR.set(i+j-1, CommonUtil.listQTRR.get(i+j-1).add(CommonUtil.listR.get(i).multiply(listQu.get(j))));
            }
        }
        /**
         * 把多余的数组元素移除
         */
        Iterator<BigDecimal> it = CommonUtil.listQTRR.iterator();
        int i = 0;
        while(it.hasNext()){
            it.next();
            if( i++ >= CommonUtil.listR.size() + 24 ) {
                it.remove();
            }
        }
    }

//    /**
//     * 河道汇流演算（分段）
//     * @return
//     */
//    public static void getQT(){
//        CommonUtil.listQT.clear();
//
//        BigDecimal KE = CommonUtil.plan.getKE();
//        BigDecimal XE = CommonUtil.plan.getXE();
//        BigDecimal K = KE;
//        BigDecimal T = KE;
//        BigDecimal DECIMAL_0_5 = new BigDecimal("0.5");
//        /**
//         * For i = 0 To sum + K -> QT(i) = 0
//         */
//        for(int i = 0; i < CommonUtil.listR.size() + K.intValue(); i++){
//            CommonUtil.listQT.add(i, NumberConst.ZERO);
//        }
//        /**
//         * C0 = (0.5 * T - KE * XE) / (KE - KE * XE + 0.5 * T)
//         * C1 = (0.5 * T + KE * XE) / (KE - KE * XE + 0.5 * T)
//         * C2 = (KE - KE * XE - 0.5 * T) / (KE - KE * XE + 0.5 * T)
//         */
//        BigDecimal divide = KE.subtract(KE.multiply(XE)).add(DECIMAL_0_5.multiply(T));
//        BigDecimal C0 = DECIMAL_0_5.multiply(T).subtract(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
//        BigDecimal C1 = DECIMAL_0_5.multiply(T).add(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
//        BigDecimal C2 = DECIMAL_0_5.multiply(T).subtract(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
//        /**
//         * Dim QS1 As Single
//         * Dim QS2 As Single
//         * Dim QX1 As Single
//         * Dim QX2 As Single
//         * QX1 = QTRR(0)
//         * For i = 0 To sum
//         *   QS1 = QTRR(i)
//         *   QS2 = QTRR(i + 1)
//         *   For J = 1 To K
//         *     QX2 = C0 * QS2 + C1 * QS1 + C2 * QX1
//         *     QS1 = QX1
//         *     QS2 = QX2
//         *     QX1 = QX2
//         *   Next
//         *   QT(i + K) = QX2
//         * Next
//         */
//        BigDecimal QS1;
//        BigDecimal QS2;
//        BigDecimal QX1 = CommonUtil.listQTRR.get(0);
//        BigDecimal QX2 = NumberConst.ZERO;
//        for (int i = 0; i < CommonUtil.listR.size(); i++){
//            QS1 = CommonUtil.listQTRR.get(i);
//            QS2 = CommonUtil.listQTRR.get(i+1);
//            for (int j = 1; j <= K.intValue(); j++){
//                QX2 = C0.multiply(QS2).add(C1.multiply(QS1)).add(C2.multiply(QX1));
//                QS1 = QX1;
//                QS2 = QX2;
//                QX1 = QX2;
//            }
//            CommonUtil.listQT.set(i + K.intValue(), QX2);
//        }
//        /**
//         * For i = 0 To K - 1
//         *   QT(i) = QT(K)
//         * Next
//         */
//        for (int i = 0; i <= K.intValue() - 1; i++){
//            CommonUtil.listQT.set(i, CommonUtil.listQT.get(K.intValue()));
//        }
//    }

    public static void main(String[] args){
        /**
         * 读降雨径流关系线
         */
        for (int i = 0; i <= 60; i+=10){
            Pa0.add(new BigDecimal(i));
            R0.add(TempUtil.getR(i));
            P0.add(TempUtil.getP(i));
            //
            PR_num.add(R0.get(0).size());
        }
        Pa_num = Pa0.size();
        /**
         *
         */
        Plan plan = new Plan();
        plan.setF(new BigDecimal("911"));
        plan.setK(new BigDecimal("0.735"));
        plan.setIM(new BigDecimal("0.044"));
        plan.setWUM(new BigDecimal("10.562"));
        plan.setWLM(new BigDecimal("68.412"));
        plan.setWDM(new BigDecimal("1.042"));
        plan.setB(new BigDecimal("0.5"));
        plan.setC(new BigDecimal("0.199"));
        plan.setKSS(new BigDecimal("0.403"));
        plan.setKG(new BigDecimal("0.297"));
        plan.setSM(new BigDecimal("25.174"));
        plan.setEX(new BigDecimal("1.5"));
        plan.setCI(new BigDecimal("0.112"));
        plan.setCG(new BigDecimal("0.95"));
        plan.setCS(new BigDecimal("0.948"));
        plan.setL(4);
        plan.setT(new BigDecimal("1"));
        plan.setKE(new BigDecimal("18"));
        plan.setXE(new BigDecimal("0.15"));
        plan.setWU0(new BigDecimal("10"));
        plan.setWL0(new BigDecimal("60"));
        plan.setWD0(new BigDecimal("10"));
        plan.setS0(new BigDecimal("6"));
        plan.setFR0(new BigDecimal("0.7"));
        plan.setQRS0(new BigDecimal("3"));
        plan.setQRSS0(new BigDecimal("38"));
        plan.setQRG0(new BigDecimal("7"));
        plan.setPa(new BigDecimal(60));
        CommonUtil.plan = plan;

        List<BigDecimal> listP = new ArrayList<>();
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.30"));
        listP.add(new BigDecimal("0.40"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("2.00"));
        listP.add(new BigDecimal("0.50"));
        listP.add(new BigDecimal("0.40"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("0.20"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("2.60"));
        listP.add(new BigDecimal("2.80"));
        listP.add(new BigDecimal("1.80"));
        listP.add(new BigDecimal("2.30"));
        listP.add(new BigDecimal("2.80"));
        listP.add(new BigDecimal("0.90"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("0.20"));
        listP.add(new BigDecimal("1.10"));
        listP.add(new BigDecimal("2.10"));
        listP.add(new BigDecimal("2.90"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("0.80"));
        listP.add(new BigDecimal("2.40"));
        listP.add(new BigDecimal("2.40"));
        listP.add(new BigDecimal("1.90"));
        listP.add(new BigDecimal("1.60"));
        listP.add(new BigDecimal("1.10"));
        listP.add(new BigDecimal("1.20"));
        listP.add(new BigDecimal("2.10"));
        listP.add(new BigDecimal("1.50"));
        listP.add(new BigDecimal("0.90"));
        listP.add(new BigDecimal("0.60"));
        listP.add(new BigDecimal("0.40"));
        listP.add(new BigDecimal("0.70"));
        listP.add(new BigDecimal("1.30"));
        listP.add(new BigDecimal("1.50"));
        listP.add(new BigDecimal("0.90"));
        listP.add(new BigDecimal("0.30"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("0.10"));

//        listP.add(new BigDecimal("10"));
//        listP.add(new BigDecimal("20"));
//        listP.add(new BigDecimal("30"));
//        listP.add(new BigDecimal("40"));
//        listP.add(new BigDecimal("50"));
//        listP.add(new BigDecimal("40"));
//        listP.add(new BigDecimal("30"));
//        listP.add(new BigDecimal("20"));
//        listP.add(new BigDecimal("10"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
//        listP.add(new BigDecimal("0"));
        CommonUtil.listP = listP;

        getR();
        getQTRR();
        CommonUtil.getQT();
    }
}
