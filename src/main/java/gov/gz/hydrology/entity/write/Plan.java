package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class Plan {
	/**
	 * 业务数据
	 */
	private Integer id;
	private String stcd;
	private String name;
	private Integer model;
	private String userId;
	private String userName;
	private String stname;
	private Date createTime;
	/**
	 * 计算参数
	 */
	private BigDecimal WU0;
	private BigDecimal WL0;
	private BigDecimal WD0;
	private BigDecimal WUM;
	private BigDecimal WLM;
	private BigDecimal WDM;
	private BigDecimal b;
	private BigDecimal k;
	private BigDecimal c;
	private BigDecimal SM;
	private BigDecimal EX;
	private BigDecimal KSS;
	private BigDecimal KG;
	private BigDecimal IM;
	private BigDecimal XE;
	private BigDecimal KE;
	private BigDecimal CS;
	private BigDecimal CI;
	private BigDecimal CG;
	private Integer l;
	private BigDecimal t;
	private BigDecimal f;
	private BigDecimal S0;
	private BigDecimal FR0;
	private BigDecimal QRS0;
	private BigDecimal QRSS0;
	private BigDecimal QRG0;
	private BigDecimal INTV;
	private BigDecimal Pa;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
		return S0;
	}

	public void setS0(BigDecimal s0) {
		S0 = s0;
	}

	public BigDecimal getFR0() {
		return FR0;
	}

	public void setFR0(BigDecimal FR0) {
		this.FR0 = FR0;
	}

	public BigDecimal getQRS0() {
		return QRS0;
	}

	public void setQRS0(BigDecimal QRS0) {
		this.QRS0 = QRS0;
	}

	public BigDecimal getQRSS0() {
		return QRSS0;
	}

	public void setQRSS0(BigDecimal QRSS0) {
		this.QRSS0 = QRSS0;
	}

	public BigDecimal getQRG0() {
		return QRG0;
	}

	public void setQRG0(BigDecimal QRG0) {
		this.QRG0 = QRG0;
	}

	public BigDecimal getINTV() {
		return INTV;
	}

	public void setINTV(BigDecimal INTV) {
		this.INTV = INTV;
	}

	public BigDecimal getPa() {
		return Pa;
	}

	public void setPa(BigDecimal pa) {
		Pa = pa;
	}
}
