import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionshipStatistics {

    public static double calculateAveragePointsPerDriver(List<Driver> drivers) {
        if (drivers.isEmpty()) return 0;

        int totalPoints = 0;
        for (Driver driver : drivers) {
            totalPoints += driver.getTotalPoints();
        }
        return (double) totalPoints / drivers.size();
    }

    public static String findMostSuccessfulCountry(List<Driver> drivers) {
        if (drivers.isEmpty()) return "None";

        Map<String, Integer> countryPoints = new HashMap<>();

        for (Driver driver : drivers) {
            String country = driver.getCountry();
            int points = driver.getTotalPoints();
            countryPoints.put(country, countryPoints.getOrDefault(country, 0) + points);
        }

        String mostSuccessful = null;
        int maxPoints = -1;

        for (Map.Entry<String, Integer> entry : countryPoints.entrySet()) {
            if (entry.getValue() > maxPoints) {
                maxPoints = entry.getValue();
                mostSuccessful = entry.getKey();
            }
        }

        return mostSuccessful;
    }

    public static int countTotalRaces() {
        return ChampionshipManager.getTotalRaces();
    }
}