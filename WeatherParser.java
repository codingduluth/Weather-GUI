public class WeatherParser {

    public static double extractTemperature(String json) {
        try {

            String currentWeatherSection = json.substring(json.indexOf("\"current_weather\""));
            String key = "\"temperature\":";
            int start = currentWeatherSection.indexOf(key) + key.length();
            int end = currentWeatherSection.indexOf(",", start);
            String tempString = currentWeatherSection.substring(start, end).trim();
            return Double.parseDouble(tempString);
        } catch (Exception e) {
            System.out.println("Could not extract temperature.");
            return Double.NaN;
        }
    }

    public static String extractCondition(String json) {
        try {
            String currentWeatherSection = json.substring(json.indexOf("\"current_weather\""));
            String key = "\"weathercode\":";
            int start = currentWeatherSection.indexOf(key) + key.length();
            int end = currentWeatherSection.indexOf("}", start);
            int code = Integer.parseInt(currentWeatherSection.substring(start, end).trim());
            return decodeWeatherCode(code);
        } catch (Exception e) {
            System.out.println("Could not extract weather condition.");
            return "Unknown";
        }
    }

    public static String decodeWeatherCode(int code) {
    switch (code) {
        case 0:
            return "Clear sky";
        case 1:
            return "Mainly clear";
        case 2:
            return "Partly cloudy";
        case 3:
            return "Overcast";
        case 45: case 48:
            return "Fog";
        case 51: case 53: case 55:
            return "Drizzle";
        case 61: case 63: case 65:
            return "Rain";
        case 66: case 67:
            return "Freezing rain";
        case 71: case 73: case 75:
            return "Snow fall";
        case 80: case 81: case 82:
            return "Rain showers";
        case 95:
            return "Thunderstorm";
        default:
            return "Unknown";
    }
}
}
