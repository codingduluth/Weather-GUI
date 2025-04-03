import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * WeatherFetcher fetches current weather data from Open-Meteo API.
 */
public class WeatherFetcher {

    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    /**
     * Fetches current weather data based on latitude and longitude.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     * @return A string containing the JSON response from the API.
     */
    public String fetchCurrentWeather(String latitude, String longitude) {
        String apiUrl = BASE_URL
                + "?latitude=" + latitude
                + "&longitude=" + longitude
                + "&current_weather=true"
                + "&temperature_unit=fahrenheit";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
