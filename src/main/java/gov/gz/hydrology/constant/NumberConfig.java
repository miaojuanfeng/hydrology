package gov.gz.hydrology.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NumberConfig {
	
	/**
	 * WU0  上层土壤蓄水容量初始含水量
	 */
	public static BigDecimal WU0 = new BigDecimal(40);
	
	/**
	 * WL0  下层土壤蓄水容量初始含水量
	 */
	public static BigDecimal WL0 = new BigDecimal(20);
	
	/**
	 * WD0  深层土壤蓄水容量初始含水量
	 */
	public static BigDecimal WD0 = new BigDecimal(10);
	
	/**
	 * QRSS  壤中流初值
	 */
	public static BigDecimal QRSS = new BigDecimal(5);
	
	/**
	 * QRG  地下径流初值
	 */
	public static BigDecimal QRG = new BigDecimal(3);
	
	/**
	 * E  每小时蒸发量
	 */
	public static List<BigDecimal> E = new ArrayList<BigDecimal>();
	
	/**
	 * 根据月蒸发计算每小时蒸发
	 */
	static {
		E.add(hourE(new BigDecimal("41.3"))); // 1月
		E.add(hourE(new BigDecimal("38.6"))); // 2月
		E.add(hourE(new BigDecimal("47.0"))); // 3月
		E.add(hourE(new BigDecimal("68.5"))); // 4月
		E.add(hourE(new BigDecimal("88.4"))); // 5月
		E.add(hourE(new BigDecimal("101.9"))); // 6月
		E.add(hourE(new BigDecimal("160.7"))); // 7月
		E.add(hourE(new BigDecimal("140.4"))); // 8月
		E.add(hourE(new BigDecimal("116.0"))); // 9月
		E.add(hourE(new BigDecimal("100.9"))); // 10月
		E.add(hourE(new BigDecimal("66.9"))); // 11月
		E.add(hourE(new BigDecimal("51.2"))); // 12月
	}
	
	/**
	 * 根据月蒸发量计算每小时蒸发
	 * @param monthE
	 * @return
	 */
	public static BigDecimal hourE(BigDecimal monthE) {
		BigDecimal hourTotal = new BigDecimal(30 * 24);
		return monthE.divide(hourTotal, NumberConst.DIGIT, NumberConst.MODE);
	}
	
	/**
	 * WUM  上层土壤蓄水容量
	 */
	public static BigDecimal WUM = new BigDecimal(15);
	
	/**
	 * WLM  下层土壤蓄水容量
	 */
	public static BigDecimal WLM = new BigDecimal(70);
	
	/**
	 * WDM  深层土壤蓄水容量
	 */
	public static BigDecimal WDM = new BigDecimal(16);
	
	/**
	 * B  蓄水容量曲线抛物线指数
	 */
	public static BigDecimal B = new BigDecimal("0.456");
	
	/**
	 * K  蒸散发折算系数
	 */
	public static BigDecimal K = new BigDecimal("0.826");
	
	/**
	 * C  深层蒸散发系数
	 */
	public static BigDecimal C = new BigDecimal("0.141");
	
	/**
	 * SM  自由水平均蓄水容量
	 */
	public static BigDecimal SM = new BigDecimal(71);
	
	/**
	 * EX  自由水蓄水容量曲线指数
	 */
	public static BigDecimal EX = new BigDecimal("1.5");
	
	/**
	 * KSS  壤中流出流系数
	 */
	public static BigDecimal KSS = new BigDecimal("0.254");
	
	/**
	 * KG  地下径流出流系数
	 */
	public static BigDecimal KG = new BigDecimal("0.445");
	
	/**
	 * IM  不透水面积比例
	 */
	public static BigDecimal IM = new BigDecimal("0.041");
	
	/**
	 * XE  流量比重因子
	 */
	public static BigDecimal XE = new BigDecimal("0.273");
			
	/**
	 * KE  蓄量常数
	 */
	public static BigDecimal KE = new BigDecimal(18);
	
	/**
	 * DT  滞时
	 */
	public static BigDecimal DT = new BigDecimal(1);
	
	/**
	 * CS 壤中流消退系数
	 */
	public static BigDecimal CS = new BigDecimal("0.943");
	
	/**
	 * CG 地下径流消退系数
	 */
	public static BigDecimal CG = new BigDecimal("0.965");
	
	/**
	 * Ft 流域面积
	 */
	public static BigDecimal Ft = new BigDecimal(2372);
	
	
}
