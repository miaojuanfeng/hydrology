package gov.gz.hydrology.utils;

import gov.gz.hydrology.constant.NumberConst;

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

    private static BigDecimal KE = new BigDecimal("18");

    private static BigDecimal XE = new BigDecimal("0.15");

    private static BigDecimal Pa = new BigDecimal(60);

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
    private static List<BigDecimal> listR = new ArrayList<>();

    private static List<BigDecimal> listQu = new ArrayList<>();

    private static List<BigDecimal> listQTRR = new ArrayList<>();

    private static List<BigDecimal> listQT = new ArrayList<>();

    /**
     * 产流
     * @param listP
     * @return
     */
    public static List getR(List<BigDecimal> listP){
        listR.clear();

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
        for (int i = 0; i < listP.size(); i++){
            R01.add(NumberConst.ZERO);
            R02.add(NumberConst.ZERO);
            R_h.add(NumberConst.ZERO);
        }
        for (int i = 0; i < listP.size(); i++){
            P_sum = P_sum.add(listP.get(i));
            P_h.add(P_sum);
            if( NumberUtil.gt(P_sum, NumberConst.ZERO) ){
                for (int j = 0; j <= PR_num.get(R_num01).intValue(); j++){
//                    if( i == 3 ){
//                        System.out.println(1);
//                    }
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
        listR.add(NumberConst.ZERO);
        for (int i = 1; i < listP.size(); i++){
            listR.add(R_h.get(i).subtract(R_h.get(i-1)));
        }
        return listR;
    }

    /**
     * 汇流
     * 算法有误差，需要核实
     * @param listR
     * @return
     */
    public static List getQTRR(List<BigDecimal> listR){
        listQu.clear();
        listQTRR.clear();

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
        for (int i = 0; i < listR.size() + listQu.size(); i++){
            listQTRR.add(NumberConst.ZERO);
        }
        for (int i = 0; i < listR.size(); i++){
            for (int j = 1; j < listQu.size(); j++){
                System.out.println("i="+i+" j="+j);
                listQTRR.set(i+j-1, listQTRR.get(i+j-1).add(listR.get(i).multiply(listQu.get(j))));
            }
        }
        /**
         * 把多余的数组元素移除
         */
        Iterator<BigDecimal> it = listQTRR.iterator();
        int i = 0;
        while(it.hasNext()){
            it.next();
            if( i++ >= listR.size() + 24 ) {
                it.remove();
            }
        }
        return listQTRR;
    }

    /**
     * 河道汇流演算（分段）
     * @param listQTRR
     * @return
     */
    public static List getQT(List<BigDecimal> listQTRR){
        listQT.clear();

        BigDecimal K = KE;
        BigDecimal T = KE;
        BigDecimal DECIMAL_0_5 = new BigDecimal("0.5");
        /**
         * For i = 0 To sum + K -> QT(i) = 0
         */
        for(int i = 0; i < listR.size() + K.intValue(); i++){
            listQT.add(i, NumberConst.ZERO);
        }
        /**
         * C0 = (0.5 * T - KE * XE) / (KE - KE * XE + 0.5 * T)
         * C1 = (0.5 * T + KE * XE) / (KE - KE * XE + 0.5 * T)
         * C2 = (KE - KE * XE - 0.5 * T) / (KE - KE * XE + 0.5 * T)
         */
        BigDecimal divide = KE.subtract(KE.multiply(XE)).add(DECIMAL_0_5.multiply(T));
        BigDecimal C0 = DECIMAL_0_5.multiply(T).subtract(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal C1 = DECIMAL_0_5.multiply(T).add(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
        BigDecimal C2 = DECIMAL_0_5.multiply(T).subtract(KE.multiply(XE)).divide(divide, NumberConst.DIGIT, NumberConst.MODE);
        /**
         * Dim QS1 As Single
         * Dim QS2 As Single
         * Dim QX1 As Single
         * Dim QX2 As Single
         * QX1 = QTRR(0)
         * For i = 0 To sum
         *   QS1 = QTRR(i)
         *   QS2 = QTRR(i + 1)
         *   For J = 1 To K
         *     QX2 = C0 * QS2 + C1 * QS1 + C2 * QX1
         *     QS1 = QX1
         *     QS2 = QX2
         *     QX1 = QX2
         *   Next
         *   QT(i + K) = QX2
         * Next
         */
        BigDecimal QS1;
        BigDecimal QS2;
        BigDecimal QX1 = listQTRR.get(0);
        BigDecimal QX2 = NumberConst.ZERO;
        for (int i = 0; i < listR.size(); i++){
            QS1 = listQTRR.get(i);
            QS2 = listQTRR.get(i+1);
            for (int j = 1; j <= K.intValue(); j++){
                QX2 = C0.multiply(QS2).add(C1.multiply(QS1)).add(C2.multiply(QX1));
                QS1 = QX1;
                QS2 = QX2;
                QX1 = QX2;
            }
            listQT.set(i + K.intValue(), QX2);
        }
        /**
         * For i = 0 To K - 1
         *   QT(i) = QT(K)
         * Next
         */
        for (int i = 0; i <= K.intValue() - 1; i++){
            listQT.set(i, listQT.get(K.intValue()));
        }
        return listQT;
    }

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
        List<BigDecimal> listP = new ArrayList<>();
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.30"));
//        listP.add(new BigDecimal("0.40"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("2.00"));
//        listP.add(new BigDecimal("0.50"));
//        listP.add(new BigDecimal("0.40"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.20"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("2.60"));
//        listP.add(new BigDecimal("2.80"));
//        listP.add(new BigDecimal("1.80"));
//        listP.add(new BigDecimal("2.30"));
//        listP.add(new BigDecimal("2.80"));
//        listP.add(new BigDecimal("0.90"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.20"));
//        listP.add(new BigDecimal("1.10"));
//        listP.add(new BigDecimal("2.10"));
//        listP.add(new BigDecimal("2.90"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.80"));
//        listP.add(new BigDecimal("2.40"));
//        listP.add(new BigDecimal("2.40"));
//        listP.add(new BigDecimal("1.90"));
//        listP.add(new BigDecimal("1.60"));
//        listP.add(new BigDecimal("1.10"));
//        listP.add(new BigDecimal("1.20"));
//        listP.add(new BigDecimal("2.10"));
//        listP.add(new BigDecimal("1.50"));
//        listP.add(new BigDecimal("0.90"));
//        listP.add(new BigDecimal("0.60"));
//        listP.add(new BigDecimal("0.40"));
//        listP.add(new BigDecimal("0.70"));
//        listP.add(new BigDecimal("1.30"));
//        listP.add(new BigDecimal("1.50"));
//        listP.add(new BigDecimal("0.90"));
//        listP.add(new BigDecimal("0.30"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("0.10"));

        listP.add(new BigDecimal("10"));
        listP.add(new BigDecimal("20"));
        listP.add(new BigDecimal("30"));
        listP.add(new BigDecimal("40"));
        listP.add(new BigDecimal("50"));
        listP.add(new BigDecimal("40"));
        listP.add(new BigDecimal("30"));
        listP.add(new BigDecimal("20"));
        listP.add(new BigDecimal("10"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        listP.add(new BigDecimal("0"));
        List<BigDecimal> listR = getR(listP);
        List<BigDecimal> listQTRR = getQTRR(listR);
        List<BigDecimal> listQT = getQT(listQTRR);
    }
}
