package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepTwoUtil {

	private static Plan plan;
	
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

	public static BigDecimal W;
	public static BigDecimal ES;
	public static BigDecimal EU;
	public static BigDecimal EL;
	public static BigDecimal ED;
	
	public static void init(Plan plan) {
		plan = plan;
		WU = NumberConst.ZERO;
		WL = NumberConst.ZERO;
		WD = NumberConst.ZERO;
		WUup = plan.getWU0();
		WLup = plan.getWL0();
		WDup = plan.getWD0();

		W = NumberConst.ZERO;
		ES = NumberConst.ZERO;
		EU = NumberConst.ZERO;
		EL = NumberConst.ZERO;
		ED = NumberConst.ZERO;
	}
	
	/**
	 * 最终结果
	 * @return
	 */
	public static void getResult() {


//		System.out.println("PEx="+StepTwoUtil.getPEx());
//		System.out.println("WUx1="+StepTwoUtil.getWUx1()+" WUx2="+StepTwoUtil.getWUx2());
//		System.out.println("WLx1="+StepTwoUtil.getWLx1()+" WLx2="+StepTwoUtil.getWLx2());
//		System.out.println("WDx1="+StepTwoUtil.getWDx1()+" WDx2="+StepTwoUtil.getWDx2());
//		System.out.println("PEy="+StepTwoUtil.getPEy());
//		System.out.println("PEz="+StepTwoUtil.getPEz());
//		System.out.println("----------------Two算前----------------");



		BigDecimal PE = StepCommonUtil.getPE();
		// PE > 0  上分支
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			// WUx = WUup + PEx
			BigDecimal WUx = WUup.add(getPEx());
			// WUx > WUM
			if( NumberUtil.gt(WUx, plan.getWUM()) ) {
				// WU = WUM
				WU = plan.getWUM();
				BigDecimal WLx = getWLx1();
				// WLx > WLM
				if( NumberUtil.gt(WLx, plan.getWLM()) ) {
					WL = plan.getWLM();
					BigDecimal WDx = getWDx1();
					// WDx > WDM
					if( NumberUtil.gt(WDx, plan.getWDM()) ) {
						WD = plan.getWDM();
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



			EU = StepCommonUtil.getEk();
			ED = NumberConst.ZERO;
			EL = NumberConst.ZERO;


		// PE <= 0 下分支
		}else {
			// WUx = WUup + PE
			BigDecimal WUx = WUup.add(StepCommonUtil.getPE());
			// WUx > 0
			if( NumberUtil.gt(WUx, NumberConst.ZERO) ) {
				WU = WUx;
				WL = WLup;
				WD = WDup;


				EU = StepCommonUtil.getEk();
				EL = NumberConst.ZERO;
				ED = NumberConst.ZERO;


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


				EU = WUup.add(NumberConfig.getTextP());
				WU = NumberConst.ZERO;
				if (NumberUtil.gt(WLup, plan.getC().multiply(plan.getWLM()))) {
					EL = (StepCommonUtil.getEk().subtract(EU)).multiply(WLup).divide(plan.getWLM(), NumberConst.DIGIT, NumberConst.MODE);
					WL = WLup.subtract(EL);
					ED = NumberConst.ZERO;
					WD = WDup.subtract(ED);
				}else{
					BigDecimal temp = (StepCommonUtil.getEk().subtract(EU)).multiply(plan.getC());
					if( NumberUtil.gt(WLup, temp) ){
						EL = temp;
						WL = WLup.subtract(EL);
						ED = NumberConst.ZERO;
						WD = WDup.subtract(ED);
					}else{
						EL = WLup;
						WL = NumberConst.ZERO;
						ED = temp.subtract(EL);
						WD = WDup.subtract(ED);
					}
				}


			}
		}
		if( NumberUtil.gt(WU, plan.getWUM()) ){
			WU = plan.getWUM();
		}
		if( NumberUtil.gt(WL, plan.getWLM()) ){
			WL = plan.getWLM();
		}
		if( NumberUtil.gt(WD, plan.getWDM()) ){
			WD = plan.getWDM();
		}
		WUup = WU;
		WLup = WL;
		WDup = WD;
		StepOneUtil.Wup = WUup.add(WLup).add(WDup);


		ES = EU.add(EL).add(ED);
		W = WU.add(WL).add(WD);


		System.out.println("WUup="+StepTwoUtil.WUup);
		System.out.println("PE="+StepCommonUtil.getPE());
		System.out.println("WU="+StepTwoUtil.WU);
		System.out.println("WL="+StepTwoUtil.WL);
		System.out.println("WD="+StepTwoUtil.WD);
		System.out.println("W="+StepTwoUtil.W);
		System.out.println("EU="+StepTwoUtil.EU);
		System.out.println("EL="+StepTwoUtil.EL);
		System.out.println("ED="+StepTwoUtil.ED);
		System.out.println("ES="+StepTwoUtil.ES);
		System.out.println("StepOneUtil.Wup="+StepOneUtil.Wup);
//		System.out.println("----------------Two算后----------------");
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
		return getWUx1().subtract(plan.getWUM());
	}
	
	/**
	 * PEz 下层剩余的净雨
	 * @return
	 */
	public static BigDecimal getPEz() {
		// PEz = WLx - WLM
		return getWLx1().subtract(plan.getWLM());
	}
	
	///////////////////下分支代码//////////////////////////
	
	/**
	 * WUx 上层蓄水量
	 * @return
	 */
	public static BigDecimal getWUx2() {
		// WUx = WUup + PE
		return WUup.add(StepCommonUtil.getPE());
	}
	
	/**
	 * WLx 下层蓄水量
	 * @return
	 */
	public static BigDecimal getWLx2() {
		// WLx = WLup + WLup/WLM*EKy
		return WLup.add(WLup.divide(plan.getWLM(), NumberConst.DIGIT, NumberConst.MODE).multiply(getEKy()));
	}
	
	/**
	 * WDx 深层蓄水量
	 * @return
	 */
	public static BigDecimal getWDx2() {
		// WDx = WDup + C * EKz
		return WDup.add(plan.getC().multiply(getEKz()));
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


	/**
	 * new
	 */
	public static BigDecimal getEU(){
		// EU = WUup + P
		return WUup.add(NumberConfig.getTextP());
	}
}
