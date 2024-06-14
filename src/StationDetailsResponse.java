
public class StationDetailsResponse {

	private String stationId;
	private Double distance;

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public StationDetailsResponse(String stationId, Double distance) {
		super();
		this.stationId = stationId;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "StationDetailsResponse [stationId=" + stationId + ", distance=" + distance + "]";
	}
	
	
	
	

}
