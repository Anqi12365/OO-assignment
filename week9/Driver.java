
public class Driver {
    private String name;
    private String country;
    private int totalPoints;
    private RallyCar car;

    public Driver(String name, String country) {
        this.name = name;
        this.country = country;
        this.totalPoints = 0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public RallyCar getCar() {
        return car;
    }

    public void setCar(RallyCar car) {
        this.car = car;
    }

    public void addPoints(int points) {
        this.totalPoints += points;
    }
}