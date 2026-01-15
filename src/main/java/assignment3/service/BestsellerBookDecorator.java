package assignment3.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BestsellerBookDecorator implements BookDecorator {

    private final BookDecorator wrappedBook;
    private static final double BESTSELLER_MARKUP = 1.15; // 15% markup for bestsellers

    public BestsellerBookDecorator(BookDecorator book) {
        this.wrappedBook = book;
        log.debug("Wrapping book as BESTSELLER: {}", book.getDescription());
    }

    @Override
    public String getDescription() {
        return "BESTSELLER: " + wrappedBook.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedBook.getPrice() * BESTSELLER_MARKUP;
    }

    @Override
    public String getDisplayInfo() {
        log.info("Displaying bestseller book info");
        return String.format("%s - $%.2f (Bestseller Price)", getDescription(), getPrice());
    }
}