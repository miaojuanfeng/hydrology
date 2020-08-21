package gov.gz.hydrology.utils;

import java.math.BigDecimal;
import java.util.List;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepCommonUtil {

	private static Plan plan;

	public static void init(Plan p){
		plan = p;
	}

//	/**
//	 * E 蒸发量
//	 * @return
//	 */
//	public static BigDecimal getE() {
//		/*
//			月分取P的月份/天数/小时
//		 */
////		BigDecimal E = NumberConfig.E.get(9).divide(new BigDecimal(31), NumberConst.DIGIT, NumberConst.MODE).divide(new BigDecimal(24), NumberConst.DIGIT, NumberConst.MODE);
////		return E;
//		return new BigDecimal("0.14");
//	}
	
//	/**
//	 * Ek 蒸发量
//	 * @return
//	 */
//	public static BigDecimal getEk() {
//		// Ek = K * E
//		return plan.getK().multiply(getE());
//	}
//
//	/**
//	 * PE 净雨
//	 * @return
//	 */
//	public static BigDecimal getPE(BigDecimal p) {
//		// PE = P - Ek
//		return p.subtract(getEk());
//	}
}
