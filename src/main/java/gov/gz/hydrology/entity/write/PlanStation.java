package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class PlanStation {
	private Integer planId;
	private String poStcd;
	private BigDecimal ke;
	private BigDecimal xe;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPoStcd() {
		return poStcd;
	}

	public void setPoStcd(String poStcd) {
		this.poStcd = poStcd;
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
}
