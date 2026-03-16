
public class GravelCar extends RallyCar {

    public GravelCar(String make, String model, int horsepower) {
        super(make, model, horsepower);
    }
    @Override
    public double calculatePerformance() {
        // Gravel performance formula: horsepower * 1.2 + 50 (suspension bonus)
        return getHorsepower() * 1.2 + 50;
    }
}