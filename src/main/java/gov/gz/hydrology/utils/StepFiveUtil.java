package gov.gz.hydrology.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepFiveUtil {

	private static List<BigDecimal> QT_List = new ArrayList<>();

	/**
	 * C0   参数
	 * @return
	 */
	public static BigDecimal getC0(BigDecimal DT, BigDecimal KE, BigDecimal XE) {
		// C0=(0.5*DT-KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = DT.multiply(new BigDecimal("0.5")).subtract(KE.multiply(XE));
		BigDecimal divisor = DT.multiply(new BigDecimal("0.5")).add(KE).subtract(KE.multiply(XE));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * C1   参数
	 * @return
	 */
	public static BigDecimal getC1(BigDecimal DT, BigDecimal KE, BigDecimal XE) {
		// C1=(0.5*DT+KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = DT.multiply(new BigDecimal("0.5")).add(KE.multiply(XE));
		BigDecimal divisor = DT.multiply(new BigDecimal("0.5")).add(KE).subtract(KE.multiply(XE));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}

	/**
	 * C2   参数
	 * @return
	 */
	public static BigDecimal getC2(BigDecimal DT, BigDecimal KE, BigDecimal XE) {
		// C2=(-0.5*DT+KE-KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = DT.multiply(new BigDecimal("-0.5")).add(KE).subtract(KE.multiply(XE));
		BigDecimal divisor = DT.multiply(new BigDecimal("0.5")).add(KE).subtract(KE.multiply(XE));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * Qt 预报站（断面）流量
	 * @return
	 */
	public static List<BigDecimal> getQt(List<BigDecimal> QTR_List, BigDecimal KE, BigDecimal XE) {
		QT_List.clear();
		// Qt=C0*Qe+C1*Qeup+C2*Qeup

		for (int i=0; i<QTR_List.size()+KE.intValue(); i++){
			QT_List.add(NumberConst.ZERO);
		}

		BigDecimal QS1 = NumberConst.ZERO;
		BigDecimal QS2 = NumberConst.ZERO;
		BigDecimal QX1 = NumberConst.ZERO;
		BigDecimal QX2 = NumberConst.ZERO;

		BigDecimal C0 = getC0(KE, KE, XE);
		BigDecimal C1 = getC1(KE, KE, XE);
		BigDecimal C2 = getC2(KE, KE, XE);
		BigDecimal initQT = null;

		QX1 = QTR_List.get(0);

		for( int i=0; i<QTR_List.size()-1; i++) {
//			BigDecimal QT = NumberConst.ZERO;
//			if( i<plan.getKE().intValue() ){
//				QT = QTR_List.get(0);
//			}else{
////				System.out.println("C0.multiply(QTR_List.get(i))="+C0.multiply(QTR_List.get(i)));
////				System.out.println("C1.multiply(QTR_List.get(i-NumberConfig.KE.intValue()))="+C1.multiply(QTR_List.get(i-NumberConfig.KE.intValue())));
//				System.out.println("C2.multiply(QTR_List.get(i-NumberConfig.KE.intValue()))="+C2.multiply(QT_List.get(i-plan.getKE().intValue())));
//				QT = C0.multiply(QTR_List.get(i)).add(C1.multiply(QTR_List.get(i-plan.getKE().intValue()))).add(C2.multiply(QT_List.get(i-plan.getKE().intValue())));
//			}
//			if( initQT == null ){
//				initQT = QT;
//			}
//			if( i<plan.getKE().intValue() ){
//				QT_List.add(initQT);
//			}else{
//				QT_List.add(QT);
//			}


			QS1 = QTR_List.get(i);
			QS2 = QTR_List.get(i+1);
			for( int j = 1; j<=KE.intValue(); j++){
				QX2 = C0.multiply(QS2).add(C1.multiply(QS1)).add(C2.multiply(QX1));
				QS1 = QX1;
				QS2 = QX2;
				QX1 = QX2;
			}
			QT_List.set(i+KE.intValue(), QX2);
		}
		for( int i=0; i<=KE.intValue()-1; i++){
			QT_List.set(i, QT_List.get(KE.intValue()));
		}
		return QT_List;
	}
}
