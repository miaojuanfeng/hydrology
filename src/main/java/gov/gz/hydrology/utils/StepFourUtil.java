package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;

public class StepFourUtil {
	
	/**
	 * Qe 单元流域流量
	 */
	public static BigDecimal Qe;
	
	public static BigDecimal Qeup;
	
	public static BigDecimal Qssup;
	
	public static BigDecimal Qgup;
	
	static {
		Qe = NumberConst.ZERO;
		Qeup = NumberConst.ZERO;
		Qssup = NumberConst.ZERO;
		Qgup = NumberConst.ZERO;
	}
	
	/**
	 * F 透水面积
	 * @return
	 */
	public static BigDecimal getF() {
		// F=Ft*(1-IMP)
		return NumberConfig.Ft.multiply(NumberConst.ONE.subtract(NumberConfig.IM));
	}
	
	/**
	 * Qs 地表径流流量
	 * @return
	 */
	public static BigDecimal getQs() {
		// Qs=(Rs*F+Rd*Ft*IMP)/(3.6*DT)
		return (StepThreeUtil.Rs.multiply(getF()).add(StepOneUtil.Rd.multiply(NumberConfig.Ft).multiply(NumberConfig.IM))).divide(NumberConfig.DT.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * Qss 壤中流流量
	 * @return
	 */
	public static BigDecimal getQss() {
		// Qss=Cs*Qssup+(1-Cs)*Rss*F/(3.6*Dt)
		Qssup = NumberConfig.CS.multiply(Qssup).add(NumberConst.ONE.subtract(NumberConfig.CS).multiply(StepThreeUtil.Rss).multiply(getF()).divide(NumberConfig.DT.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
		return Qssup;
	}
	
	/**
	 * Qg 地下径流流量
	 * @return
	 */
	public static BigDecimal getQg() {
		// Qg=Cg*Qgup+(1-Cg)*Rg*F/(3.6*Dt)
		Qgup = NumberConfig.CG.multiply(Qgup).add(NumberConst.ONE.subtract(NumberConfig.CG).multiply(StepThreeUtil.Rg).multiply(getF()).divide(NumberConfig.DT.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
		return Qgup;
	}
	
	public static void getResult() {
		// Qe = Qs + Qss + Qg
		Qe = getQs().add(getQss()).add(getQg());
		Qeup = Qe;
	}
	
}
