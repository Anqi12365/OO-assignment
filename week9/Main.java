import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        // 1. Get ChampionshipManager singleton instance
        ChampionshipManager manager = ChampionshipManager.getInstance();

        // 2. Create cars
        GravelCar gravelCar1 = new GravelCar("Toyota", "Yaris", 380);
        GravelCar gravelCar2 = new GravelCar("Hyundai", "i20", 375);
        AsphaltCar asphaltCar1 = new AsphaltCar("Toyota", "Yaris", 380);
        AsphaltCar asphaltCar2 = new AsphaltCar("Hyundai", "i20", 375);

        // 3. Create and register drivers
        Driver ogier = new Driver("Sébastien Ogier", "France");
        Driver rovanger = new Driver("Kalle Rovanger", "Finland");
        Driver tanak = new Driver("Ott Tänak", "Estonia");
        Driver neuville = new Driver("Thierry Neuville", "Belgium");

        // Set initial cars (gravel for first race)
        ogier.setCar(gravelCar1);
        rovanger.setCar(gravelCar2);
        tanak.setCar(gravelCar1);
        neuville.setCar(gravelCar2);

        // Register drivers
        manager.registerDriver(ogier);
        manager.registerDriver(rovanger);
        manager.registerDriver(tanak);
        manager.registerDriver(neuville);

        // 4. Simulate first race - Rally Finland (Gravel)
        System.out.println("=== Simulating Rally Finland (Gravel) ===");
        RallyRaceResult race1 = new RallyRaceResult("Rally Finland", "Jyväskylä");

        // Record results for first race
        race1.recordResult(ogier, 1, 25);
        race1.recordResult(tanak, 2, 18);
        race1.recordResult(rovanger, 3, 15);
        race1.recordResult(neuville, 4, 12);

        manager.addRaceResult(race1);
        System.out.println("Race 1 completed!\n");

        System.out.println("=== Switching cars for Monte Carlo (Asphalt) ===");
        ogier.setCar(asphaltCar1);
        rovanger.setCar(asphaltCar2);
        tanak.setCar(asphaltCar1);
        neuville.setCar(asphaltCar2);

        System.out.println("\n=== Simulating Monte Carlo Rally (Asphalt) ===");
        RallyRaceResult race2 = new RallyRaceResult("Monte Carlo Rally", "Monaco");


        race2.recordResult(rovanger, 1, 25);
        race2.recordResult(neuville, 2, 18);
        race2.recordResult(ogier, 3, 15);
        race2.recordResult(tanak, 4, 12);

        manager.addRaceResult(race2);
        System.out.println("Race 2 completed!\n");

        // 5. Display championship standings
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CHAMPIONSHIP STANDINGS");
        System.out.println("=".repeat(50));

        List<Driver> standings = manager.getStandings();
        for (int i = 0; i < standings.size(); i++) {
            Driver d = standings.get(i);
            System.out.printf("%d. %s (%s): %d points%n",
                    i + 1, d.getName(), d.getCountry(), d.getTotalPoints());
        }

        // 6. Display championship leader
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CHAMPIONSHIP LEADER");
        System.out.println("=".repeat(50));

        Driver leader = ChampionshipManager.getLeadingDriver();
        System.out.printf("%s with %d points%n",
                leader.getName(), leader.getTotalPoints());

        // 7. Display championship statistics
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CHAMPIONSHIP STATISTICS");
        System.out.println("=".repeat(50));

        System.out.printf("Total Drivers: %d%n", ChampionshipManager.getTotalDrivers());
        System.out.printf("Total Races: %d%n", ChampionshipManager.getTotalRaces());
        System.out.printf("Average Points Per Driver: %.2f%n",
                ChampionshipStatistics.calculateAveragePointsPerDriver(manager.getDrivers()));
        System.out.printf("Most Successful Country: %s%n",
                ChampionshipStatistics.findMostSuccessfulCountry(manager.getDrivers()));
        System.out.printf("Total Championship Points: %d%n",
                ChampionshipManager.getTotalChampionshipPoints());

        System.out.println("\n" + "=".repeat(50));
        System.out.println("RACE RESULTS");
        System.out.println("=".repeat(50));

        for (RallyRaceResult race : manager.getRaceResults()) {
            System.out.printf("Race: %s (%s)%n", race.getRaceName(), race.getLocation());

            Map<Driver, int[]> results = race.getResults();

            results.entrySet().stream()
                    .sorted((e1, e2) -> Integer.compare(e1.getValue()[0], e2.getValue()[0]))
                    .forEach(entry -> {
                        int position = entry.getValue()[0];
                        int points = entry.getValue()[1];
                        System.out.printf("  Position %d: %s - %d points%n",
                                position, entry.getKey().getName(), points);
                    });
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("CAR PERFORMANCE RATINGS");
        System.out.println("=".repeat(50));

        System.out.printf("Gravel Car Performance: %.1f%n", gravelCar1.calculatePerformance());
        System.out.printf("Asphalt Car Performance: %.1f%n", asphaltCar1.calculatePerformance());
    }
}