package assignment3.service;

import assignment3.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BasicBook implements BookDecorator {

    private final Book book;

    @Override
    public String getDescription() {
        return book.getTitle() + " by " + book.getAuthor();
    }

    @Override
    public double getPrice() {
        return book.getPrice();
    }

    @Override
    public String getDisplayInfo() {
        log.debug("Getting display info for basic book: {}", book.getTitle());
        return String.format("%s - $%.2f", getDescription(), getPrice());
    }
}