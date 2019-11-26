package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepFourUtil {

	private static Plan plan;
	
	/**
	 * Qe 单元流域流量
	 */
	public static BigDecimal QTR;

	public static BigDecimal QTRup;

	public static BigDecimal QRSup;
	
	public static BigDecimal QRssup;
	
	public static BigDecimal QRgup;
	
	public static void init(Plan p) {
		plan = p;
		QTR = NumberConst.ZERO;
		QRSup = plan.getQRs0();
		QRssup = plan.getQRss0();
		QRgup = plan.getQRg0();
		QTRup = QRSup.add(QRssup).add(QRgup);
	}
	
	/**
	 * Ft 透水面积
	 * @return
	 */
	public static BigDecimal getFt() {
		// Ft=F*(1-IMP)
		return plan.getF().multiply(NumberConst.ONE.subtract(plan.getIM()));
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
		return (StepThreeUtil.RS.multiply(getFt()).add(StepOneUtil.Rd.multiply(plan.getF()).multiply(plan.getIM()))).divide(plan.getT().multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * Qss 壤中流流量
	 * @return
	 */
	public static BigDecimal getQRss() {
		// QRss=CI*Qssup+(1-CI)*Rss*F/(3.6*Dt)
		return plan.getCI().multiply(QRssup).add(NumberConst.ONE.subtract(plan.getCI()).multiply(StepThreeUtil.RSS).multiply(plan.getF()).divide(plan.getT().multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
	}
	
	/**
	 * Qg 地下径流流量
	 * @return
	 */
	public static BigDecimal getQRg() {
		// Qg=Cg*Qgup+(1-Cg)*Rg*F/(3.6*Dt)
		return plan.getCG().multiply(QRgup).add(NumberConst.ONE.subtract(plan.getCG()).multiply(StepThreeUtil.RG).multiply(plan.getF()).divide(plan.getT().multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
	}
	
	public static void getResult() {
		// Qe = Qs + Qss + Qg
		BigDecimal QRs = getQRs();
		BigDecimal QRss = getQRss();
		BigDecimal QRg = getQRg();
		QTR = QRs.add(QRss).add(QRg);
		QTR = QTRup.multiply(plan.getCS()).add(QTR.multiply(NumberConst.ONE.subtract(plan.getCS()))).setScale(2, NumberConst.MODE);
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
