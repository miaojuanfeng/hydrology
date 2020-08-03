package gov.gz.hydrology.utils;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.constant.NumberConst;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年07月31日
 */
public class ReservoirCalcUtil {

    private static BigDecimal K = new BigDecimal("0.85");

    private static BigDecimal Kr = new BigDecimal("0.605");

    private static BigDecimal Im = new BigDecimal(60);

    private static BigDecimal Imm = new BigDecimal(270);

    private static BigDecimal Na = new BigDecimal(4);

    private static BigDecimal Nu = new BigDecimal(150);

    private static BigDecimal Kg = new BigDecimal(120);

    private static BigDecimal Ku = new BigDecimal("2.7");

    private static BigDecimal Area = new BigDecimal(412);

    private static BigDecimal INTV = new BigDecimal(1);

    private static BigDecimal KE = new BigDecimal(18);

    private static BigDecimal XE = new BigDecimal("0.15");

    private static BigDecimal Pa = new BigDecimal("44.618");

    private static BigDecimal B = null;

    private static BigDecimal A = null;

    static {
        /**
         * B = Imm / Im
         * B = Round(B, 6)
         */
        B = Imm.divide(Im, NumberConst.DIGIT, NumberConst.MODE);

        /**
         * A = Imm * (1 - (1 - Pa / Im) ^ (1 / B))
         * A = Round(A, 6)
         */
        BigDecimal base = NumberConst.ONE.subtract(Pa.divide(Im, NumberConst.DIGIT, NumberConst.MODE));
        BigDecimal power = NumberConst.ONE.divide(B, NumberConst.DIGIT, NumberConst.MODE);
        A = Imm.multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
    }

    /**
     * 产流
     * @param pList
     * @return
     */
    public static List getR(List<BigDecimal> listP){
        List<BigDecimal> listR = new ArrayList<>();

        for(int i = 0; i < listP.size(); i++){
            BigDecimal p = listP.get(i);
            BigDecimal r;
            if( NumberUtil.lt(p.add(A), Imm) ){
                /**
                 * R(i) = Kr * (P(i) + Pa - Im + Im * (1 - (P(i) + A) / Imm) ^ B)
                 */
                BigDecimal base = NumberConst.ONE.subtract(p.add(A).divide(Imm, NumberConst.DIGIT, NumberConst.MODE));
                BigDecimal power = B;
                r = Kr.multiply(p.add(Pa).subtract(Im).add(Im.multiply(NumberUtil.pow(base, power))));
            }else{
                /**
                 * R(i) = Kr * (P(i) - Im + Pa)
                 */
                r = Kr.multiply(p.subtract(Im).add(Pa));
            }
            if( NumberUtil.lt(r, NumberConst.ZERO) ){
                r = NumberConst.ZERO;
            }
            listR.add(i, r);
        }
        return listR;
    }

    /**
     * 汇流
     * @param rList
     * @return
     */
    public static List getQTRR(List<BigDecimal> rList){
        List<BigDecimal> listQTRR = new ArrayList<>();
        List<BigDecimal> listQu = new ArrayList<>();
        BigDecimal DECIMAL_0_15 = new BigDecimal("0.15");
        BigDecimal DECIMAL_0_5 = new BigDecimal("0.5");
        BigDecimal DECIMAL_0_88 = new BigDecimal("0.88");
        BigDecimal DECIMAL_3 = new BigDecimal("3");
        BigDecimal DECIMAL_3_6 = new BigDecimal("3.6");
        BigDecimal DECIMAL_6 = new BigDecimal("6");

        /**
         * For i = 0 To sum + 24 -> QTRR(i) = 0
         */
        for(int i = 0; i < rList.size()+24; i++){
            listQTRR.add(i, NumberConst.ZERO);
        }
        /**
         * For i = 0 To Nu -> Qu(i) = 0
         */
        for(int i = 0; i < Nu.intValue(); i++){
            listQu.add(i, NumberConst.ZERO);
        }
        /**
         * V = 1
         * E = Exp(1)
         */
        BigDecimal v = NumberConst.ONE;
        BigDecimal e = new BigDecimal(Math.E);
        /**
         * For i = 1 To Na - 1 -> V = V * i
         */
        for(int i = 1; i < Na.intValue()-1; i++){
            v = v.multiply(new BigDecimal(i));
        }
        /**
         * For j = 1 To Nu
         */
        for(int j = 1; j < Nu.intValue(); j++){
            if( j == 1 ){
                /**
                 * Yp = 0.15 / 3.6 / (Kg + 0.5)
                 * Qu(J) = 0.88 / (Ku * 6 * 3.6) * Exp(1) ^ (-J / Ku) * (J / Ku) ^ 3 + 0.15 / 3.6 / (Kg + 0.5)
                 */
                BigDecimal Yp = DECIMAL_0_15.divide(DECIMAL_3_6, NumberConst.DIGIT, NumberConst.MODE).divide(Kg.add(DECIMAL_0_5), NumberConst.DIGIT, NumberConst.MODE);
//                BigDecimal Qu = DECIMAL_0_88
            }else{
                /**
                 * Yp = (Kg - 0.5) * Yp / (Kg + 0.5)
                 * Qu(J) = 0.88 / (Ku * V * 3.6) * Exp(1) ^ (-J / Ku) * (J / Ku) ^ 3 + Yp
                 */

            }
        }
    }

    public static void main(String[] args){
        List<BigDecimal> pList = new ArrayList<>();
        pList.add(new BigDecimal("0"));
        pList.add(new BigDecimal("10"));
        pList.add(new BigDecimal("20"));
        pList.add(new BigDecimal("30"));
        pList.add(new BigDecimal("50"));
        pList.add(new BigDecimal("100"));
        pList.add(new BigDecimal("80"));
        pList.add(new BigDecimal("70"));
        pList.add(new BigDecimal("60"));
        pList.add(new BigDecimal("50"));
        pList.add(new BigDecimal("4"));
        pList.add(new BigDecimal("3"));
        pList.add(new BigDecimal("2"));
        pList.add(new BigDecimal("1"));
        List<BigDecimal> rList = getR(pList);
    }
}
