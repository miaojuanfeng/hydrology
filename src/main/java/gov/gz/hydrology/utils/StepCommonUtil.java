package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;

public class StepCommonUtil {
	
	/**
	 * P 时刻雨量，从数据库中读取
	 * @return
	 */
	private static BigDecimal getP() {
		return new BigDecimal("0.01");
	}

	/**
	 * E 蒸发量
	 * @return
	 */
	public static BigDecimal getE() {
		/*
			月分取P的月份/天数/小时
		 */
		BigDecimal E = NumberConfig.E.get(9).divide(new BigDecimal(31), NumberConst.DIGIT, NumberConst.MODE).divide(new BigDecimal(24), NumberConst.DIGIT, NumberConst.MODE);
		return E;
	}
	
	/**
	 * Ek 蒸发量
	 * @return
	 */
	public static BigDecimal getEk() {
		// Ek = K * E
		return NumberConfig.K.multiply(getE());
	}
	
	/**
	 * PE 净雨
	 * @return
	 */
	public static BigDecimal getPE() {
		// PE = P - Ek
		return getP().subtract(getEk());
	}
}
