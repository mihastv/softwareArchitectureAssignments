package assignment1.exercise2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Car {
    private final String model;
    private final int year;
    private final EngineType engine;
    private final TransmissionType transmission;
    private final ExteriorColor color;
    private final RimType rims;
    private final Set<InteriorFeature> interiorFeatures;
    private final Set<ExteriorOption> exteriorOptions;
    private final Set<SafetyFeature> safetyFeatures;
    private final double basePrice;
    private final String vin;

    Car(String model, int year, EngineType engine, TransmissionType transmission,
        ExteriorColor color, RimType rims, Set<InteriorFeature> interiorFeatures,
        Set<ExteriorOption> exteriorOptions, Set<SafetyFeature> safetyFeatures,
        double basePrice) {
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.transmission = transmission;
        this.color = color;
        this.rims = rims;
        this.interiorFeatures = Collections.unmodifiableSet(new HashSet<>(interiorFeatures));
        this.exteriorOptions = Collections.unmodifiableSet(new HashSet<>(exteriorOptions));
        this.safetyFeatures = Collections.unmodifiableSet(new HashSet<>(safetyFeatures));
        this.basePrice = basePrice;
        this.vin = generateVIN();
    }

    private String generateVIN() {
        return "VIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public String getModel() { return model; }
    public int getYear() { return year; }
    public EngineType getEngine() { return engine; }
    public TransmissionType getTransmission() { return transmission; }
    public ExteriorColor getColor() { return color; }
    public RimType getRims() { return rims; }
    public Set<InteriorFeature> getInteriorFeatures() { return interiorFeatures; }
    public Set<ExteriorOption> getExteriorOptions() { return exteriorOptions; }
    public Set<SafetyFeature> getSafetyFeatures() { return safetyFeatures; }
    public double getBasePrice() { return basePrice; }
    public String getVin() { return vin; }

    public double getTotalPrice() {
        double total = basePrice;

        for (InteriorFeature feature : interiorFeatures) {
            total += feature.getPrice();
        }
        for (ExteriorOption option : exteriorOptions) {
            total += option.getPrice();
        }
        for (SafetyFeature feature : safetyFeatures) {
            total += feature.getPrice();
        }

        return total;
    }

    public double getOptionsPrice() {
        return getTotalPrice() - basePrice;
    }

    @Override
    public String toString() {
        return String.format("%d %s", year, model);
    }

    public String getFullSpecification() {
        StringBuilder sb = new StringBuilder();
        String separator = "═".repeat(60);

        sb.append("\n").append(separator).append("\n");
        sb.append("  CAR CONFIGURATION SUMMARY\n");
        sb.append(separator).append("\n\n");

        sb.append(String.format("  Model: %d %s\n", year, model));
        sb.append(String.format("  VIN: %s\n\n", vin));

        sb.append("  POWERTRAIN\n");
        sb.append("  ──────────────────────────────────────\n");
        sb.append(String.format("  Engine: %s\n", engine));
        sb.append(String.format("  Transmission: %s\n\n", transmission));

        sb.append("  EXTERIOR\n");
        sb.append("  ──────────────────────────────────────\n");
        sb.append(String.format("  Color: %s\n", color));
        sb.append(String.format("  Rims: %s\n", rims));
        if (!exteriorOptions.isEmpty()) {
            sb.append("  Options:\n");
            for (ExteriorOption option : exteriorOptions) {
                sb.append(String.format("    • %s ($%.2f)\n", option.getName(), option.getPrice()));
            }
        }
        sb.append("\n");

        sb.append("  INTERIOR\n");
        sb.append("  ──────────────────────────────────────\n");
        if (interiorFeatures.isEmpty()) {
            sb.append("  Standard Interior\n");
        } else {
            for (InteriorFeature feature : interiorFeatures) {
                sb.append(String.format("    • %s ($%.2f)\n", feature.getName(), feature.getPrice()));
            }
        }
        sb.append("\n");

        sb.append("  SAFETY FEATURES\n");
        sb.append("  ──────────────────────────────────────\n");
        for (SafetyFeature feature : safetyFeatures) {
            String priceStr = feature.isStandard() ? "Standard" : String.format("$%.2f", feature.getPrice());
            sb.append(String.format("    • %s (%s)\n", feature.getName(), priceStr));
        }
        sb.append("\n");

        sb.append("  PRICING\n");
        sb.append("  ──────────────────────────────────────\n");
        sb.append(String.format("  Base Price:    $%,.2f\n", basePrice));
        sb.append(String.format("  Options:       $%,.2f\n", getOptionsPrice()));
        sb.append(String.format("  ─────────────────────────\n"));
        sb.append(String.format("  TOTAL PRICE:   $%,.2f\n", getTotalPrice()));

        sb.append("\n").append(separator).append("\n");

        return sb.toString();
    }
}
