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
 * @date 2020年07月31日
 */
public class ApiModelUtil {

    private static BigDecimal K = new BigDecimal("0.85");

    private static BigDecimal Kr = new BigDecimal("0.605");

    private static BigDecimal Im = new BigDecimal(60);

    private static BigDecimal Imm = new BigDecimal(270);

    private static BigDecimal Na = new BigDecimal(4);

    private static BigDecimal Nu = new BigDecimal(150);

    private static BigDecimal Kg = new BigDecimal(120);

    private static BigDecimal Ku = new BigDecimal("2.7");

    private static BigDecimal Area = new BigDecimal(412);

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
     * @return
     */
    public static void getR(){
        CommonUtil.listR.clear();

        for(int i = 0; i < CommonUtil.listP.size(); i++){
            BigDecimal p = CommonUtil.listP.get(i);
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
            CommonUtil.listR.add(i, r);
        }
        CommonUtil.listR.add(NumberConst.ZERO);
    }

    /**
     * 汇流
     * @return
     */
    public static void getQTRR(){
        CommonUtil.listQTRR.clear();

        List<BigDecimal> listQu = new ArrayList<>();

        BigDecimal DECIMAL_0_15 = new BigDecimal("0.15");
        BigDecimal DECIMAL_0_5 = new BigDecimal("0.5");
        BigDecimal DECIMAL_0_88 = new BigDecimal("0.88");
        BigDecimal DECIMAL_3 = new BigDecimal("3");
        BigDecimal DECIMAL_3_6 = new BigDecimal("3.6");
        BigDecimal DECIMAL_6 = new BigDecimal("6");

        /**
         * For i = 0 To Nu -> Qu(i) = 0
         */
        for(int i = 0; i <= Nu.intValue(); i++){
            listQu.add(i, NumberConst.ZERO);
        }
        /**
         * For i = 0 To sum + 24 -> QTRR(i) = 0
         * 这里如果sum+24，则会出现下标越界问题
         */
        for(int i = 0; i < CommonUtil.listR.size() + listQu.size(); i++){
            CommonUtil.listQTRR.add(i, NumberConst.ZERO);
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
        for(int i = 1; i <= Na.intValue()-1; i++){
            v = v.multiply(new BigDecimal(i));
        }
        /**
         * For j = 1 To Nu
         */
        BigDecimal Yp = NumberConst.ZERO;
        BigDecimal Qu = NumberConst.ZERO;
        for(int j = 1; j <= Nu.intValue(); j++){
            if( j == 1 ){
                /**
                 * Yp = 0.15 / 3.6 / (Kg + 0.5)
                 * Qu(J) = 0.88 / (Ku * 6 * 3.6) * Exp(1) ^ (-J / Ku) * (J / Ku) ^ 3 + 0.15 / 3.6 / (Kg + 0.5)
                 */
                Yp = DECIMAL_0_15.divide(DECIMAL_3_6.multiply(Kg.add(DECIMAL_0_5)), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp1 = DECIMAL_0_88.divide(Ku.multiply(DECIMAL_6).multiply(DECIMAL_3_6), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp2 = NumberUtil.pow(e, NumberConst.ZERO.subtract(new BigDecimal(j)).divide(Ku, NumberConst.DIGIT, NumberConst.MODE));
                BigDecimal temp3 = NumberUtil.pow(new BigDecimal(j).divide(Ku, NumberConst.DIGIT, NumberConst.MODE), DECIMAL_3);
                BigDecimal temp4 = DECIMAL_0_15.divide(DECIMAL_3_6, NumberConst.DIGIT, NumberConst.MODE).divide(Kg.add(DECIMAL_0_5), NumberConst.DIGIT, NumberConst.MODE);
                Qu = temp1.multiply(temp2).multiply(temp3).add(temp4);
            }else{
                /**
                 * Yp = (Kg - 0.5) * Yp / (Kg + 0.5)
                 * Qu(J) = 0.88 / (Ku * V * 3.6) * Exp(1) ^ (-J / Ku) * (J / Ku) ^ 3 + Yp
                 */
                Yp = Kg.subtract(DECIMAL_0_5).multiply(Yp).divide(Kg.add(DECIMAL_0_5), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp1 = DECIMAL_0_88.divide(Ku.multiply(v).multiply(DECIMAL_3_6), NumberConst.DIGIT, NumberConst.MODE);
                BigDecimal temp2 = NumberUtil.pow(e, NumberConst.ZERO.subtract(new BigDecimal(j)).divide(Ku, NumberConst.DIGIT, NumberConst.MODE));
                BigDecimal temp3 = NumberUtil.pow(new BigDecimal(j).divide(Ku, NumberConst.DIGIT, NumberConst.MODE), DECIMAL_3);
                Qu = temp1.multiply(temp2).multiply(temp3).add(Yp);
            }
            listQu.set(j, Qu);
        }
        /**
         * Yp = 0
         * For i = 0 To Nu -> Yp = Yp + Qu(i)
         */
        Yp = NumberConst.ZERO;
        for(int i = 0; i <= Nu.intValue(); i++){
            Yp = Yp.add(listQu.get(i));
        }
        /**
         * Yp = 1 / (Yp * 3.6)
         */
        Yp = NumberConst.ONE.divide(Yp.multiply(DECIMAL_3_6), NumberConst.DIGIT, NumberConst.MODE);
        /**
         * For i = 0 To Nu -> Qu(i) = Qu(i) * Yp
         */
        for(int i = 0; i <= Nu.intValue(); i++){
            listQu.set(i, listQu.get(i).multiply(Yp));
        }
        /**
         * For i = 0 To sum
         *   For J = 1 To 50
         *      QTRR(i + J - 1) = QTRR(i + J - 1) + R(i) * Qu(J) * Area
         */
        for(int i = 0; i < CommonUtil.listR.size(); i++){
            for(int j = 1; j <= 50; j++){
                BigDecimal temp1 = CommonUtil.listQTRR.get(i + j - 1);
                BigDecimal temp2 = CommonUtil.listR.get(i).multiply(listQu.get(j)).multiply(Area);
                CommonUtil.listQTRR.set(i + j - 1, temp1.add(temp2));
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

    public static void main(String[] args){
        List<BigDecimal> listP = new ArrayList<>();
//        listP.add(new BigDecimal("0.00"));
//        listP.add(new BigDecimal("10.00"));
//        listP.add(new BigDecimal("20.00"));
//        listP.add(new BigDecimal("30.00"));
//        listP.add(new BigDecimal("50.00"));
//        listP.add(new BigDecimal("100.00"));
//        listP.add(new BigDecimal("80.00"));
//        listP.add(new BigDecimal("70.00"));
//        listP.add(new BigDecimal("60.00"));
//        listP.add(new BigDecimal("50.00"));
//        listP.add(new BigDecimal("4.00"));
//        listP.add(new BigDecimal("3.00"));
//        listP.add(new BigDecimal("2.00"));
//        listP.add(new BigDecimal("1.00"));
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

        listP.add(new BigDecimal("20"));
        listP.add(new BigDecimal("40"));
        listP.add(new BigDecimal("60"));
        listP.add(new BigDecimal("80"));
        listP.add(new BigDecimal("100"));
        listP.add(new BigDecimal("80"));
        listP.add(new BigDecimal("60"));
        listP.add(new BigDecimal("40"));
        listP.add(new BigDecimal("20"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        listP.add(new BigDecimal("0.0"));
        CommonUtil.listP = listP;

        getR();
        getQTRR();
        CommonUtil.getQT();
        CommonUtil.getOQ();
    }
}
