package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class PlanStation {
	private String stcd;
	private BigDecimal ke;
	private BigDecimal xe;
	private BigDecimal dt;
	private Integer planId;

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public BigDecimal getKe() {
		return ke;
	}

	public void setKe(BigDecimal ke) {
		this.ke = ke;
	}

	public BigDecimal getXe() {
		return xe;
	}

	public void setXe(BigDecimal xe) {
		this.xe = xe;
	}

	public BigDecimal getDt() {
		return dt;
	}

	public void setDt(BigDecimal dt) {
		this.dt = dt;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
}
