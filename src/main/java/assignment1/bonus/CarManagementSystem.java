package assignment1.bonus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CarManagementSystem {
    private final List<Car> inventory;
    private final List<Document> documents;
    private final CarConfigurationDirector configurator;
    private final CarDocumentGenerator documentGenerator;

    public CarManagementSystem() {
        this.inventory = new ArrayList<>();
        this.documents = new ArrayList<>();
        this.configurator = new CarConfigurationDirector(new CustomCarBuilder());
        this.documentGenerator = new CarDocumentGenerator();
    }

    public void addCar(Car car) {
        inventory.add(car);
        System.out.println("[System] Added car to inventory: " + car);
    }

    public Car createCustomCar(CarBuilder builder) {
        Car car = builder.build();
        addCar(car);
        return car;
    }

    public Car createPresetCar(String type, String model) {
        Car car;
        switch (type.toLowerCase()) {
            case "economy":
                car = configurator.buildEconomyCar(model);
                break;
            case "family":
                car = configurator.buildFamilyCar(model);
                break;
            case "sports":
                car = configurator.buildSportsCar(model);
                break;
            case "luxury":
                car = configurator.buildLuxuryCar(model);
                break;
            case "electric":
                car = configurator.buildElectricCar(model);
                break;
            default:
                throw new IllegalArgumentException("Unknown car type: " + type);
        }
        addCar(car);
        return car;
    }

    public Document createCarDocument(Car car, String documentType, String contentType) {
        Document doc = DocumentFactory.createDocument(documentType);

        if (doc instanceof CarDocument) {
            ((CarDocument) doc).setCar(car);
        }

        doc.open();

        String content;
        switch (contentType.toLowerCase()) {
            case "specification":
            case "spec":
                content = documentGenerator.generateSpecification(car);
                break;
            case "summary":
                content = documentGenerator.generateSummary(car);
                break;
            case "price":
            case "pricing":
                content = documentGenerator.generatePriceBreakdown(car);
                break;
            case "table":
            case "html_table":
                content = documentGenerator.generateHTMLTable(car);
                break;
            default:
                content = documentGenerator.generateSpecification(car);
        }

        doc.save(content);
        documents.add(doc);

        return doc;
    }

    public List<Document> createDocumentsForAllCars(String documentType, String contentType) {
        List<Document> createdDocs = new ArrayList<>();
        for (Car car : inventory) {
            Document doc = createCarDocument(car, documentType, contentType);
            createdDocs.add(doc);
        }
        return createdDocs;
    }

    public List<Car> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public List<Document> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    public List<Car> findCarsByModel(String modelName) {
        List<Car> results = new ArrayList<>();
        for (Car car : inventory) {
            if (car.getModel().toLowerCase().contains(modelName.toLowerCase())) {
                results.add(car);
            }
        }
        return results;
    }

    public List<Car> findCarsByPriceRange(double minPrice, double maxPrice) {
        List<Car> results = new ArrayList<>();
        for (Car car : inventory) {
            double price = car.getTotalPrice();
            if (price >= minPrice && price <= maxPrice) {
                results.add(car);
            }
        }
        return results;
    }

    public List<Car> findCarsByEngine(EngineType engineType) {
        List<Car> results = new ArrayList<>();
        for (Car car : inventory) {
            if (car.getEngine() == engineType) {
                results.add(car);
            }
        }
        return results;
    }

    public String getInventorySummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("INVENTORY SUMMARY\n");

        sb.append("Total Cars: ").append(inventory.size()).append("\n");
        sb.append("Total Documents: ").append(documents.size()).append("\n\n");

        if (!inventory.isEmpty()) {
            sb.append("CARS IN INVENTORY:\n");

            double totalValue = 0;
            for (int i = 0; i < inventory.size(); i++) {
                Car car = inventory.get(i);
                sb.append(String.format("%d. %-30s VIN: %-15s Price: $%,.2f\n",
                        i + 1, car.toString(), car.getVin(), car.getTotalPrice()));
                totalValue += car.getTotalPrice();
            }

            sb.append(String.format("Total Inventory Value: $%,.2f\n", totalValue));
        }

        return sb.toString();
    }

    public void clearAll() {
        inventory.clear();
        documents.clear();
        System.out.println("[System] Inventory and documents cleared.");
    }
}

