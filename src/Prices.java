import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Prices {

	@JsonProperty("E10")
	public double e10;
	@JsonProperty("E5")
	public double e5;
	@JsonProperty("B7")
	public double b7;
}