import java.util.HashMap;
import java.util.Map;

public class RallyRaceResult implements RaceResult {
    private String raceName;
    private String location;
    private Map<Driver, int[]> results;
    public RallyRaceResult(String raceName, String location) {
        this.raceName = raceName;
        this.location = location;
        this.results = new HashMap<>();
    }

    @Override
    public void recordResult(Driver driver, int position, int points) {
        results.put(driver, new int[]{position, points});
        driver.addPoints(points);
    }

    @Override
    public String getRaceName() {
        return raceName;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public Map<Driver, int[]> getResults() {
        return results;
    }

    public int getDriverPosition(Driver driver) {
        int[] result = results.get(driver);
        return result != null ? result[0] : -1;
    }
    public int getDriverPoints(Driver driver) {
        int[] result = results.get(driver);
        return result != null ? result[1] : 0;
    }
}