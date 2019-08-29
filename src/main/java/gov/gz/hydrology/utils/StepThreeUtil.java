package gov.gz.hydrology.utils;

import java.math.BigDecimal;

import gov.gz.hydrology.constant.NumberConfig;
import gov.gz.hydrology.constant.NumberConst;

public class StepThreeUtil {
	
	/**
	 * Rs 地表径流
	 */
	public static BigDecimal Rs;
	/**
	 * Rss 壤中流
	 */
	public static BigDecimal Rss;
	/**
	 * Rg 地下径流
	 */
	public static BigDecimal Rg;
	/**
	 * S 自由水蓄水量
	 */
	public static BigDecimal S;
	/**
	 * Sup 上次计算后自由水蓄水量
	 */
	public static BigDecimal Sup;
	
	static {
		Rs = NumberConst.ZERO;
		Rss = NumberConst.ZERO;
		Rg = NumberConst.ZERO;
		S = NumberConst.ZERO;
		Sup = NumberConst.ZERO;
	}
	
	/**
	 * 最终结果
	 * @return
	 */
	public static void getResult() {
		BigDecimal PE = StepCommonUtil.getPE();
		// PE>0
		if( NumberUtil.gt(PE, NumberConst.ZERO) ) {
			BigDecimal SMMF = getSMMF();
			BigDecimal AU = getAU();
			BigDecimal temp_PE_AU = PE.add(AU);
			// PE+AU<SMMF
			if( NumberUtil.lt(temp_PE_AU, SMMF) ) {
				BigDecimal base = null;
				BigDecimal power = null;
				// Rs=(PE-SM+Sup+SM*(1-(PE+AU)/SMMF)^(1+EX))*FR
				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
				power = NumberConst.ONE.add(NumberConfig.EX);
				Rs = getFR().multiply(StepCommonUtil.getPE().subtract(NumberConfig.SM).add(Sup.add(NumberConfig.SM.multiply(NumberUtil.pow(base, power)))));
				// Rss={SM-SM*[1-(PE+AU)/SMMF]^(1+EX)}*KSS*FR
				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
				power = NumberConst.ONE.add(NumberConfig.EX);
				Rss = NumberConfig.SM.subtract(NumberConfig.SM.multiply(NumberUtil.pow(base, power))).multiply(NumberConfig.KSS).multiply(getFR());
				// RG={SM-SM*[1-(PE+AU)/SMMF]^(1+EX)}*KG*FR
				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
				power = NumberConst.ONE.add(NumberConfig.EX);
				Rg = NumberConfig.SM.subtract(NumberConfig.SM.multiply(NumberUtil.pow(base, power))).multiply(NumberConfig.KG).multiply(getFR());
				// S=(1-KSS-KG){SM-SM[1-(PE+AU)/SMMF]^(1+EX)}
				base = NumberConst.ONE.subtract(StepCommonUtil.getPE().add(getAU()).divide(getSMMF(), NumberConst.DIGIT, NumberConst.MODE));
				power = NumberConst.ONE.add(NumberConfig.EX);
				S = NumberConst.ONE.subtract(NumberConfig.KSS).subtract(NumberConfig.KG).multiply(NumberConfig.SM.subtract(NumberConfig.SM.multiply(NumberUtil.pow(base, power))));
			// PE+AU>=SMMF
			}else {
				// Rs=(PE-SM+Sup)*FR
				Rs = getFR().multiply(StepCommonUtil.getPE().subtract(NumberConfig.SM).add(Sup));
				// Rss=SM*KSS*FR
				Rss = NumberConfig.SM.multiply(NumberConfig.KSS).multiply(getFR());
				// Rg=SM*KG*FR
				Rg = NumberConfig.SM.multiply(NumberConfig.KG).multiply(getFR());
				// S=(1-KSS-KG)*SM
				S = NumberConfig.SM.multiply(NumberConst.ONE.subtract(NumberConfig.KSS).subtract(NumberConfig.KG));
			}
		// PE<=0
		}else {
			// Rs=0
			Rs = NumberConst.ZERO;
			// Rss=Sup*KSS*FR
			Rss = Sup.multiply(NumberConfig.KSS).multiply(getFR());
			// Rg=Sup*KG*FR
			Rg = Sup.multiply(NumberConfig.KG).multiply(getFR());
			// S=(1-KSS-KG)*Sup
			S = Sup.multiply(NumberConst.ONE.subtract(NumberConfig.KSS).subtract(NumberConfig.KG));
		}
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
			BigDecimal Wm = NumberConfig.WUM.add(NumberConfig.WLM).add(NumberConfig.WDM);		
			// FR=1-(1-Wi/Wm)^[B/(1+B)]
			BigDecimal base = NumberConst.ONE.subtract(Wi.divide(Wm, NumberConst.DIGIT, NumberConst.MODE));
			BigDecimal power = NumberConfig.B.divide(NumberConfig.B.add(NumberConst.ONE), NumberConst.DIGIT, NumberConst.MODE);
			return NumberConst.ONE.subtract(NumberUtil.pow(base, power));
		}
	}
	
	/**
	 * SMMF 产流面积上的自由水蓄水量
	 * @return
	 */
	public static BigDecimal getSMMF() {
		// SMMF=SM*(1+EX)
		return NumberConfig.SM.multiply(NumberConst.ONE.add(NumberConfig.EX));
	}
	
	/**
	 * AU
	 * @return
	 */
	public static BigDecimal getAU() {
		// AU=SMMF(1-(1-Sup/SM)^[1/(1+EX)])
		BigDecimal base = NumberConst.ONE.subtract(Sup.divide(NumberConfig.SM, NumberConst.DIGIT, NumberConst.MODE));
		BigDecimal power = NumberConst.ONE.divide(NumberConst.ONE.add(NumberConfig.EX), NumberConst.DIGIT, NumberConst.MODE);
		return getSMMF().multiply(NumberConst.ONE.subtract(NumberUtil.pow(base, power)));
	}
	
}
