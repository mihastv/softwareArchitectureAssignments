package assignment1.bonus;

import java.time.Year;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Document Editor & Car Configuration");

        CarManagementSystem system = new CarManagementSystem();

        System.out.println(" DEMO 1: Creating Preset Cars");

        Car economyCar = system.createPresetCar("economy", "Compact SE");
        Car familyCar = system.createPresetCar("family", "Family Cruiser");
        Car sportsCar = system.createPresetCar("sports", "Turbo RS");
        Car luxuryCar = system.createPresetCar("luxury", "Executive Elite");
        Car electricCar = system.createPresetCar("electric", "Eco Drive EV");

        System.out.println();

        System.out.println(" DEMO 2: Creating Custom Car with Builder");

        CustomCarBuilder builder = new CustomCarBuilder();
        Car customCar = system.createCustomCar(
                builder
                        .setModel("Custom Roadster")
                        .setYear(Year.now().getValue())
                        .setBasePrice(48000.0)
                        .setEngine(EngineType.V6)
                        .setTransmission(TransmissionType.DUAL_CLUTCH)
                        .setColor(ExteriorColor.BLUE)
                        .setRims(RimType.SPORT_19)
                        .addInteriorFeatures(
                                InteriorFeature.LEATHER_SEATS,
                                InteriorFeature.PREMIUM_SOUND,
                                InteriorFeature.GPS_NAVIGATION
                        )
                        .addExteriorOptions(
                                ExteriorOption.SUNROOF,
                                ExteriorOption.SPOILER
                        )
                        .addSafetyFeatures(
                                SafetyFeature.REAR_CAMERA,
                                SafetyFeature.ADAPTIVE_CRUISE
                        )
        );

        System.out.println();

        System.out.println(" DEMO 3: Generating Car Documents");

        System.out.println("\n Creating PDF Specification for Luxury Car:");
        Document pdfSpec = system.createCarDocument(luxuryCar, "pdf", "specification");
        pdfSpec.display();

        System.out.println("\n Creating Word Summary for Family Car:");
        Document wordSummary = system.createCarDocument(familyCar, "word", "summary");
        wordSummary.display();

        System.out.println("\n Creating HTML Document for Sports Car:");
        Document htmlDoc = system.createCarDocument(sportsCar, "html", "table");
        htmlDoc.display();

        System.out.println("\n Creating Markdown Price Breakdown for Custom Car:");
        Document mdDoc = system.createCarDocument(customCar, "markdown", "price");
        mdDoc.display();

        System.out.println();

        System.out.println(" DEMO 4: Creating Car Order with Documents");

        CarOrder order = new CarOrder(luxuryCar, "John Smith");
        order.confirmOrder();

        Document orderPDF = order.generateOrderDocument("pdf");
        Document orderHTML = order.generateOrderDocument("html");

        System.out.println(order.getOrderSummary());

        System.out.println(" DEMO 5: Searching Inventory");

        System.out.println("\n Finding cars with V8 engine:");
        List<Car> v8Cars = system.findCarsByEngine(EngineType.V8);
        for (Car car : v8Cars) {
            System.out.println("  - " + car + " (VIN: " + car.getVin() + ")");
        }

        System.out.println("\n Finding cars in price range $30,000 - $50,000:");
        List<Car> midRangeCars = system.findCarsByPriceRange(30000, 50000);
        for (Car car : midRangeCars) {
            System.out.printf("  - %s - $%,.2f%n", car, car.getTotalPrice());
        }

        System.out.println("\n Finding cars with 'Elite' in model name:");
        List<Car> eliteCars = system.findCarsByModel("Elite");
        for (Car car : eliteCars) {
            System.out.println("  - " + car + " (VIN: " + car.getVin() + ")");
        }

        System.out.println();

        System.out.println(" DEMO 6: Inventory Summary");

        System.out.println(system.getInventorySummary());

        System.out.println(" DEMO 7: Exporting Document Content");

        System.out.println("\n Exported HTML Content (excerpt):");
        String exportedHtml = htmlDoc.exportContent();
        if (exportedHtml.length() > 500) {
            System.out.println(exportedHtml.substring(0, 500) + "\n... (truncated)");
        } else {
            System.out.println(exportedHtml);
        }

        System.out.println();

        System.out.println("Total Cars Created: " + system.getInventory().size());
        System.out.println("Total Documents Created: " + system.getDocuments().size());
    }
}