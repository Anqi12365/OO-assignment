
public class AsphaltCar extends RallyCar {
    public AsphaltCar(String make, String model, int horsepower) {
        super(make, model, horsepower);
    }
    @Override
    public double calculatePerformance() {
        // Asphalt performance formula: horsepower * 1.5 + 30 (aerodynamics bonus)
        return getHorsepower() * 1.5 + 30;
    }
}