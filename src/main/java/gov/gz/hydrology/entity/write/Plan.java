package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class Plan {
	private Integer id;
	private String stcd;
	private String name;
	private Integer model;
	private BigDecimal e;
	private BigDecimal wm;
	private BigDecimal wum;
	private BigDecimal wlm;
	private BigDecimal k;
	private BigDecimal c;
	private BigDecimal b;
	private BigDecimal im;
	private BigDecimal sm;
	private BigDecimal ex;
	private BigDecimal ki;
	private BigDecimal kg;
	private BigDecimal ci;
	private BigDecimal cg;
	private BigDecimal cs;
	private BigDecimal lag;
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

	public BigDecimal getE() {
		return e;
	}

	public void setE(BigDecimal e) {
		this.e = e;
	}

	public BigDecimal getWm() {
		return wm;
	}

	public void setWm(BigDecimal wm) {
		this.wm = wm;
	}

	public BigDecimal getWum() {
		return wum;
	}

	public void setWum(BigDecimal wum) {
		this.wum = wum;
	}

	public BigDecimal getWlm() {
		return wlm;
	}

	public void setWlm(BigDecimal wlm) {
		this.wlm = wlm;
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

	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	public BigDecimal getIm() {
		return im;
	}

	public void setIm(BigDecimal im) {
		this.im = im;
	}

	public BigDecimal getSm() {
		return sm;
	}

	public void setSm(BigDecimal sm) {
		this.sm = sm;
	}

	public BigDecimal getEx() {
		return ex;
	}

	public void setEx(BigDecimal ex) {
		this.ex = ex;
	}

	public BigDecimal getKi() {
		return ki;
	}

	public void setKi(BigDecimal ki) {
		this.ki = ki;
	}

	public BigDecimal getKg() {
		return kg;
	}

	public void setKg(BigDecimal kg) {
		this.kg = kg;
	}

	public BigDecimal getCi() {
		return ci;
	}

	public void setCi(BigDecimal ci) {
		this.ci = ci;
	}

	public BigDecimal getCg() {
		return cg;
	}

	public void setCg(BigDecimal cg) {
		this.cg = cg;
	}

	public BigDecimal getCs() {
		return cs;
	}

	public void setCs(BigDecimal cs) {
		this.cs = cs;
	}

	public BigDecimal getLag() {
		return lag;
	}

	public void setLag(BigDecimal lag) {
		this.lag = lag;
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
