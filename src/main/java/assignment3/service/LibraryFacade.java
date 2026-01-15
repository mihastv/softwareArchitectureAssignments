package assignment3.service;

import assignment3.dto.BookDto;
import assignment3.entity.Book;
import assignment3.utils.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LibraryFacade {

    private final BookService bookService;

    public BookDto addBook(BookDto bookDto) {
        log.info("FACADE: Adding book to library: {}", bookDto.getTitle());

        Book book = BookMapper.toEntity(bookDto);
        Book savedBook = bookService.addBook(book);

        return BookMapper.toDTO(savedBook);
    }

    public List<BookDto> getAllBooks() {
        log.info("FACADE: Retrieving all books");

        return bookService.getAllBooks()
                .stream()
                .map(this::createDecoratedDto)
                .collect(Collectors.toList());
    }

    public BookDto getBookById(Long id) {
        log.info("FACADE: Retrieving book by ID: {}", id);

        return bookService.getBookById(id)
                .map(this::createDecoratedDto)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        log.info("FACADE: Updating book with ID: {}", id);

        Book book = BookMapper.toEntity(bookDto);
        Book updatedBook = bookService.updateBook(id, book);

        return createDecoratedDto(updatedBook);
    }

    public void deleteBook(Long id) {
        log.info("FACADE: Deleting book with ID: {}", id);
        bookService.deleteBook(id);
    }

    public List<BookDto> getFeaturedBooks() {
        log.info("FACADE: Retrieving featured books");

        return bookService.getFeaturedBooks()
                .stream()
                .map(this::createDecoratedDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBestsellerBooks() {
        log.info("FACADE: Retrieving bestseller books");

        return bookService.getBestsellerBooks()
                .stream()
                .map(this::createDecoratedDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksByCategory(String category) {
        log.info("FACADE: Retrieving books by category: {}", category);

        return bookService.getBooksByCategory(category)
                .stream()
                .map(this::createDecoratedDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> searchByAuthor(String author) {
        log.info("FACADE: Searching books by author: {}", author);

        return bookService.searchByAuthor(author)
                .stream()
                .map(this::createDecoratedDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> searchByTitle(String title) {
        log.info("FACADE: Searching books by title: {}", title);

        return bookService.searchByTitle(title)
                .stream()
                .map(this::createDecoratedDto)
                .collect(Collectors.toList());
    }

    public BookDto markAsFeatured(Long id) {
        log.info("FACADE: Marking book {} as featured", id);

        Book book = bookService.markAsFeatured(id);
        return createDecoratedDto(book);
    }

    public BookDto markAsBestseller(Long id) {
        log.info("FACADE: Marking book {} as bestseller", id);

        Book book = bookService.markAsBestseller(id);
        return createDecoratedDto(book);
    }

    private BookDto createDecoratedDto(Book book) {
        BookDecorator decoratedBook = bookService.decorateBook(book);

        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .category(book.getCategory())
                .featured(book.isFeatured())
                .bestseller(book.isBestseller())
                .description(decoratedBook.getDescription())
                .displayPrice(String.format("$%.2f", decoratedBook.getPrice()))
                .build();
    }

    public LibraryStats getLibraryStats() {
        log.info("FACADE: Getting library statistics");

        List<Book> allBooks = bookService.getAllBooks();
        List<Book> featured = bookService.getFeaturedBooks();
        List<Book> bestsellers = bookService.getBestsellerBooks();

        double totalValue = allBooks.stream()
                .mapToDouble(Book::getPrice)
                .sum();

        double averagePrice = allBooks.isEmpty() ? 0 : totalValue / allBooks.size();

        return LibraryStats.builder()
                .totalBooks(allBooks.size())
                .featuredBooks(featured.size())
                .bestsellerBooks(bestsellers.size())
                .totalValue(totalValue)
                .averagePrice(averagePrice)
                .build();
    }

    @lombok.Data
    @lombok.Builder
    public static class LibraryStats {
        private int totalBooks;
        private int featuredBooks;
        private int bestsellerBooks;
        private double totalValue;
        private double averagePrice;
    }
}