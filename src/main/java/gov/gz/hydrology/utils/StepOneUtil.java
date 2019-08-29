package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;

public class StepOneUtil {
	
	/**
	 * R 时刻产流量
	 */
	public static BigDecimal R;
	
	/**
	 * Rd 直接径流
	 */
	public static BigDecimal Rd;
	
	static {
		R = NumberConst.ZERO;
		Rd = NumberConst.ZERO;
	}
	
	/**
	 * W0  流域初始平均蓄水量
	 * @return
	 */
	public static BigDecimal getW0() {
		// W0 = WU0 + WL0 + WD0
		return NumberConfig.WU0.add(NumberConfig.WL0).add(NumberConfig.WD0);
	}
	
	/**
	 * Wm 流域平均蓄水容量
	 * @return
	 */
	public static BigDecimal getWm() {
		// Wm = WUM + WLM + WDM
		return NumberConfig.WUM.add(NumberConfig.WLM).add(NumberConfig.WDM);
	}
	
	/**
	 * Wmm 流域内点最大的点蓄水容量
	 * @return
	 */
	public static BigDecimal getWmm() {
		// Wmm = Wm*(1+B)
		return getWm().multiply(NumberConst.ONE.add(NumberConfig.B));
	}
	
	/**
	 * A
	 */
	public static BigDecimal getA() {
		// A = Wmm*[1-(1-W0/Wm)^(1/B+1)]
		BigDecimal base = NumberConst.ONE.subtract(getW0().divide(getWm(), NumberConst.DIGIT, NumberConst.MODE));
		BigDecimal power = NumberConst.ONE.divide(NumberConfig.B, NumberConst.DIGIT, NumberConst.MODE).add(NumberConst.ONE);
		return getWmm().multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
	}
	
	/**
	 * R 产流量（径流深）
	 * Rd 直接径流
	 */
	public static void getResult() {
		BigDecimal PE = StepCommonUtil.getPE();
		// PE > 0
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			BigDecimal temp_PE_A = PE.add(getA());
			BigDecimal temp_Wmm = getWmm();
			// PE+A<Wmm
			if( temp_PE_A.compareTo(temp_Wmm) == -1 ) {
				BigDecimal base = NumberConst.ONE.subtract(temp_PE_A.divide(temp_Wmm, NumberConst.DIGIT, NumberConst.MODE));
				BigDecimal power = NumberConst.ONE.add(NumberConfig.B);
				R = PE.add(getW0()).subtract(getWm()).add(getWm().multiply(NumberUtil.pow(base, power)));
			// PE+A>=Wmm
			}else {
				// R=PE-(Wm-W0)
				R = PE.subtract(getWm().subtract(getW0()));
			}
		// PE <= 0 ? R = 0
		}else {
			R = NumberConst.ZERO;
		}
		// Rd = PE
		Rd = PE;
	}
}
