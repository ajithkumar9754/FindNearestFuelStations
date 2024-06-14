import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Station {

	public String site_id;
	public String brand;
	public String address;
	public String postcode;
	public Location location;
	public Prices prices;

	@Override
	public String toString() {
		return "Station [site_id=" + site_id + ", location=" + location + "]";
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	
	
	
}