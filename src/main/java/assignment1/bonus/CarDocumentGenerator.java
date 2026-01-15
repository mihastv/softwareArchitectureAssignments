package assignment1.bonus;

class CarDocumentGenerator {

    public String generateSpecification(Car car) {
        StringBuilder sb = new StringBuilder();

        sb.append("VEHICLE SPECIFICATION SHEET\n");
        sb.append("===========================\n\n");

        sb.append("BASIC INFORMATION\n");
        sb.append("-----------------\n");
        sb.append("Model: ").append(car.getYear()).append(" ").append(car.getModel()).append("\n");
        sb.append("VIN: ").append(car.getVin()).append("\n\n");

        sb.append("POWERTRAIN\n");
        sb.append("Engine: ").append(car.getEngine()).append("\n");
        sb.append("Transmission: ").append(car.getTransmission()).append("\n\n");

        sb.append("EXTERIOR\n");
        sb.append("Color: ").append(car.getColor()).append("\n");
        sb.append("Rims: ").append(car.getRims()).append("\n");
        if (!car.getExteriorOptions().isEmpty()) {
            sb.append("Options:\n");
            for (ExteriorOption option : car.getExteriorOptions()) {
                sb.append("  - ").append(option.getName())
                        .append(" (").append(formatPrice(option.getPrice())).append(")\n");
            }
        }
        sb.append("\n");

        sb.append("INTERIOR FEATURES\n");
        if (car.getInteriorFeatures().isEmpty()) {
            sb.append("Standard Interior\n");
        } else {
            for (InteriorFeature feature : car.getInteriorFeatures()) {
                sb.append("  - ").append(feature.getName())
                        .append(" (").append(formatPrice(feature.getPrice())).append(")\n");
            }
        }
        sb.append("\n");

        sb.append("SAFETY FEATURES\n");
        for (SafetyFeature feature : car.getSafetyFeatures()) {
            String priceStr = feature.isStandard() ? "Standard" : formatPrice(feature.getPrice());
            sb.append("  - ").append(feature.getName())
                    .append(" (").append(priceStr).append(")\n");
        }
        sb.append("\n");

        sb.append("PRICING\n");
        sb.append("Base Price:    ").append(formatPrice(car.getBasePrice())).append("\n");
        sb.append("Options:       ").append(formatPrice(car.getOptionsPrice())).append("\n");
        sb.append("TOTAL PRICE:   ").append(formatPrice(car.getTotalPrice())).append("\n");

        return sb.toString();
    }

    public String generateSummary(Car car) {
        StringBuilder sb = new StringBuilder();

        sb.append("VEHICLE SUMMARY\n");
        sb.append("Model: ").append(car.getYear()).append(" ").append(car.getModel()).append("\n");
        sb.append("VIN: ").append(car.getVin()).append("\n");
        sb.append("Engine: ").append(car.getEngine().getName()).append("\n");
        sb.append("Transmission: ").append(car.getTransmission().getName()).append("\n");
        sb.append("Color: ").append(car.getColor()).append("\n");
        sb.append("Total Price: ").append(formatPrice(car.getTotalPrice())).append("\n");

        return sb.toString();
    }

    public String generatePriceBreakdown(Car car) {
        StringBuilder sb = new StringBuilder();

        sb.append("PRICE BREAKDOWN\n");
        sb.append("Vehicle: ").append(car.getYear()).append(" ").append(car.getModel()).append("\n");
        sb.append("VIN: ").append(car.getVin()).append("\n\n");

        sb.append("BASE PRICE\n");
        sb.append(formatPrice(car.getBasePrice())).append("\n\n");

        if (!car.getInteriorFeatures().isEmpty()) {
            sb.append("INTERIOR OPTIONS\n");
            double interiorTotal = 0;
            for (InteriorFeature feature : car.getInteriorFeatures()) {
                sb.append(String.format("%-40s %s\n", feature.getName(), formatPrice(feature.getPrice())));
                interiorTotal += feature.getPrice();
            }
            sb.append(String.format("%-40s %s\n", "Subtotal:", formatPrice(interiorTotal)));
            sb.append("\n");
        }

        if (!car.getExteriorOptions().isEmpty()) {
            sb.append("EXTERIOR OPTIONS\n");
            double exteriorTotal = 0;
            for (ExteriorOption option : car.getExteriorOptions()) {
                sb.append(String.format("%-40s %s\n", option.getName(), formatPrice(option.getPrice())));
                exteriorTotal += option.getPrice();
            }
            sb.append(String.format("%-40s %s\n", "Subtotal:", formatPrice(exteriorTotal)));
            sb.append("\n");
        }

        double safetyTotal = 0;
        boolean hasNonStandardSafety = false;
        for (SafetyFeature feature : car.getSafetyFeatures()) {
            if (!feature.isStandard()) {
                hasNonStandardSafety = true;
                safetyTotal += feature.getPrice();
            }
        }

        if (hasNonStandardSafety) {
            sb.append("SAFETY OPTIONS\n");
            for (SafetyFeature feature : car.getSafetyFeatures()) {
                if (!feature.isStandard()) {
                    sb.append(String.format("%-40s %s\n", feature.getName(), formatPrice(feature.getPrice())));
                }
            }
            sb.append(String.format("%-40s %s\n", "Subtotal:", formatPrice(safetyTotal)));
            sb.append("\n");
        }

        sb.append(String.format("%-40s %s\n", "GRAND TOTAL:", formatPrice(car.getTotalPrice())));

        return sb.toString();
    }

    public String generateHTMLTable(Car car) {
        StringBuilder sb = new StringBuilder();

        sb.append("<table>\n");
        sb.append("<tr><th colspan=\"2\">Vehicle Information</th></tr>\n");
        sb.append("<tr><td>Model</td><td>").append(car.getYear()).append(" ").append(car.getModel()).append("</td></tr>\n");
        sb.append("<tr><td>VIN</td><td>").append(car.getVin()).append("</td></tr>\n");
        sb.append("<tr><td>Engine</td><td>").append(car.getEngine()).append("</td></tr>\n");
        sb.append("<tr><td>Transmission</td><td>").append(car.getTransmission()).append("</td></tr>\n");
        sb.append("<tr><td>Color</td><td>").append(car.getColor()).append("</td></tr>\n");
        sb.append("<tr><td>Rims</td><td>").append(car.getRims()).append("</td></tr>\n");
        sb.append("<tr><th colspan=\"2\">Pricing</th></tr>\n");
        sb.append("<tr><td>Base Price</td><td>").append(formatPrice(car.getBasePrice())).append("</td></tr>\n");
        sb.append("<tr><td>Options</td><td>").append(formatPrice(car.getOptionsPrice())).append("</td></tr>\n");
        sb.append("<tr><td><strong>Total</strong></td><td class=\"price\">").append(formatPrice(car.getTotalPrice())).append("</td></tr>\n");
        sb.append("</table>");

        return sb.toString();
    }

    private String formatPrice(double price) {
        return String.format("$%,.2f", price);
    }
}