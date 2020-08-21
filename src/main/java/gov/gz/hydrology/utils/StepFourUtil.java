//package gov.gz.hydrology.utils;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import gov.gz.hydrology.constant.NumberConfig;
//import gov.gz.hydrology.constant.NumberConst;
//import gov.gz.hydrology.entity.write.Plan;
//
//public class StepFourUtil {
//
//	private static Plan plan;
//
//	/**
//	 * Qe 单元流域流量
//	 */
//	public static BigDecimal QTRR;
//
//	public static BigDecimal QTRRup;
//
//	public static BigDecimal QRSup;
//
//	public static BigDecimal QRssup;
//
//	public static BigDecimal QRgup;
//
//	public static void init(Plan p) {
//		plan = p;
//		QTRR = NumberConst.ZERO;
//		QRSup = plan.getQRs0();
//		QRssup = plan.getQRss0();
//		QRgup = plan.getQRg0();
//		QTRRup = QRSup.add(QRssup).add(QRgup);
//	}
//
//	public static void getQTRR(List<BigDecimal> list) {
//		/**
//		 * Ft=F*(1-IMP)
//		 * QRs=(Rs*Ft+Rd*F*IMP)/(3.6*DT)
//		 * QRss=CI*Qssup+(1-CI)*Rss*F/(3.6*Dt)
//		 * Qg=Cg*Qgup+(1-Cg)*Rg*F/(3.6*Dt)
//		 * Qe = Qs + Qss + Qg
//		 */
//		BigDecimal Ft = plan.getF().multiply(NumberConst.ONE.subtract(plan.getIM()));
//		BigDecimal QRs = (StepThreeUtil.RS.multiply(Ft).add(StepOneUtil.Rd.multiply(plan.getF()).multiply(plan.getIM()))).divide(plan.getT().multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE);
//		BigDecimal QRss = plan.getCI().multiply(QRssup).add(NumberConst.ONE.subtract(plan.getCI()).multiply(StepThreeUtil.RSS).multiply(plan.getF()).divide(plan.getT().multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
//		BigDecimal QRg = plan.getCG().multiply(QRgup).add(NumberConst.ONE.subtract(plan.getCG()).multiply(StepThreeUtil.RG).multiply(plan.getF()).divide(plan.getT().multiply(new BigDecimal("3.6")), NumberConst.DIGIT, NumberConst.MODE));
//		QTRR = QRs.add(QRss).add(QRg);
//		QTRR = QTRRup.multiply(plan.getCS()).add(QTRR.multiply(NumberConst.ONE.subtract(plan.getCS())));
//		QTRRup = QTRR;
//		QRSup = QRs;
//		QRssup = QRss;
//		QRgup = QRg;
//
////		System.out.println("QRs="+QRs);
////		System.out.println("QRss="+QRss);
////		System.out.println("QRg="+QRg);
////		System.out.println("QTR="+QTR);
//	}
//
//}
