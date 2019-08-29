package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;

public class StepTwoUtil {
	
	/**
	 * 上层蓄水量最终结果
	 */
	public static BigDecimal WU;
	
	/**
	 * 下层蓄水量最终结果
	 */
	public static BigDecimal WL;
	
	/**
	 * 深层蓄水量最终结果
	 */
	public static BigDecimal WD;
	
	/**
	 * WUup 上次计算上层蓄水量
	 */
	public static BigDecimal WUup;
	
	/**
	* WLup 上次计算下层蓄水量
	*/
	public static BigDecimal WLup;
	
	/**
	* WDup 上次计算深层蓄水量
	*/
	public static BigDecimal WDup;
	
	static {
		WU = NumberConst.ZERO;
		WL = NumberConst.ZERO;
		WD = NumberConst.ZERO;
		WUup = NumberConst.ZERO;
		WLup = NumberConst.ZERO;
		WDup = NumberConst.ZERO;
	}
	
	/**
	 * 最终结果
	 * @return
	 */
	public static void getResult() {
		BigDecimal PE = StepCommonUtil.getPE();
		// PE > 0  上分支
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			// WUx = WUup + PEx
			BigDecimal WUx = WUup.add(getPEx());
			// WUx > WUM
			if( NumberUtil.gt(WUx, NumberConfig.WUM) ) {
				// WU = WUM
				WU = NumberConfig.WUM;
				BigDecimal WLx = getWLx1();
				// WLx > WLM
				if( NumberUtil.gt(WLx, NumberConfig.WLM) ) {
					WL = NumberConfig.WLM;
					BigDecimal WDx = getWDx1();
					// WDx > WDM
					if( NumberUtil.gt(WDx, NumberConfig.WDM) ) {
						WD = NumberConfig.WDM;
					// WDx <= WDM
					}else {
						WD = WDx;
					}
				// WLx <= WLM
				} else {
					WL = WLx;
					WD = WDup;
				}
			// WUx <= WUM
			} else {
				WU = WUx;
				WL = WLup;
				WD = WDup;
			}
		// PE <= 0 下分支
		}else {
			// WUx = WUup + EKx
			BigDecimal WUx = WUup.add(getEKx());
			// WUx > 0
			if( NumberUtil.gt(WUx, NumberConst.ZERO) ) {
				WU = WUx;
				WL = WLup;
				WD = WDup;
			// WUx <= 0
			}else {
				BigDecimal WLx = getWLx2();
				// WLx > 0
				if( NumberUtil.gt(WLx, NumberConst.ZERO) ) {
					WU = NumberConst.ZERO;
					WL = WLx;
					WD = WDup;
				// WLx <= 0
				}else {
					BigDecimal WDx = getWDx2();
					// WDx > 0
					if( NumberUtil.gt(WDx, NumberConst.ZERO) ) {
						WU = NumberConst.ZERO;
						WL = NumberConst.ZERO;
						WD = WDx;
					// WDx <= 0
					}else {
						WU = NumberConst.ZERO;
						WL = NumberConst.ZERO;
						WD = NumberConst.ZERO;
					}
				}
			}
		}
	}
	
	/**
	 * WUx 上层蓄水量
	 * @return
	 */
	public static BigDecimal getWUx1() {
		// WUx = WUup + PEx
		return WUup.add(getPEx());
	}
	
	/**
	 * WLx 下层蓄水量
	 * @return
	 */
	public static BigDecimal getWLx1() {
		// WLx = WLup + PEy
		return WLup.add(getPEy());
	}
	
	/**
	 * WDx 深层蓄水量
	 * @return
	 */
	public static BigDecimal getWDx1() {
		// WDx = WDup + PEz
		return WDup.add(getPEz());
	}
	
	/**
	 * PEx 产流之后剩下的净雨
	 * @return
	 */
	public static BigDecimal getPEx() {
		// PEx = PE-R
		return StepCommonUtil.getPE().subtract(StepOneUtil.R);
	}
	
	/**
	 * PEy 上层剩余的净雨
	 * @return
	 */
	public static BigDecimal getPEy() {
		// PEy = WUx - WUM
		return getWUx1().subtract(NumberConfig.WUM);
	}
	
	/**
	 * PEz 下层剩余的净雨
	 * @return
	 */
	public static BigDecimal getPEz() {
		// PEz = WLx - WLM
		return getWLx1().subtract(NumberConfig.WLM);
	}
	
	///////////////////下分支代码//////////////////////////
	
	/**
	 * WUx 上层蓄水量
	 * @return
	 */
	public static BigDecimal getWUx2() {
		// WUx = WUup + EKx
		return WUup.add(getEKx());
	}
	
	/**
	 * WLx 下层蓄水量
	 * @return
	 */
	public static BigDecimal getWLx2() {
		// WLx = WLup + WLup/WLM*EKy
		return WLup.add(WLup.divide(NumberConfig.WLM, NumberConst.DIGIT, NumberConst.MODE).multiply(getEKy()));
	}
	
	/**
	 * WDx 深层蓄水量
	 * @return
	 */
	public static BigDecimal getWDx2() {
		// WDx = WDup + C * EKz
		return WDup.add(NumberConfig.C.multiply(getEKz()));
	}
	
	/**
	 * EKx 上层蒸发量
	 * @return
	 */
	public static BigDecimal getEKx() {
		// EKx = PE
		return StepCommonUtil.getPE();
	}
	
	/**
	 * EKy 上层蒸发后，需要下层补充的蒸发量
	 * @return
	 */
	public static BigDecimal getEKy() {
		// EKy = WUx
		return getWUx2();
	}
	
	/**
	 * EKz 下层蒸发后，需要补充的蒸发量
	 * @return
	 */
	public static BigDecimal getEKz() {
		// EKz = WLx
		return getWLx2();
	}
}
