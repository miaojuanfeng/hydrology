package gov.gz.hydrology.entity.write;

public class UserStation {
	private String userId;
	private String userStcd;
	private Station station;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserStcd() {
		return userStcd;
	}

	public void setUserStcd(String userStcd) {
		this.userStcd = userStcd;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}
