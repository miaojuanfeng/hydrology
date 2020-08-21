package gov.gz.hydrology.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepFiveUtil {

	private static List<BigDecimal> listQT = new ArrayList<>();
	
	/**
	 * Qt 预报站（断面）流量
	 * @return
	 */
	public static List getQT(List<BigDecimal> listQTRR, BigDecimal KE, BigDecimal XE) {
		listQT.clear();
		// Qt=C0*Qe+C1*Qeup+C2*Qeup

		for (int i = 0; i < listQTRR.size() + KE.intValue(); i++){
			listQT.add(NumberConst.ZERO);
		}

		BigDecimal QS1 = NumberConst.ZERO;
		BigDecimal QS2 = NumberConst.ZERO;
		BigDecimal QX1 = NumberConst.ZERO;
		BigDecimal QX2 = NumberConst.ZERO;

		/**
		 * C0=(0.5*DT-KE*XE)/(0.5*DT+KE-KE*XE)
		 * C1=(0.5*DT+KE*XE)/(0.5*DT+KE-KE*XE)
		 * C2=(-0.5*DT+KE-KE*XE)/(0.5*DT+KE-KE*XE)
		 * temp0 = 0.5*DT
		 * temp1 = KE*XE
		 * temp2 = 0.5*DT+KE-KE*XE)
		 */
		BigDecimal DECIMAL_0_5 = new BigDecimal("0.5");
		BigDecimal temp0 = KE.multiply(DECIMAL_0_5);
		BigDecimal temp1 = KE.multiply(XE);
		BigDecimal temp2 = KE.multiply(DECIMAL_0_5).add(KE).subtract(temp1);

		BigDecimal C0 = temp0.subtract(temp1).divide(temp2, NumberConst.DIGIT, NumberConst.MODE);
		BigDecimal C1 = temp0.add(temp1).divide(temp2, NumberConst.DIGIT, NumberConst.MODE);
		BigDecimal C2 = KE.subtract(temp0).subtract(temp1).divide(temp2, NumberConst.DIGIT, NumberConst.MODE);

		BigDecimal initQT = null;

		QX1 = listQTRR.get(0);

		for( int i = 0; i < listQTRR.size() - 1; i++) {
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

			QS1 = listQTRR.get(i);
			QS2 = listQTRR.get(i+1);
			for( int j = 1; j <= KE.intValue(); j++){
				QX2 = C0.multiply(QS2).add(C1.multiply(QS1)).add(C2.multiply(QX1));
				QS1 = QX1;
				QS2 = QX2;
				QX1 = QX2;
			}
			listQT.set(i+KE.intValue(), QX2);
		}
		for( int i = 0; i <= KE.intValue() - 1; i++){
			listQT.set(i, listQT.get(KE.intValue()));
		}
		return listQT;
	}
}
