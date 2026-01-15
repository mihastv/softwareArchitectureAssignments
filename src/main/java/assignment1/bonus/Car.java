package assignment1.bonus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class Car {
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
}

