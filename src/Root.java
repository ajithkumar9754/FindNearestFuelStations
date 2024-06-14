import java.util.ArrayList;

public class Root {
	public String last_updated;

	public ArrayList<Station> stations;

	public ArrayList<Station> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}

	@Override
	public String toString() {
		return "Root [last_updated=" + last_updated + "]";
	}

}
