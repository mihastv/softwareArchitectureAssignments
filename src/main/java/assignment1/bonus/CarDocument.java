package assignment1.bonus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class CarDocument implements Document {
    protected String content = "";
    protected Car car;
    protected String title;
    protected LocalDateTime createdAt;

    public CarDocument() {
        this.createdAt = LocalDateTime.now();
    }

    public void setCar(Car car) {
        this.car = car;
        this.title = car != null ? car.toString() + " - " + getFormatName() + " Document" : "Untitled";
    }

    public Car getCar() {
        return car;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String getContent() {
        return content;
    }

    protected String formatPrice(double price) {
        return String.format("$%,.2f", price);
    }

    protected String getTimestamp() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

