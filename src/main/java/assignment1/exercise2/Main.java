package assignment1.exercise2;

public class Main {
    public static void main(String[] args) {
        System.out.println("CAR CONFIGURATION SYSTEM DEMO");

        System.out.println("\n▶ DEMO 1: Custom Car Configuration (Builder Pattern)");
        System.out.println("─".repeat(60));

        CustomCarBuilder builder = new CustomCarBuilder();

        Car customCar = builder
                .setModel("Speedster GT")
                .setYear(2026)
                .setBasePrice(42000.0)
                .setEngine(EngineType.V6)
                .setTransmission(TransmissionType.AUTOMATIC_8_SPEED)
                .setColor(ExteriorColor.RED)
                .setRims(RimType.SPORT_19)
                .addInteriorFeatures(
                        InteriorFeature.LEATHER_SEATS,
                        InteriorFeature.GPS_NAVIGATION,
                        InteriorFeature.PREMIUM_SOUND,
                        InteriorFeature.HEATED_SEATS
                )
                .addExteriorOptions(
                        ExteriorOption.SUNROOF,
                        ExteriorOption.SPOILER,
                        ExteriorOption.TINTED_WINDOWS
                )
                .addSafetyFeatures(
                        SafetyFeature.REAR_CAMERA,
                        SafetyFeature.BLIND_SPOT_MONITOR,
                        SafetyFeature.ADAPTIVE_CRUISE
                )
                .build();

        System.out.println(customCar.getFullSpecification());

        System.out.println("\nDEMO 2: Preset Configurations (Director Pattern)");
        System.out.println("─".repeat(60));

        CarConfigurationDirector director = new CarConfigurationDirector(new CustomCarBuilder());

        System.out.println("\n Economy Car Configuration:");
        Car economyCar = director.buildEconomyCar("Compact SE");
        System.out.println(economyCar);
        System.out.printf("Total Price: $%,.2f%n", economyCar.getTotalPrice());

        System.out.println("\n Family Car Configuration:");
        Car familyCar = director.buildFamilyCar("Family Cruiser");
        System.out.println(familyCar);
        System.out.printf("Total Price: $%,.2f%n", familyCar.getTotalPrice());

        System.out.println("\n Sports Car Configuration:");
        Car sportsCar = director.buildSportsCar("Turbo RS");
        System.out.println(sportsCar);
        System.out.printf("Total Price: $%,.2f%n", sportsCar.getTotalPrice());

        System.out.println("\n>>> Luxury Car Configuration:");
        Car luxuryCar = director.buildLuxuryCar("Executive Elite");
        System.out.println(luxuryCar.getFullSpecification());

        System.out.println("\n Electric Car Configuration:");
        Car electricCar = director.buildElectricCar("Eco Drive EV");
        System.out.println(electricCar);
        System.out.printf("Total Price: $%,.2f%n", electricCar.getTotalPrice());

        System.out.println("\n DEMO 3: Creating an Order");

        CarOrder order = new CarOrder(luxuryCar, "John Smith");
        order.confirmOrder();
        System.out.println(order.getOrderSummary());

        System.out.println("\n DEMO 4: Configuration Validation");
        System.out.println("─".repeat(60));

        try {
            System.out.println("Attempting invalid configuration (Electric + Manual)...");
            new CustomCarBuilder()
                    .setModel("Invalid Car")
                    .setEngine(EngineType.ELECTRIC)
                    .setTransmission(TransmissionType.MANUAL_6_SPEED)
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }

        System.out.println("\n DEMO 5: Builder Reuse");

        builder.reset();
        Car car1 = builder
                .setModel("Model A")
                .setEngine(EngineType.V6)
                .setColor(ExteriorColor.BLUE)
                .build();

        builder.reset();
        Car car2 = builder
                .setModel("Model B")
                .setEngine(EngineType.V8)
                .setColor(ExteriorColor.BLACK)
                .build();

        System.out.println("Car 1: " + car1 + " - " + car1.getEngine());
        System.out.println("Car 2: " + car2 + " - " + car2.getEngine());
        System.out.println("Different VINs: " + !car1.getVin().equals(car2.getVin()));
    }
}
