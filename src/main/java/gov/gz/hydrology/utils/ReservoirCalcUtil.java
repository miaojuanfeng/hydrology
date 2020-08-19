package gov.gz.hydrology.utils;

import gov.gz.hydrology.constant.CommonConst;
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

    /**
     * 以下是输出结果
     */
    private static List<BigDecimal> listR = new ArrayList<>();

    private static List<BigDecimal> listQu = new ArrayList<>();

    private static List<BigDecimal> listQTRR = new ArrayList<>();

    private static List<BigDecimal> listQT = new ArrayList<>();

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
     * @param listP
     * @return
     */
    public static List getR(List<BigDecimal> listP){
        listR.clear();

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
     * 算法有误差，需要核实
     * @param listR
     * @return
     */
    public static List getQTRR(List<BigDecimal> listR){
        listQu.clear();
        listQTRR.clear();

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
        for(int i = 0; i < listR.size() + listQu.size(); i++){
            listQTRR.add(i, NumberConst.ZERO);
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
        for(int i = 0; i < listR.size(); i++){
            for(int j = 1; j <= 50; j++){
                BigDecimal temp1 = listQTRR.get(i + j - 1);
                BigDecimal temp2 = listR.get(i).multiply(listQu.get(j)).multiply(Area);
                listQTRR.set(i + j - 1, temp1.add(temp2));
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
     * 算法有误差，需要核实
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

    /**
     * 调洪演算,目的在于通过QTRR()算出出库流量过程OQ()和水位过程Z()
     */
    public static void getResult(List<BigDecimal> listR, List<BigDecimal> listQTRR){
        // 预见期水位过程
        List<BigDecimal> Z = new ArrayList<>();
        // 预见期库容过程
        List<BigDecimal> W = new ArrayList<>();
        // 预见期出库过程
        List<BigDecimal> OQ = new ArrayList<>();
        // 汛限水位
        BigDecimal Z_lim;
        // 汛限水位对应的蓄水量
        BigDecimal W_lim;
        // 比汛限水位低0.5m的水位
        BigDecimal Z00;
        // 比汛限水位低0.5m的蓄水量
        BigDecimal W00;
        //
        BigDecimal Temp_W;
        BigDecimal Temp_Z;
        BigDecimal Temp_OQ;
        // 入库洪峰
        BigDecimal maxIQ;
        // 入库洪峰所在位置
        Integer maxIQ_xiuhao;
        // 最大泄流量
        BigDecimal maxOQ;

        /**
         * 初始化数据
         */
        for (int i = 0; i < listR.size(); i++){
            Z.add(NumberConst.ZERO);
            OQ.add(NumberConst.ZERO);
            W.add(NumberConst.ZERO);
        }
        /**
         * 取初始数据
         */
        List<BigDecimal> Z0 = new ArrayList<>();
        List<BigDecimal> HCOQ = new ArrayList<>();
        TempUtil.getXL(Z0, HCOQ);
        List<BigDecimal> Z_CUR = new ArrayList<>();
        List<BigDecimal> V_CUR = new ArrayList<>();
        TempUtil.getKR(Z_CUR, V_CUR);
        /**
         * 读取起调水位，从实时数据库读取  ST_RSVR_R，预报时间的水位    字段RZ
         */
        Z.set(0, new BigDecimal("240"));
        W.set(0, CommonUtil.diffVal(Z.get(0), Z_CUR, V_CUR));
        /**
         * 读取初始出库流量，从实时数据库读取  ST_RSVR_R，预报时间往前最近的一个出库流量   字段OTQ
         */
        OQ.set(0, new BigDecimal("20"));
        Temp_OQ = OQ.get(0);
        /**
         * 读取汛限水位，从实时数据库的汛期水位表 ST_RSVRFSR_B 读取   字段FSLTDZ      BGMD EDMD两个字段为开始结束时间，根据这两个时间来读汛限水位
         */
        Z_lim = new BigDecimal("242");
        W_lim = CommonUtil.diffVal(Z_lim, Z_CUR, V_CUR);
        Z00 = Z_lim.subtract(new BigDecimal("0.5"));
        W00 = CommonUtil.diffVal(Z00, Z_CUR, V_CUR);
        /**
         * 找到入库流量洪峰所在位置
         */
        maxIQ = listQTRR.get(0);
        for (int i = 1; i < listR.size(); i++){
            if ( NumberUtil.gt(listQTRR.get(i), maxIQ) ){
                maxIQ = listQTRR.get(i);
                maxIQ_xiuhao = i;
            }
        }
        /**
         * 调洪演算
         */
        for (int i = 0; i < listR.size() - 1; i++){
            /**
             * Temp_W = W(i) + (QTRR(i) + QTRR(i + 1)) * 0.0018 * INTV - OQ(i) * 0.0036 * INTV
             */
            Temp_W = W.get(i).add(temp1(listQTRR, i)).subtract(temp2(OQ, i));
            if( NumberUtil.gt(Temp_W, W_lim) ){
                // OQ(i) = (W(i) + (QTRR(i + 1) + QTRR(i)) * 0.0018 * INTV - W00) / (0.0036 * INTV)
                OQ.set(i, W.get(i).add(temp1(listQTRR, i)).subtract(W00).divide(temp3(), NumberConst.DIGIT, NumberConst.MODE));
                if( NumberUtil.gt(OQ.get(i), maxIQ) ){
                    OQ.set(i, maxIQ);
                }
                //OQ.set(i, new BigDecimal(OQ.get(i).intValue()));
                Temp_W = W.get(i).add(temp1(listQTRR, i)).subtract(temp2(OQ, i));
            }
            if( NumberUtil.lt(Temp_W, W_lim) ){
                // OQ(i) = (W(i) + (QTRR(i + 1) + QTRR(i)) * 0.0018 * INTV - W00) / (0.0036 * INTV)
                OQ.set(i, W.get(i).add(temp1(listQTRR, i)).subtract(W00).divide(temp3(), NumberConst.DIGIT, NumberConst.MODE));
                if( NumberUtil.gt(OQ.get(i), maxIQ) ){
                    OQ.set(i, maxIQ);
                }
                //OQ.set(i, new BigDecimal(OQ.get(i).intValue()));
                Temp_W = W.get(i).add(temp1(listQTRR, i)).subtract(temp2(OQ, i));
                if( NumberUtil.le(OQ.get(i), NumberConst.ZERO) ){
                    OQ.set(i, Temp_OQ);
                    Temp_W = W.get(i).add(temp1(listQTRR, i)).subtract(temp2(OQ, i));
                }
            }
            /**
             * Temp_Z = chazhi(W(i), CUR_sum, V_CUR, Z_CUR)
             */
            Temp_Z = CommonUtil.diffVal(W.get(i), V_CUR, Z_CUR);
            maxOQ = CommonUtil.diffVal(Temp_Z, Z0, HCOQ);

            if( NumberUtil.gt(OQ.get(i), maxOQ) ){
                OQ.set(i, maxOQ);
                Temp_W = W.get(i).add(temp1(listQTRR, i)).subtract(temp2(OQ, i));
            }

            Temp_Z = CommonUtil.diffVal(Temp_W, V_CUR, Z_CUR);
            W.set(i+1, Temp_W);
            Z.set(i+1, Temp_Z);
            OQ.set(i+1, OQ.get(i));
            Temp_OQ = OQ.get(i+1);
        }
    }

    private static BigDecimal temp1(List<BigDecimal> listQTRR, Integer i){
        // return (QTRR(i) + QTRR(i + 1)) * 0.0018 * INTV;
        BigDecimal DECIMAL_0_0018 = new BigDecimal("0.0018");
        return INTV.multiply(DECIMAL_0_0018).multiply(listQTRR.get(i).add(listQTRR.get(i+1)));
    }

    private static BigDecimal temp2(List<BigDecimal> OQ, Integer i){
        // return OQ(i) * 0.0036 * INTV;
        BigDecimal DECIMAL_0_0036 = new BigDecimal("0.0036");
        return INTV.multiply(DECIMAL_0_0036).multiply(OQ.get(i));
    }

    private static BigDecimal temp3(){
        // return 0.0036 * INTV;
        BigDecimal DECIMAL_0_0036 = new BigDecimal("0.0036");
        return INTV.multiply(DECIMAL_0_0036);
    }

    public static void main(String[] args){
        List<BigDecimal> listP = new ArrayList<>();
        listP.add(new BigDecimal("0.00"));
        listP.add(new BigDecimal("10.00"));
        listP.add(new BigDecimal("20.00"));
        listP.add(new BigDecimal("30.00"));
        listP.add(new BigDecimal("50.00"));
        listP.add(new BigDecimal("100.00"));
        listP.add(new BigDecimal("80.00"));
        listP.add(new BigDecimal("70.00"));
        listP.add(new BigDecimal("60.00"));
        listP.add(new BigDecimal("50.00"));
        listP.add(new BigDecimal("4.00"));
        listP.add(new BigDecimal("3.00"));
        listP.add(new BigDecimal("2.00"));
        listP.add(new BigDecimal("1.00"));
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
        List<BigDecimal> listR = getR(listP);
        List<BigDecimal> listQTRR = getQTRR(listR);
        List<BigDecimal> ListQT = getQT(listQTRR);
        getResult(listR, listQTRR);
    }
}
