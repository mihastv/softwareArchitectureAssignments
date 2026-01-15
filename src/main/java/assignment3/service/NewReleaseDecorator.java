package assignment3.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewReleaseDecorator implements BookDecorator {

    private final BookDecorator wrappedBook;
    private static final double NEW_RELEASE_DISCOUNT = 0.90; // 10% discount for new releases

    public NewReleaseDecorator(BookDecorator book) {
        this.wrappedBook = book;
        log.debug("Wrapping book as NEW RELEASE: {}", book.getDescription());
    }

    @Override
    public String getDescription() {
        return "NEW RELEASE: " + wrappedBook.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedBook.getPrice() * NEW_RELEASE_DISCOUNT;
    }

    @Override
    public String getDisplayInfo() {
        log.info("Displaying new release book info");
        return String.format("%s - $%.2f (New Release Discount!)", getDescription(), getPrice());
    }
}