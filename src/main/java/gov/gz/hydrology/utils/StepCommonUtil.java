package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;

public class StepCommonUtil {
	
	/**
	 * P 时刻雨量，从数据库中读取
	 * @return
	 */
	private static BigDecimal getP() {
		return new BigDecimal("0.1");
	}
	
	/**
	 * E 蒸发量
	 * @return
	 */
	private static BigDecimal getE() {
		return new BigDecimal("0.1");
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
