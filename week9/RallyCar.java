
public abstract class RallyCar {
    private String make;
    private String model;
    private int horsepower;

    public RallyCar(String make, String model, int horsepower) {
        this.make = make;
        this.model = model;
        this.horsepower = horsepower;
    }

    // Getters
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getHorsepower() {
        return horsepower;
    }


    public abstract double calculatePerformance();
}