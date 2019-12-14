package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class Plan {
	private Integer id;
	private String stcd;
	private String name;
	private Integer model;
	/**
	 * WU0  上层土壤蓄水容量初始含水量
	 */
	private BigDecimal WU0;
	/**
	 * WL0  下层土壤蓄水容量初始含水量
	 */
	private BigDecimal WL0;
	/**
	 * WD0  深层土壤蓄水容量初始含水量
	 */
	private BigDecimal WD0;
	/**
	 * WUM  上层土壤蓄水容量
	 */
	private BigDecimal WUM;
	/**
	 * WLM  下层土壤蓄水容量
	 */
	private BigDecimal WLM;
	/**
	 * WDM  深层土壤蓄水容量
	 */
	private BigDecimal WDM;
	/**
	 * B  蓄水容量曲线抛物线指数
	 */
	private BigDecimal b;
	/**
	 * K  蒸散发折算系数
	 */
	private BigDecimal k;
	/**
	 * C  深层蒸散发系数
	 */
	private BigDecimal c;
	/**
	 * SM  自由水平均蓄水容量
	 */
	private BigDecimal SM;
	/**
	 * EX  自由水蓄水容量曲线指数
	 */
	private BigDecimal EX;
	/**
	 * KSS  壤中流出流系数
	 */
	private BigDecimal KSS;
	/**
	 * KG  地下径流出流系数
	 */
	private BigDecimal KG;
	/**
	 * IM  不透水面积比例
	 */
	private BigDecimal IM;
	///////////////////////////////////////////////////////////////////从数据库另一张表中读
	/**
	 * XE  流量比重因子
	 */
	private BigDecimal XE;
	/**
	 * KE  蓄量常数(传播时间)
	 */
	private BigDecimal KE;
	//////////////////////////////////////////////////////////////////从数据库另一张表中读
	/**
	 * CS 河网蓄水消退系数
	 */
	private BigDecimal CS;
	/**
	 * CI 壤中流消退系数
	 */
	private BigDecimal CI;
	/**
	 * CG 地下径流消退系数
	 */
	private BigDecimal CG;
	/**
	 * L 滞时
	 */
	private Integer l;
	/**
	 * T 时段长, 变量
	 */
	private BigDecimal t;
	/**
	 * F 流域面积
	 */
	private BigDecimal f;

	private BigDecimal s0;
	private BigDecimal FR0;
	private BigDecimal QRs0;
	private BigDecimal QRss0;
	private BigDecimal QRg0;
	private String userId;
	private Date createTime;
	private String stname;
	private String userName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public BigDecimal getWU0() {
		return WU0;
	}

	public void setWU0(BigDecimal WU0) {
		this.WU0 = WU0;
	}

	public BigDecimal getWL0() {
		return WL0;
	}

	public void setWL0(BigDecimal WL0) {
		this.WL0 = WL0;
	}

	public BigDecimal getWD0() {
		return WD0;
	}

	public void setWD0(BigDecimal WD0) {
		this.WD0 = WD0;
	}

	public BigDecimal getWUM() {
		return WUM;
	}

	public void setWUM(BigDecimal WUM) {
		this.WUM = WUM;
	}

	public BigDecimal getWLM() {
		return WLM;
	}

	public void setWLM(BigDecimal WLM) {
		this.WLM = WLM;
	}

	public BigDecimal getWDM() {
		return WDM;
	}

	public void setWDM(BigDecimal WDM) {
		this.WDM = WDM;
	}

	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	public BigDecimal getK() {
		return k;
	}

	public void setK(BigDecimal k) {
		this.k = k;
	}

	public BigDecimal getC() {
		return c;
	}

	public void setC(BigDecimal c) {
		this.c = c;
	}

	public BigDecimal getSM() {
		return SM;
	}

	public void setSM(BigDecimal SM) {
		this.SM = SM;
	}

	public BigDecimal getEX() {
		return EX;
	}

	public void setEX(BigDecimal EX) {
		this.EX = EX;
	}

	public BigDecimal getKSS() {
		return KSS;
	}

	public void setKSS(BigDecimal KSS) {
		this.KSS = KSS;
	}

	public BigDecimal getKG() {
		return KG;
	}

	public void setKG(BigDecimal KG) {
		this.KG = KG;
	}

	public BigDecimal getIM() {
		return IM;
	}

	public void setIM(BigDecimal IM) {
		this.IM = IM;
	}

	public BigDecimal getXE() {
		return XE;
	}

	public void setXE(BigDecimal XE) {
		this.XE = XE;
	}

	public BigDecimal getKE() {
		return KE;
	}

	public void setKE(BigDecimal KE) {
		this.KE = KE;
	}

	public BigDecimal getCS() {
		return CS;
	}

	public void setCS(BigDecimal CS) {
		this.CS = CS;
	}

	public BigDecimal getCI() {
		return CI;
	}

	public void setCI(BigDecimal CI) {
		this.CI = CI;
	}

	public BigDecimal getCG() {
		return CG;
	}

	public void setCG(BigDecimal CG) {
		this.CG = CG;
	}

	public Integer getL() {
		return l;
	}

	public void setL(Integer l) {
		this.l = l;
	}

	public BigDecimal getT() {
		return t;
	}

	public void setT(BigDecimal t) {
		this.t = t;
	}

	public BigDecimal getF() {
		return f;
	}

	public void setF(BigDecimal f) {
		this.f = f;
	}

	public BigDecimal getS0() {
		return s0;
	}

	public void setS0(BigDecimal s0) {
		this.s0 = s0;
	}

	public BigDecimal getFR0() {
		return FR0;
	}

	public void setFR0(BigDecimal FR0) {
		this.FR0 = FR0;
	}

	public BigDecimal getQRs0() {
		return QRs0;
	}

	public void setQRs0(BigDecimal QRs0) {
		this.QRs0 = QRs0;
	}

	public BigDecimal getQRss0() {
		return QRss0;
	}

	public void setQRss0(BigDecimal QRss0) {
		this.QRss0 = QRss0;
	}

	public BigDecimal getQRg0() {
		return QRg0;
	}

	public void setQRg0(BigDecimal QRg0) {
		this.QRg0 = QRg0;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
