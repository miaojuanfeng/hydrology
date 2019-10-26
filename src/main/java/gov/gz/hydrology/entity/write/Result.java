package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;
import java.util.Date;

public class Result {
	private String stcd;
	private Date foreTime;
	private Date flTime;
	private BigDecimal flow;
	private String userId;
	private String fileCd;

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Date getForeTime() {
		return foreTime;
	}

	public void setForeTime(Date foreTime) {
		this.foreTime = foreTime;
	}

	public Date getFlTime() {
		return flTime;
	}

	public void setFlTime(Date flTime) {
		this.flTime = flTime;
	}

	public BigDecimal getFlow() {
		return flow;
	}

	public void setFlow(BigDecimal flow) {
		this.flow = flow;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileCd() {
		return fileCd;
	}

	public void setFileCd(String fileCd) {
		this.fileCd = fileCd;
	}
}
