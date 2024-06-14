import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationDistanceFinder {

	static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {

		String file = "src/apiDataBP.json";
		String apiJsonData = readFileAsString(file);

		double sourceLatitude = Math.toRadians(51.0718); // driver current location ,Sample data input
		double sourceLongitude = Math.toRadians(0.315937); // driver current location, Sample data input

		String jsonData = getDistanceBetweenSource(apiJsonData, sourceLatitude, sourceLongitude);

		System.out.println("Listing  10  nearest fuel Stations");

		System.out.println(jsonData);
	}

	private static String getDistanceBetweenSource(String apiJsonData, double sourceLatitude, double sourceLongitude)
			throws JsonMappingException, JsonProcessingException {

		Map<String, Double> stationsDistanceMap = new HashMap<String, Double>();

		Root root = objectMapper.readValue(apiJsonData, Root.class);

		ArrayList<Station> stations = root.getStations();

		for (Station station : stations) {

			double destinationLatitude = Math.toRadians(station.getLocation().latitude);
			double destinationLongitude = Math.toRadians(station.getLocation().longitude);

			double distance = calculatDistanceInMiles(sourceLatitude, sourceLongitude, destinationLatitude,
					destinationLongitude);

			stationsDistanceMap.put(station.getSite_id(), distance);

		}

		System.out.println("Total number of Stations -->" + stationsDistanceMap.size());

		/*
		Map<String, Double> nearestStationsMap = stationsDistanceMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())).limit(10)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
         */
		
		List<StationDetailsResponse> nearestStationsList = stationsDistanceMap.keySet().stream()
				.map(k -> new StationDetailsResponse(k, stationsDistanceMap.get(k)))
				.sorted(Comparator.comparingDouble(StationDetailsResponse::getDistance)).collect(Collectors.toList());

		return convertToJson(nearestStationsList);
	}

	public static double calculatDistanceInMiles(double sourceLatitude, double sourceLongitude,
			double destinationLatitude, double destinationLongitude) {
		double dlon = destinationLongitude - sourceLongitude;
		double dlat = destinationLatitude - sourceLatitude;
		double a = Math.pow(Math.sin(dlat / 2), 2)
				+ Math.cos(sourceLatitude) * Math.cos(destinationLatitude) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return Math.floor(6371 * c * 0.621371 * 100) / 100;

	}

	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	public static String convertToJson(List<StationDetailsResponse> stations) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(stations);
		return jsonData;

	}
}
