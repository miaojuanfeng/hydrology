package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;

public class StepFiveUtil {
	
	/**
	 * C0   参数
	 * @return
	 */
	public static BigDecimal getC0() {
		// C0=(0.5*DT-KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = NumberConfig.DT.multiply(new BigDecimal("0.5")).subtract(NumberConfig.KE.multiply(NumberConfig.XE));
		BigDecimal divisor = NumberConfig.DT.multiply(new BigDecimal("0.5")).add(NumberConfig.KE).subtract(NumberConfig.KE.multiply(NumberConfig.XE));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * C1   参数
	 * @return
	 */
	public static BigDecimal getC1() {
		// C1=(0.5*DT+KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = NumberConfig.DT.multiply(new BigDecimal("0.5")).add(NumberConfig.KE.multiply(NumberConfig.XE));
		BigDecimal divisor = NumberConfig.DT.multiply(new BigDecimal("0.5")).add(NumberConfig.KE).subtract(NumberConfig.KE.multiply(NumberConfig.XE));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * C2   参数
	 * @return
	 */
	public static BigDecimal getC2() {
		// C2=(-0.5*DT+KE-KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = NumberConfig.DT.multiply(new BigDecimal("-0.5")).add(NumberConfig.KE).subtract(NumberConfig.KE.multiply(NumberConfig.XE));
		BigDecimal divisor = NumberConfig.DT.multiply(new BigDecimal("0.5")).add(NumberConfig.KE).subtract(NumberConfig.KE.multiply(NumberConfig.XE));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * Qt 预报站（断面）流量
	 * @return
	 */
	public static BigDecimal getQt() {
		// Qt=C0*Qe+C1*Qeup+C2*Qeup
		return getC0().multiply(StepFourUtil.Qe).add(getC1().multiply(StepFourUtil.Qeup)).add(getC2().multiply(StepFourUtil.Qeup));
	}
}
