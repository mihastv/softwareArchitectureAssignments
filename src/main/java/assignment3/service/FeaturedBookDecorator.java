package assignment3.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeaturedBookDecorator implements BookDecorator {

    private final BookDecorator wrappedBook;
    private static final double FEATURED_MARKUP = 1.10; // 10% markup for featured books

    public FeaturedBookDecorator(BookDecorator book) {
        this.wrappedBook = book;
        log.debug("Wrapping book as FEATURED: {}", book.getDescription());
    }

    @Override
    public String getDescription() {
        return "FEATURED: " + wrappedBook.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedBook.getPrice() * FEATURED_MARKUP;
    }

    @Override
    public String getDisplayInfo() {
        log.info("Displaying featured book info");
        return String.format("%s - $%.2f (Featured Price)", getDescription(), getPrice());
    }
}