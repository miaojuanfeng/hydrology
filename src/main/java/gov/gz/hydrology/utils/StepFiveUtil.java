package gov.gz.hydrology.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepFiveUtil {

	private static Plan plan;

	/**
	 * DT 时段长, 变量
	 */
	public static BigDecimal DT;

	private static List<BigDecimal> QT_List = new ArrayList<>();

	public static void init(Plan p) {
		plan = p;
		DT = plan.getKE();
	}

	/**
	 * C0   参数
	 * @return
	 */
	public static BigDecimal getC0() {
		// C0=(0.5*DT-KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = DT.multiply(new BigDecimal("0.5")).subtract(plan.getKE().multiply(plan.getXE()));
		BigDecimal divisor = DT.multiply(new BigDecimal("0.5")).add(plan.getKE()).subtract(plan.getKE().multiply(plan.getXE()));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * C1   参数
	 * @return
	 */
	public static BigDecimal getC1() {
		// C1=(0.5*DT+KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = DT.multiply(new BigDecimal("0.5")).add(plan.getKE().multiply(plan.getXE()));
		BigDecimal divisor = DT.multiply(new BigDecimal("0.5")).add(plan.getKE()).subtract(plan.getKE().multiply(plan.getXE()));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * C2   参数
	 * @return
	 */
	public static BigDecimal getC2() {
		// C2=(-0.5*DT+KE-KE*XE)/(0.5*DT+KE-KE*XE)
		BigDecimal base = DT.multiply(new BigDecimal("-0.5")).add(plan.getKE()).subtract(plan.getKE().multiply(plan.getXE()));
		BigDecimal divisor = DT.multiply(new BigDecimal("0.5")).add(plan.getKE()).subtract(plan.getKE().multiply(plan.getXE()));
		return base.divide(divisor, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * Qt 预报站（断面）流量
	 * @return
	 */
	public static List<BigDecimal> getQt(List<BigDecimal> QTR_List) {
		QT_List.clear();
		// Qt=C0*Qe+C1*Qeup+C2*Qeup

//		for (int i=0; i<QTR_List; i++){
//			QT_List.add(NumberConst.ZERO);
//		}

		BigDecimal C0 = getC0();
		BigDecimal C1 = getC1();
		BigDecimal C2 = getC2();
		BigDecimal initQT = null;
		for( int i=0; i<QTR_List.size(); i++) {
			BigDecimal QT = NumberConst.ZERO;
			if( i<plan.getKE().intValue() ){
				QT = QTR_List.get(0);
			}else{
//				System.out.println("C0.multiply(QTR_List.get(i))="+C0.multiply(QTR_List.get(i)));
//				System.out.println("C1.multiply(QTR_List.get(i-NumberConfig.KE.intValue()))="+C1.multiply(QTR_List.get(i-NumberConfig.KE.intValue())));
				System.out.println("C2.multiply(QTR_List.get(i-NumberConfig.KE.intValue()))="+C2.multiply(QT_List.get(i-plan.getKE().intValue())));
				QT = C0.multiply(QTR_List.get(i)).add(C1.multiply(QTR_List.get(i-plan.getKE().intValue()))).add(C2.multiply(QT_List.get(i-plan.getKE().intValue())));
			}
			if( initQT == null ){
				initQT = QT;
			}
			if( i<plan.getKE().intValue() ){
				QT_List.add(initQT);
			}else{
				QT_List.add(QT);
			}
		}
		return QT_List;
	}
}
