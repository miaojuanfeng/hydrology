package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;

public class ModelStation {

	private Integer id;

	private Integer modelId;

	private String moStcd;

	private Integer planId;

	private BigDecimal ke;

	private BigDecimal xe;

	private Integer clId;

	private Integer hlId;

	private String faStcd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getMoStcd() {
		return moStcd;
	}

	public void setMoStcd(String moStcd) {
		this.moStcd = moStcd;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
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

	public Integer getClId() {
		return clId;
	}

	public void setClId(Integer clId) {
		this.clId = clId;
	}

	public Integer getHlId() {
		return hlId;
	}

	public void setHlId(Integer hlId) {
		this.hlId = hlId;
	}

	public String getFaStcd() {
		return faStcd;
	}

	public void setFaStcd(String faStcd) {
		this.faStcd = faStcd;
	}
}
