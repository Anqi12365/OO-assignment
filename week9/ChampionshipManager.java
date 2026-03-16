import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChampionshipManager {
    private static ChampionshipManager instance;
    private List<Driver> drivers;
    private List<RallyRaceResult> raceResults;
    private static int totalDrivers = 0;
    private static int totalRaces = 0;
    private ChampionshipManager() {
        drivers = new ArrayList<>();
        raceResults = new ArrayList<>();
    }

    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
        totalDrivers++;
    }

    public void addRaceResult(RallyRaceResult result) {
        raceResults.add(result);
        totalRaces++;
    }

    public List<Driver> getStandings() {
        List<Driver> sortedDrivers = new ArrayList<>(drivers);
        sortedDrivers.sort((d1, d2) -> Integer.compare(d2.getTotalPoints(), d1.getTotalPoints()));
        return sortedDrivers;
    }

    public static Driver getLeadingDriver() {
        List<Driver> standings = instance.getStandings();
        return standings.isEmpty() ? null : standings.get(0);
    }

    public static int getTotalChampionshipPoints() {
        int total = 0;
        for (Driver driver : instance.drivers) {
            total += driver.getTotalPoints();
        }
        return total;
    }

    // Getters
    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<RallyRaceResult> getRaceResults() {
        return raceResults;
    }

    public static int getTotalDrivers() {
        return totalDrivers;
    }

    public static int getTotalRaces() {
        return totalRaces;
    }
}