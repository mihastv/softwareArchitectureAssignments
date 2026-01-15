package assignment3.service;

import assignment3.entity.Book;
import assignment3.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        log.debug("Fetching all books");
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        log.debug("Fetching book by ID: {}", id);
        return bookRepository.findById(id);
    }

    @Transactional
    public Book addBook(Book book) {
        log.info("Adding new book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, Book updatedBook) {
        log.info("Updating book with ID: {}", id);

        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setPrice(updatedBook.getPrice());
                    existingBook.setCategory(updatedBook.getCategory());
                    existingBook.setFeatured(updatedBook.isFeatured());
                    existingBook.setBestseller(updatedBook.isBestseller());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    @Transactional
    public void deleteBook(Long id) {
        log.info("Deleting book with ID: {}", id);

        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByCategory(String category) {
        log.debug("Fetching books by category: {}", category);
        return bookRepository.findByCategory(category);
    }

    public List<Book> getFeaturedBooks() {
        log.debug("Fetching featured books");
        return bookRepository.findByFeaturedTrue();
    }

    public List<Book> getBestsellerBooks() {
        log.debug("Fetching bestseller books");
        return bookRepository.findByBestsellerTrue();
    }

    public List<Book> searchByAuthor(String author) {
        log.debug("Searching books by author: {}", author);
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> searchByTitle(String title) {
        log.debug("Searching books by title: {}", title);
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public Book markAsFeatured(Long id) {
        log.info("Marking book {} as featured", id);

        return bookRepository.findById(id)
                .map(book -> {
                    book.setFeatured(true);
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    @Transactional
    public Book markAsBestseller(Long id) {
        log.info("Marking book {} as bestseller", id);

        return bookRepository.findById(id)
                .map(book -> {
                    book.setBestseller(true);
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    public BookDecorator decorateBook(Book book) {
        log.debug("Applying decorators to book: {}", book.getTitle());

        BookDecorator decoratedBook = new BasicBook(book);

        if (book.isFeatured()) {
            decoratedBook = new FeaturedBookDecorator(decoratedBook);
        }

        if (book.isBestseller()) {
            decoratedBook = new BestsellerBookDecorator(decoratedBook);
        }

        return decoratedBook;
    }
}