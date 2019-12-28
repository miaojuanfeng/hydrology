package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepOneUtil {

	private static Plan plan;
	
	/**
	 * R 时刻产流量
	 */
	public static BigDecimal R;
	
	/**
	 * Rd 直接径流
	 */
	public static BigDecimal Rd;

	public static BigDecimal Wup;
	
	public static void init(Plan p) {
		plan = p;
		R = NumberConst.ZERO;
		Rd = NumberConst.ZERO;
		Wup = plan.getWU0().add(plan.getWL0()).add(plan.getWD0());
	}
	
	/**
	 * W0  流域初始平均蓄水量
	 * @return
	 */
	public static BigDecimal getW0() {
		// W0 = WU0 + WL0 + WD0
		return plan.getWU0().add(plan.getWL0()).add(plan.getWD0());
	}
	
	/**
	 * Wm 流域平均蓄水容量
	 * @return
	 */
	public static BigDecimal getWm() {
		// Wm = WUM + WLM + WDM
		return plan.getWUM().add(plan.getWLM()).add(plan.getWDM());
	}
	
	/**
	 * Wmm 流域内点最大的点蓄水容量
	 * @return
	 */
	public static BigDecimal getWmm() {
		// Wmm = Wm*(1+B)
		return getWm().multiply(NumberConst.ONE.add(plan.getB()));
	}
	
	/**
	 * A
	 */
	public static BigDecimal getA() {
		// A = Wmm*[1-(1-Wup/Wm)^(1/(B+1))]
		BigDecimal base = NumberConst.ONE.subtract(Wup.divide(getWm(), NumberConst.DIGIT, NumberConst.MODE));
		BigDecimal power = NumberConst.ONE.divide(plan.getB().add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
		return getWmm().multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
	}
	
	/**
	 * R 产流量（径流深）
	 * Rd 直接径流
	 */
	public static void getResult() {

//		System.out.println("A="+StepOneUtil.getA());
//		System.out.println("----------------One算前----------------");


		BigDecimal PE = StepCommonUtil.getPE();
		// PE > 0
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			BigDecimal temp_PE_A = PE.add(getA());
			BigDecimal temp_Wmm = getWmm();
			// PE+A<Wmm
			if( temp_PE_A.compareTo(temp_Wmm) == -1 ) {
				BigDecimal base = NumberConst.ONE.subtract(temp_PE_A.divide(temp_Wmm, NumberConst.DIGIT, NumberConst.MODE));
				BigDecimal power = NumberConst.ONE.add(plan.getB());
				R = PE.add(Wup).subtract(getWm()).add(getWm().multiply(NumberUtil.pow(base, power)));
			// PE+A>=Wmm
			}else {
				// R=PE-(Wm-Wup)
				R = PE.subtract(getWm().subtract(Wup));
			}
		// PE <= 0 ? R = 0
		}else {
			R = NumberConst.ZERO;
		}
		// Rd = PE
		if( PE.compareTo(NumberConst.ZERO) == -1 ){
			Rd = NumberConst.ZERO;
		}else {
			Rd = PE;
		}

//		StepTwoUtil.WUup = NumberConfig.WU0;
//		StepTwoUtil.WLup = NumberConfig.WL0;
//		StepTwoUtil.WDup = NumberConfig.WD0;




//		System.out.println("R="+StepOneUtil.R);
//		System.out.println("Rd="+StepOneUtil.Rd);
//		System.out.println("-----------------");
//		System.out.println("----------------One算后----------------");
	}
}
