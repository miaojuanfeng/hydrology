package gov.gz.hydrology.entity.write;

public class Station {
	private String stcd;
	private String type;
	private String stname;
	private String dis;
	private String wea;
	private String fileCd;
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}

	public String getWea() {
		return wea;
	}

	public void setWea(String wea) {
		this.wea = wea;
	}

	public String getFileCd() {
		return fileCd;
	}

	public void setFileCd(String fileCd) {
		this.fileCd = fileCd;
	}
}
