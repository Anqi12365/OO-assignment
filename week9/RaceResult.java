import java.util.Map;
public interface RaceResult {
    void recordResult(Driver driver, int position, int points);
    String getRaceName();
    String getLocation();
    Map<Driver, int[]> getResults();
}