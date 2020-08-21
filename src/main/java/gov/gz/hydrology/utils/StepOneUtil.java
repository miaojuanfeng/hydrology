package gov.gz.hydrology.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

public class StepOneUtil {

	private static Plan plan;
	
	/**
	 * R 时刻产流量
	 */
//	public static BigDecimal R;
	
	/**
	 * Rd 直接径流
	 */
	public static BigDecimal Rd;

	public static BigDecimal Wup;










	/**
	 * 上层蓄水量最终结果
	 */
	public static BigDecimal WU;

	/**
	 * 下层蓄水量最终结果
	 */
	public static BigDecimal WL;

	/**
	 * 深层蓄水量最终结果
	 */
	public static BigDecimal WD;

	/**
	 * WUup 上次计算上层蓄水量
	 */
	public static BigDecimal WUup;

	/**
	 * WLup 上次计算下层蓄水量
	 */
	public static BigDecimal WLup;

	/**
	 * WDup 上次计算深层蓄水量
	 */
	public static BigDecimal WDup;

	public static BigDecimal W;
	public static BigDecimal ES;
	public static BigDecimal EU;
	public static BigDecimal EL;
	public static BigDecimal ED;












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
//		R = NumberConst.ZERO;
		Rd = NumberConst.ZERO;

		WU = NumberConst.ZERO;
		WL = NumberConst.ZERO;
		WD = NumberConst.ZERO;
		WUup = plan.getWU0();
		WLup = plan.getWL0();
		WDup = plan.getWD0();
		W = NumberConst.ZERO;
		ES = NumberConst.ZERO;
		EU = NumberConst.ZERO;
		EL = NumberConst.ZERO;
		ED = NumberConst.ZERO;

		RS = NumberConst.ZERO;
		RSS = NumberConst.ZERO;
		RG = NumberConst.ZERO;
		S = NumberConst.ZERO;
		FR = NumberConst.ZERO;
		Sup = plan.getS0();
		FRup = plan.getFR0();

		Wup = plan.getWU0().add(plan.getWL0()).add(plan.getWD0());
	}
	
//	/**
//	 * W0  流域初始平均蓄水量
//	 * @return
//	 */
//	public static BigDecimal getW0() {
//		// W0 = WU0 + WL0 + WD0
//		return plan.getWU0().add(plan.getWL0()).add(plan.getWD0());
//	}
	
//	/**
//	 * Wm 流域平均蓄水容量
//	 * @return
//	 */
//	public static BigDecimal getWm() {
//		// Wm = WUM + WLM + WDM
//		return ;
//	}
	
//	/**
//	 * Wmm 流域内点最大的点蓄水容量
//	 * @return
//	 */
//	public static BigDecimal getWmm() {
//		// Wm = WUM + WLM + WDM
//		BigDecimal Wm = plan.getWUM().add(plan.getWLM()).add(plan.getWDM());
//		// Wmm = Wm*(1+B)
//		return Wm.multiply(NumberConst.ONE.add(plan.getB()));
//	}
	
//	/**
//	 * A
//	 */
//	public static BigDecimal getA() {
//		// Wm = WUM + WLM + WDM
//		BigDecimal Wm = plan.getWUM().add(plan.getWLM()).add(plan.getWDM());
//		// Wmm = Wm*(1+B)
//		BigDecimal Wmm = Wm.multiply(NumberConst.ONE.add(plan.getB()));
//		// A = Wmm*[1-(1-Wup/Wm)^(1/(B+1))]
//		BigDecimal base = NumberConst.ONE.subtract(Wup.divide(Wm, NumberConst.DIGIT, NumberConst.MODE));
//		BigDecimal power = NumberConst.ONE.divide(plan.getB().add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
//		return Wmm.multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
//	}
	
	/**
	 * R 产流量（径流深）
	 * Rd 直接径流
	 */
	public static List getR(List<BigDecimal> listP) {
		List<BigDecimal> listR = new ArrayList<>();
		BigDecimal R;
		/**
		 * 循环计算
		 */
		for (BigDecimal P : listP) {
			/**
			 * Wm = WUM + WLM + WDM
			 * Wmm = Wm*(1+B)
			 */
			BigDecimal Wm = plan.getWUM().add(plan.getWLM()).add(plan.getWDM());
			BigDecimal Wmm = Wm.multiply(NumberConst.ONE.add(plan.getB()));
			/**
			 * A = Wmm*[1-(1-Wup/Wm)^(1/(B+1))]
			 */
			BigDecimal base = NumberConst.ONE.subtract(Wup.divide(Wm, NumberConst.DIGIT, NumberConst.MODE));
			BigDecimal power = NumberConst.ONE.divide(plan.getB().add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
			BigDecimal A = Wmm.multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
			/**
			 * Ek = K * E
			 * PE = P - Ek
			 */
			BigDecimal PE = getPE(P);
			/**
			 * PE > 0
			 */
			if (NumberUtil.gt(PE, NumberConst.ZERO)) {
				/**
				 * PE + A < Wmm
				 */
				if ( NumberUtil.lt(PE.add(A), Wmm) ) {
					base = NumberConst.ONE.subtract(PE.add(A).divide(Wmm, NumberConst.DIGIT, NumberConst.MODE));
					power = NumberConst.ONE.add(plan.getB());
					R = PE.add(Wup).subtract(Wm).add(Wm.multiply(NumberUtil.pow(base, power)));
				/**
				 * PE+A>=Wmm
				 */
				} else {
					/**
					 * R=PE-(Wm-Wup)
					 */
					R = PE.subtract(Wm.subtract(Wup));
				}
			/**
			 * PE <= 0 ? R = 0
			 */
			} else {
				R = NumberConst.ZERO;
			}
			listR.add(R);

			Rd = NumberUtil.lt(PE, NumberConst.ZERO) ? NumberConst.ZERO : PE;
			getW(P, R);
			getS(P, R);
		}
		return listR;
	}

	private static void getW(BigDecimal P, BigDecimal R) {
//		System.out.println("PEx="+StepTwoUtil.getPEx());
//		System.out.println("WUx1="+StepTwoUtil.getWUx1()+" WUx2="+StepTwoUtil.getWUx2());
//		System.out.println("WLx1="+StepTwoUtil.getWLx1()+" WLx2="+StepTwoUtil.getWLx2());
//		System.out.println("WDx1="+StepTwoUtil.getWDx1()+" WDx2="+StepTwoUtil.getWDx2());
//		System.out.println("PEy="+StepTwoUtil.getPEy());
//		System.out.println("PEz="+StepTwoUtil.getPEz());
//		System.out.println("----------------Two算前----------------");

		BigDecimal EK = getEk();
		BigDecimal PE = getPE(P);
		// PE > 0  上分支
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			// PEx = PE-R
			BigDecimal PEx = PE.subtract(R);
			// WUx = WUup + PEx
			BigDecimal WUx = WUup.add(PEx);
			// WUx > WUM
			if( NumberUtil.gt(WUx, plan.getWUM()) ) {
				// PEy = WUx - WUM
				BigDecimal PEy = WUx.subtract(plan.getWUM());
				// WU = WUM
				WU = plan.getWUM();
				// WLx = WLup + PEy
				BigDecimal WLx = WLup.add(PEy);
				// WLx > WLM
				if( NumberUtil.gt(WLx, plan.getWLM()) ) {
					// PEz = WLx - WLM
					BigDecimal PEz = WLx.subtract(plan.getWLM());
					// WL = WLM
					WL = plan.getWLM();
					// WDx = WDup + PEz
					BigDecimal WDx = WDup.add(PEz);
					// WDx > WDM
					if( NumberUtil.gt(WDx, plan.getWDM()) ) {
						WD = plan.getWDM();
						// WDx <= WDM
					}else {
						WD = WDx;
					}
				} else {
					WL = WLx;
					WD = WDup;
				}
			} else {
				WU = WUx;
				WL = WLup;
				WD = WDup;
			}
			EU = EK;
			ED = NumberConst.ZERO;
			EL = NumberConst.ZERO;
		}else {
			// WUx = WUup + PE
			BigDecimal WUx = WUup.add(PE);
			// WUx > 0
			if( NumberUtil.gt(WUx, NumberConst.ZERO) ) {
				WU = WUx;
				WL = WLup;
				WD = WDup;
				EU = plan.getK().multiply(getE());
				EL = NumberConst.ZERO;
				ED = NumberConst.ZERO;
			}else {
				// EKy = WUx
				BigDecimal EKy = WUx;
				// WLx = WLup + WLup/WLM*EKy
				BigDecimal WLx = WLup.add(WLup.divide(plan.getWLM(), NumberConst.DIGIT, NumberConst.MODE).multiply(EKy));
				// WLx > 0
				if( NumberUtil.gt(WLx, NumberConst.ZERO) ) {
					WU = NumberConst.ZERO;
					WL = WLx;
					WD = WDup;
				}else {
					// EKz = WLx
					BigDecimal EKz = WLx;
					// WDx = WDup + C * EKz
					BigDecimal WDx = WDup.add(plan.getC().multiply(EKz));
					// WDx > 0
					if( NumberUtil.gt(WDx, NumberConst.ZERO) ) {
						WU = NumberConst.ZERO;
						WL = NumberConst.ZERO;
						WD = WDx;
						// WDx <= 0
					}else {
						WU = NumberConst.ZERO;
						WL = NumberConst.ZERO;
						WD = NumberConst.ZERO;
					}
				}
				EU = WUup.add(P);
				WU = NumberConst.ZERO;
				if (NumberUtil.gt(WLup, plan.getC().multiply(plan.getWLM()))) {
					EL = (EK.subtract(EU)).multiply(WLup).divide(plan.getWLM(), NumberConst.DIGIT, NumberConst.MODE);
					WL = WLup.subtract(EL);
					ED = NumberConst.ZERO;
					WD = WDup.subtract(ED);
				}else{
					BigDecimal temp = (EK.subtract(EU)).multiply(plan.getC());
					if( NumberUtil.gt(WLup, temp) ){
						EL = temp;
						WL = WLup.subtract(EL);
						ED = NumberConst.ZERO;
						WD = WDup.subtract(ED);
					}else{
						EL = WLup;
						WL = NumberConst.ZERO;
						ED = temp.subtract(EL);
						WD = WDup.subtract(ED);
					}
				}
			}
		}
		if( NumberUtil.gt(WU, plan.getWUM()) ){
			WU = plan.getWUM();
		}
		if( NumberUtil.gt(WL, plan.getWLM()) ){
			WL = plan.getWLM();
		}
		if( NumberUtil.gt(WD, plan.getWDM()) ){
			WD = plan.getWDM();
		}
		WUup = WU;
		WLup = WL;
		WDup = WD;
		Wup = WUup.add(WLup).add(WDup);
		ES = EU.add(EL).add(ED);
		W = WU.add(WL).add(WD);

//		System.out.println("WUup="+StepTwoUtil.WUup);
//		System.out.println("PE="+StepCommonUtil.getPE());
//		System.out.println("WU="+StepTwoUtil.WU);
//		System.out.println("WL="+StepTwoUtil.WL);
//		System.out.println("WD="+StepTwoUtil.WD);
//		System.out.println("W="+StepTwoUtil.W);
//		System.out.println("EU="+StepTwoUtil.EU);
//		System.out.println("EL="+StepTwoUtil.EL);
//		System.out.println("ED="+StepTwoUtil.ED);
//		System.out.println("ES="+StepTwoUtil.ES);
//		System.out.println("StepOneUtil.Wup="+StepOneUtil.Wup);
//		System.out.println("----------------Two算后----------------");
	}

	private static void getS(BigDecimal P, BigDecimal R) {
		BigDecimal tempBase = NumberConst.ZERO;
		BigDecimal tempPower = NumberConst.ZERO;
		BigDecimal PE = getPE(P);
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
			FR = R.divide(PE, NumberConst.DIGIT, NumberConst.MODE);
			S = FRup.multiply(Sup).divide(FR, NumberConst.DIGIT, NumberConst.MODE);
			BigDecimal Q = R.divide(FR, NumberConst.DIGIT, NumberConst.MODE);
			Integer N = Q.divide(new BigDecimal(5)).intValue() + 1;
			Q = Q.divide(new BigDecimal(N), NumberConst.DIGIT, NumberConst.MODE);

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
							RGD = (S.add(Q).subtract(RSD.divide(FR, NumberConst.DIGIT, NumberConst.MODE))).multiply(KGDD).multiply(FR);
							S = S.add(Q).subtract((RSD.add(RSSD).add(RGD)).divide(FR, NumberConst.DIGIT, NumberConst.MODE));
						} else {
							RSD = (Q.add(S).subtract(SMF)).multiply(FR);
							RSSD = SMF.multiply(KSSDD).multiply(FR);
							RGD = SMF.multiply(FR).multiply(KGDD);
							S = SMF.subtract((RSSD.add(RGD)).divide(FR, NumberConst.DIGIT, NumberConst.MODE));
						}
					} else {
						RSD = NumberConst.ZERO;
						RSSD = NumberConst.ZERO;
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
			tempBase = NumberConst.ONE.subtract(W.divide(plan.getWUM().add(plan.getWLM()).add(plan.getWDM()), NumberConst.DIGIT, NumberConst.MODE));
			tempPower = plan.getB().divide(NumberConst.ONE.add(plan.getB()), NumberConst.DIGIT, NumberConst.MODE);
			FR = NumberConst.ONE.subtract(NumberUtil.pow(tempBase, tempPower));
			// RS=0
			RS = NumberConst.ZERO;
			// RSS=Sup*KSS*FR
			RSS = Sup.multiply(getKSSD()).multiply(getFR(PE, R));
			// RGD=Sup*KG*FR
			RG = Sup.multiply(getKGD()).multiply(getFR(PE, R));
			// S=Sup-(RSS+RG)/FR
			S = Sup.subtract(RSS.add(RG).divide(getFR(PE, R), NumberConst.DIGIT, NumberConst.MODE));
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
	private static BigDecimal getFR(BigDecimal PE, BigDecimal R) {
		// PE > 0
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			// FR=R/PE
			return R.divide(PE, NumberConst.DIGIT, NumberConst.MODE);
		}else {
			// Wi=WU+WL+WD
			BigDecimal Wi = WU.add(WL).add(WD);
			// Wm=WUM+WLM+WDM
			BigDecimal Wm = plan.getWUM().add(plan.getWLM()).add(plan.getWDM());
			// FR=1-(1-Wi/Wm)^[B/(1+B)]
			BigDecimal base = NumberConst.ONE.subtract(Wi.divide(Wm, NumberConst.DIGIT, NumberConst.MODE));
			BigDecimal power = plan.getB().divide(plan.getB().add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
			return NumberConst.ONE.subtract(NumberUtil.pow(base, power));
		}
	}

	private static BigDecimal getE() {
		/*
			月分取P的月份/天数/小时
		 */
//		BigDecimal E = NumberConfig.E.get(9).divide(new BigDecimal(31), NumberConst.DIGIT, NumberConst.MODE).divide(new BigDecimal(24), NumberConst.DIGIT, NumberConst.MODE);
//		return E;
		return new BigDecimal("0.14");
	}

	private static BigDecimal getEk() {
		// Ek = K * E
		return plan.getK().multiply(getE());
	}

	private static BigDecimal getPE(BigDecimal P) {
		// PE = P - Ek
		return P.subtract(getEk());
	}

	private static BigDecimal getSMMF() {
		// SMMF = SM*(1+EX)
		return plan.getSM().multiply(NumberConst.ONE.add(plan.getEX()));
	}

	private static BigDecimal getAU() {
		// AU = SMMF(1-(1-Sup/SM)^[1/(1+EX)])
		BigDecimal base = NumberConst.ONE.subtract(Sup.divide(plan.getSM(), NumberConst.DIGIT, NumberConst.MODE));
		BigDecimal power = NumberConst.ONE.divide(NumberConst.ONE.add(plan.getEX()), NumberConst.DIGIT, NumberConst.MODE);
		return getSMMF().multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
	}

	private static BigDecimal getKSSD(){
		// KSSD = (1 - (1 - (KG + KSS)) ^ (T / 24)) / (1 + KG / KSS)
		BigDecimal base = NumberConst.ONE.subtract(plan.getKG().add(plan.getKSS()));
		BigDecimal power = plan.getT().divide(new BigDecimal(24), NumberConst.DIGIT, NumberConst.MODE);
		BigDecimal KSS = (NumberConst.ONE.subtract(NumberUtil.pow(base, power))).divide(NumberConst.ONE.add(plan.getKG().divide(plan.getKSS(), NumberConst.DIGIT, NumberConst.MODE)), NumberConst.DIGIT, NumberConst.MODE);
		return KSS;
	}

	private static BigDecimal getKGD(){
		return getKSSD().multiply(plan.getKG()).divide(plan.getKSS(), NumberConst.DIGIT, NumberConst.MODE);
	}

	public static void main(String[] args){
		Plan plan = new Plan();
		plan.setF(new BigDecimal("911"));
		plan.setK(new BigDecimal("0.735"));
		plan.setIM(new BigDecimal("0.044"));
		plan.setWUM(new BigDecimal("10.562"));
		plan.setWLM(new BigDecimal("68.412"));
		plan.setWDM(new BigDecimal("1.042"));
		plan.setB(new BigDecimal("0.5"));
		plan.setC(new BigDecimal("0.199"));
		plan.setKSS(new BigDecimal("0.403"));
		plan.setKG(new BigDecimal("0.297"));
		plan.setSM(new BigDecimal("25.174"));
		plan.setEX(new BigDecimal("1.5"));
		plan.setCI(new BigDecimal("0.112"));
		plan.setCG(new BigDecimal("0.95"));
		plan.setCS(new BigDecimal("0.948"));
		plan.setL(4);
		plan.setT(new BigDecimal("1"));
		plan.setKE(new BigDecimal("11"));
		plan.setXE(new BigDecimal("0.335"));
		plan.setWU0(new BigDecimal("10"));
		plan.setWL0(new BigDecimal("60"));
		plan.setWD0(new BigDecimal("10"));
		plan.setS0(new BigDecimal("6"));
		plan.setFR0(new BigDecimal("0.7"));
		plan.setQRs0(new BigDecimal("3"));
		plan.setQRss0(new BigDecimal("38"));
		plan.setQRg0(new BigDecimal("7"));
		init(plan);

		List<BigDecimal> listP = new ArrayList<>();
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
//		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("1.62"));
		listP.add(new BigDecimal("0.20"));
		listP.add(new BigDecimal("0.08"));
		listP.add(new BigDecimal("0.48"));
		listP.add(new BigDecimal("0.25"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.13"));
		listP.add(new BigDecimal("0.33"));
		listP.add(new BigDecimal("0.23"));
		listP.add(new BigDecimal("0.48"));
		listP.add(new BigDecimal("0.36"));
		listP.add(new BigDecimal("3.90"));
		listP.add(new BigDecimal("4.54"));
		listP.add(new BigDecimal("1.34"));
		listP.add(new BigDecimal("0.17"));
		listP.add(new BigDecimal("0.13"));
		listP.add(new BigDecimal("0.17"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.03"));
		listP.add(new BigDecimal("4.19"));
		listP.add(new BigDecimal("10.26"));
		listP.add(new BigDecimal("8.01"));
		listP.add(new BigDecimal("2.74"));
		listP.add(new BigDecimal("2.33"));
		listP.add(new BigDecimal("1.01"));
		listP.add(new BigDecimal("0.46"));
		listP.add(new BigDecimal("3.19"));
		listP.add(new BigDecimal("4.04"));
		listP.add(new BigDecimal("0.22"));
		listP.add(new BigDecimal("0.84"));
		listP.add(new BigDecimal("2.22"));
		listP.add(new BigDecimal("6.25"));
		listP.add(new BigDecimal("4.58"));
		listP.add(new BigDecimal("3.33"));
		listP.add(new BigDecimal("4.54"));
		listP.add(new BigDecimal("2.66"));
		listP.add(new BigDecimal("1.10"));
		listP.add(new BigDecimal("0.21"));
		listP.add(new BigDecimal("0.10"));
		listP.add(new BigDecimal("0.16"));
		listP.add(new BigDecimal("2.54"));
		listP.add(new BigDecimal("3.16"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.22"));
		listP.add(new BigDecimal("0.26"));
		listP.add(new BigDecimal("0.79"));
		listP.add(new BigDecimal("1.48"));
		listP.add(new BigDecimal("2.05"));
		listP.add(new BigDecimal("2.00"));
		listP.add(new BigDecimal("0.95"));
		listP.add(new BigDecimal("0.33"));
		listP.add(new BigDecimal("0.89"));
		listP.add(new BigDecimal("0.68"));
		listP.add(new BigDecimal("0.10"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.02"));
		listP.add(new BigDecimal("0.24"));
		listP.add(new BigDecimal("0.15"));
		listP.add(new BigDecimal("0.05"));
		listP.add(new BigDecimal("0.32"));
		listP.add(new BigDecimal("0.12"));
		listP.add(new BigDecimal("1.45"));
		listP.add(new BigDecimal("0.14"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.07"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.03"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.03"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.12"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.13"));
		listP.add(new BigDecimal("0.05"));
		listP.add(new BigDecimal("0.28"));
		listP.add(new BigDecimal("0.68"));
		listP.add(new BigDecimal("0.33"));
		listP.add(new BigDecimal("0.47"));
		listP.add(new BigDecimal("0.88"));
		listP.add(new BigDecimal("2.07"));
		listP.add(new BigDecimal("0.62"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.02"));
		listP.add(new BigDecimal("0.02"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.15"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("3.10"));
		listP.add(new BigDecimal("12.21"));
		listP.add(new BigDecimal("16.30"));
		listP.add(new BigDecimal("7.46"));
		listP.add(new BigDecimal("18.24"));
		listP.add(new BigDecimal("15.13"));
		listP.add(new BigDecimal("8.72"));
		listP.add(new BigDecimal("4.68"));
		listP.add(new BigDecimal("2.35"));
		listP.add(new BigDecimal("0.89"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.03"));
		listP.add(new BigDecimal("0.11"));
		listP.add(new BigDecimal("0.06"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.01"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.25"));
		listP.add(new BigDecimal("0.69"));
		listP.add(new BigDecimal("0.14"));
		listP.add(new BigDecimal("0.26"));
		listP.add(new BigDecimal("0.03"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.12"));
		listP.add(new BigDecimal("0.04"));
		listP.add(new BigDecimal("0.67"));
		listP.add(new BigDecimal("1.86"));
		listP.add(new BigDecimal("1.57"));
		listP.add(new BigDecimal("0.91"));
		listP.add(new BigDecimal("1.01"));
		listP.add(new BigDecimal("0.14"));
		listP.add(new BigDecimal("0.13"));
		listP.add(new BigDecimal("0.28"));
		listP.add(new BigDecimal("3.43"));
		listP.add(new BigDecimal("1.36"));
		listP.add(new BigDecimal("0.07"));
		listP.add(new BigDecimal("0.02"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.14"));
		listP.add(new BigDecimal("0.08"));
		listP.add(new BigDecimal("0.00"));
		listP.add(new BigDecimal("0.25"));
		listP.add(new BigDecimal("0.85"));
		List<BigDecimal> listR = getR(listP);
	}
}
