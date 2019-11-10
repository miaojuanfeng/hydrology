package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;

public class StepFourUtil {
	
	/**
	 * Qe 单元流域流量
	 */
	public static BigDecimal QTR;

	public static BigDecimal QTRup;

	public static BigDecimal QRSup;
	
	public static BigDecimal QRssup;
	
	public static BigDecimal QRgup;
	
	static {
		QTR = NumberConst.ZERO;
		QRSup = NumberConfig.QRs0;
		QRssup = NumberConfig.QRss0;
		QRgup = NumberConfig.QRg0;
		QTRup = QRSup.add(QRssup).add(QRgup);
	}
	
	/**
	 * Ft 透水面积
	 * @return
	 */
	public static BigDecimal getFt() {
		// Ft=F*(1-IMP)
		return NumberConfig.F.multiply(NumberConst.ONE.subtract(NumberConfig.IM));
	}
	
	/**
	 * Qs 地表径流流量
	 * @return
	 */
	public static BigDecimal getQRs() {
		// QRs=(Rs*Ft+Rd*F*IMP)/(3.6*DT)
//		BigDecimal a = StepThreeUtil.RS;
//		BigDecimal b = getFt();
//		BigDecimal c = a.multiply(b);
//		BigDecimal d = StepOneUtil.Rd.multiply(NumberConfig.F).multiply(NumberConfig.IM);
//		BigDecimal e = StepOneUtil.Rd;
		return (StepThreeUtil.RS.multiply(getFt()).add(StepOneUtil.Rd.multiply(NumberConfig.F).multiply(NumberConfig.IM))).divide(NumberConfig.T.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * Qss 壤中流流量
	 * @return
	 */
	public static BigDecimal getQRss() {
		// QRss=CI*Qssup+(1-CI)*Rss*F/(3.6*Dt)
		return NumberConfig.CI.multiply(QRssup).add(NumberConst.ONE.subtract(NumberConfig.CI).multiply(StepThreeUtil.RSS).multiply(NumberConfig.F).divide(NumberConfig.T.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
	}
	
	/**
	 * Qg 地下径流流量
	 * @return
	 */
	public static BigDecimal getQRg() {
		// Qg=Cg*Qgup+(1-Cg)*Rg*F/(3.6*Dt)
		return NumberConfig.CG.multiply(QRgup).add(NumberConst.ONE.subtract(NumberConfig.CG).multiply(StepThreeUtil.RG).multiply(NumberConfig.F).divide(NumberConfig.T.multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
	}
	
	public static void getResult() {
		// Qe = Qs + Qss + Qg
		BigDecimal QRs = getQRs();
		BigDecimal QRss = getQRss();
		BigDecimal QRg = getQRg();
		QTR = QRs.add(QRss).add(QRg);
		QTR = QTRup.multiply(NumberConfig.CS).add(QTR.multiply(NumberConst.ONE.subtract(NumberConfig.CS)));
		QTRup = QTR;
		QRSup = QRs;
		QRssup = QRss;
		QRgup = QRg;

		System.out.println("QRs="+QRs);
		System.out.println("QRss="+QRss);
		System.out.println("QRg="+QRg);
		System.out.println("QTR="+QTR);
	}
	
}
