package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepThreeUtil {

	private static Plan plan;
	
	/**
	 * Rs 地表径流
	 */
	public static BigDecimal RS;
	/**
	 * Rss 壤中流
	 */
	public static BigDecimal RSS;
	/**
	 * Rg 地下径流
	 */
	public static BigDecimal RG;


//	public static BigDecimal RGD;
	/**
	 * S 自由水蓄水量
	 */
	public static BigDecimal S;
	public static BigDecimal FR;

	/**
	 * Sup 上次计算后自由水蓄水量
	 */
	public static BigDecimal Sup;
	public static BigDecimal FRup;
	
	public static void init(Plan p) {
		plan = p;
		RS = NumberConst.ZERO;
		RSS = NumberConst.ZERO;
		RG = NumberConst.ZERO;
//		RGD = NumberConst.ZERO;
		S = NumberConst.ZERO;
		FR = NumberConst.ZERO;
		Sup = plan.getS0();
		FRup = plan.getFR0();
	}
	
	/**
	 * 最终结果
	 * @return
	 */
	public static void getResult() {
		BigDecimal tempBase = NumberConst.ZERO;
		BigDecimal tempPower = NumberConst.ZERO;
		BigDecimal PE = StepCommonUtil.getPE();
		// PE>0
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
//			BigDecimal SMMF = getSMMF();
//			BigDecimal AU = getAU();
//			BigDecimal temp_PE_AU = PE.add(AU);
//			// PE+AU<SMMF
//			if( NumberUtil.lt(temp_PE_AU, SMMF) ) {
//				BigDecimal base = null;
//				BigDecimal power = null;
//				// Rs=(PE-SM+Sup+SM*(1-(PE+AU)/SMMF)^(1+EX))*FR
//				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
//				power = NumberConst.ONE.add(NumberConfig.EX);
//				Rs = getFR().multiply(StepCommonUtil.getPE().subtract(NumberConfig.SM).add(Sup.add(NumberConfig.SM.multiply(NumberUtil.pow(base, power)))));
//				// Rss={SM-SM*[1-(PE+AU)/SMMF]^(1+EX)}*KSS*FR
//				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
//				power = NumberConst.ONE.add(NumberConfig.EX);
//				Rss = NumberConfig.SM.subtract(NumberConfig.SM.multiply(NumberUtil.pow(base, power))).multiply(NumberConfig.KSS).multiply(getFR());
//				// RG={SM-SM*[1-(PE+AU)/SMMF]^(1+EX)}*KG*FR
//				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
//				power = NumberConst.ONE.add(NumberConfig.EX);
//				Rg = NumberConfig.SM.subtract(NumberConfig.SM.multiply(NumberUtil.pow(base, power))).multiply(NumberConfig.KG).multiply(getFR());
//				// S=(1-KSS-KG){SM-SM[1-(PE+AU)/SMMF]^(1+EX)}
//				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
//				power = NumberConst.ONE.add(NumberConfig.EX);
//				S = NumberConst.ONE.subtract(NumberConfig.KSS).subtract(NumberConfig.KG).multiply(NumberConfig.SM.subtract(NumberConfig.SM.multiply(NumberUtil.pow(base, power))));
//			// PE+AU>=SMMF
//			}else {
//				// Rs=(PE-SM+Sup)*FR
//				Rs = getFR().multiply(StepCommonUtil.getPE().subtract(NumberConfig.SM).add(Sup));
//				// Rss=SM*KSS*FR
//				Rss = NumberConfig.SM.multiply(NumberConfig.KSS).multiply(getFR());
//				// Rg=SM*KG*FR
//				Rg = NumberConfig.SM.multiply(NumberConfig.KG).multiply(getFR());
//				// S=(1-KSS-KG)*SM
//				S = NumberConfig.SM.multiply(NumberConst.ONE.subtract(NumberConfig.KSS).subtract(NumberConfig.KG));
//			}
			FR = StepOneUtil.R.divide(StepCommonUtil.getPE(), NumberConst.DIGIT, NumberConst.MODE);
			S = FRup.multiply(Sup).divide(FR, NumberConst.DIGIT, NumberConst.MODE);
			BigDecimal Q = StepOneUtil.R.divide(FR, NumberConst.DIGIT, NumberConst.MODE);
			System.out.println("Q="+Q);
			Integer N = Q.divide(new BigDecimal(5)).intValue() + 1;
			System.out.println("N="+N);
			Q = Q.divide(new BigDecimal(N), NumberConst.DIGIT, NumberConst.MODE);
			System.out.println("Q="+Q);

			BigDecimal a = getKGD();
			BigDecimal  b = getKSSD();
			tempBase = NumberConst.ONE.subtract(getKGD().add(getKSSD()));
			tempPower = NumberConst.ONE.divide(new BigDecimal(N), NumberConst.DIGIT, NumberConst.MODE);
			BigDecimal KSSDD = (NumberConst.ONE.subtract(NumberUtil.pow(tempBase, tempPower))).divide(NumberConst.ONE.add(getKGD().divide(getKSSD(), NumberConst.DIGIT, NumberConst.MODE)), NumberConst.DIGIT, NumberConst.MODE);
			BigDecimal KGDD = KSSDD.multiply(plan.getKG()).divide(plan.getKSS(), NumberConst.DIGIT, NumberConst.MODE);

			RS = NumberConst.ZERO;
			RSS = NumberConst.ZERO;
			RG = NumberConst.ZERO;
			BigDecimal SMM = (NumberConst.ONE.add(plan.getEX())).multiply(plan.getSM());
			BigDecimal SMMF = NumberConst.ZERO;
			if( NumberUtil.et(plan.getEX(), NumberConst.ZERO) ){
				SMMF = SMM;
			}else{
				tempBase = NumberConst.ONE.subtract(FR);
				tempPower = NumberConst.ONE.divide(plan.getEX(), NumberConst.DIGIT, NumberConst.MODE);
				SMMF = (NumberConst.ONE.subtract(NumberUtil.pow(tempBase, tempPower))).multiply(SMM);
				BigDecimal SMF = SMMF.divide(NumberConst.ONE.add(plan.getEX()));
				for( int i=1; i<= N; i++ ) {
					BigDecimal RSD = NumberConst.ZERO;
					BigDecimal RSSD = NumberConst.ZERO;
					BigDecimal RGD = NumberConst.ZERO;

					if (NumberUtil.gt(S, SMF)) {
						S = SMF;
					}
					tempBase = NumberConst.ONE.subtract(S.divide(SMF, NumberConst.DIGIT, NumberConst.MODE));
					tempPower = NumberConst.ONE.divide(NumberConst.ONE.add(plan.getEX()), NumberConst.DIGIT, NumberConst.MODE);
					BigDecimal AU = SMMF.multiply(NumberConst.ONE.subtract(NumberUtil.pow(tempBase, tempPower)));

					if (NumberUtil.gt(Q.add(AU), NumberConst.ZERO)) {
						if (NumberUtil.lt(Q.add(AU), SMMF)) {
							tempBase = NumberConst.ONE.subtract((Q.add(AU)).divide(SMMF, NumberConst.DIGIT, NumberConst.MODE));
							tempPower = NumberConst.ONE.add(plan.getEX());
							RSD = (Q.subtract(SMF).add(S).add(SMF.multiply(NumberUtil.pow(tempBase, tempPower)))).multiply(FR);
							RSSD = (S.add(Q).subtract(RSD.divide(FR, NumberConst.DIGIT, NumberConst.MODE))).multiply(KSSDD).multiply(FR);
							System.out.println("Iup="+i+" RSSD="+RSSD);
							RGD = (S.add(Q).subtract(RSD.divide(FR, NumberConst.DIGIT, NumberConst.MODE))).multiply(KGDD).multiply(FR);
							S = S.add(Q).subtract((RSD.add(RSSD).add(RGD)).divide(FR, NumberConst.DIGIT, NumberConst.MODE));
						} else {
							RSD = (Q.add(S).subtract(SMF)).multiply(FR);
							RSSD = SMF.multiply(KSSDD).multiply(FR);
							System.out.println("Idown="+i+" RSSD="+RSSD);
							RGD = SMF.multiply(FR).multiply(KGDD);
							S = SMF.subtract((RSSD.add(RGD)).divide(FR, NumberConst.DIGIT, NumberConst.MODE));
						}
					} else {
						RSD = NumberConst.ZERO;
						RSSD = NumberConst.ZERO;
						System.out.println("<0="+i+" RSSD="+RSSD);
						RGD = NumberConst.ZERO;
						S = NumberConst.ZERO;
					}
					RS = RS.add(RSD);
					RSS = RSS.add(RSSD);
					RG = RG.add(RGD);
				}
			}
		// PE<=0
		}else {
			//FR = 1-(1-W/Wm)^[B/(1+B)]
			tempBase = NumberConst.ONE.subtract(StepTwoUtil.W.divide(StepOneUtil.getWm(), NumberConst.DIGIT, NumberConst.MODE));
			tempPower = plan.getB().divide(NumberConst.ONE.add(plan.getB()), NumberConst.DIGIT, NumberConst.MODE);
			FR = NumberConst.ONE.subtract(NumberUtil.pow(tempBase, tempPower));
			// RS=0
			RS = NumberConst.ZERO;
			// RSS=Sup*KSS*FR
			RSS = Sup.multiply(getKSSD()).multiply(getFR());
			// RGD=Sup*KG*FR
			RG = Sup.multiply(getKGD()).multiply(getFR());
			// S=Sup-(RSS+RG)/FR
			S = Sup.subtract(RSS.add(RG).divide(getFR(), NumberConst.DIGIT, NumberConst.MODE));
		}
		Sup = S;
		FRup = FR;

//		System.out.println("RS="+StepThreeUtil.RS);
//		System.out.println("RSS="+StepThreeUtil.RSS);
//		System.out.println("RG="+StepThreeUtil.RG);
//		System.out.println("S="+StepThreeUtil.S);
//		System.out.println("FR="+StepThreeUtil.FR);
	}

	/**
	 * FR
	 * @return
	 */
	public static BigDecimal getFR() {
		BigDecimal PE = StepCommonUtil.getPE();
		// PE > 0
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			// FR=R/PE
			return StepOneUtil.R.divide(StepCommonUtil.getPE(), NumberConst.DIGIT, NumberConst.MODE);
		}else {
			// Wi=WU+WL+WD
			BigDecimal Wi = StepTwoUtil.WU.add(StepTwoUtil.WL).add(StepTwoUtil.WD);
			// Wm=WUM+WLM+WDM
			BigDecimal Wm = plan.getWUM().add(plan.getWLM()).add(plan.getWDM());
			// FR=1-(1-Wi/Wm)^[B/(1+B)]
			BigDecimal base = NumberConst.ONE.subtract(Wi.divide(Wm, NumberConst.DIGIT, NumberConst.MODE));
			BigDecimal power = plan.getB().divide(plan.getB().add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
			return NumberConst.ONE.subtract(NumberUtil.pow(base, power));
		}
	}
	
	/**
	 * SMMF 产流面积上的自由水蓄水量
	 * @return
	 */
	public static BigDecimal getSMMF() {
		// SMMF=SM*(1+EX)
		return plan.getSM().multiply(NumberConst.ONE.add(plan.getEX()));
	}
	
	/**
	 * AU
	 * @return
	 */
	public static BigDecimal getAU() {
		// AU=SMMF(1-(1-Sup/SM)^[1/(1+EX)])
		BigDecimal base = NumberConst.ONE.subtract(Sup.divide(plan.getSM(), NumberConst.DIGIT, NumberConst.MODE));
		BigDecimal power = NumberConst.ONE.divide(NumberConst.ONE.add(plan.getEX()), NumberConst.DIGIT, NumberConst.MODE);
		return getSMMF().multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
	}

	public static BigDecimal getKSSD(){
		BigDecimal base = NumberConst.ONE.subtract(plan.getKG().add(plan.getKSS()));
		BigDecimal power = plan.getT().divide(new BigDecimal(24), NumberConst.DIGIT, NumberConst.MODE);
		BigDecimal KSS = (NumberConst.ONE.subtract(NumberUtil.pow(base, power))).divide(NumberConst.ONE.add(plan.getKG().divide(plan.getKSS(), NumberConst.DIGIT, NumberConst.MODE)), NumberConst.DIGIT, NumberConst.MODE);
		return KSS;
	}

	public static BigDecimal getKGD(){
		return getKSSD().multiply(plan.getKG()).divide(plan.getKSS(), NumberConst.DIGIT, NumberConst.MODE);
	}
}
