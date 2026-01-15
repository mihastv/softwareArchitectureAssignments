package assignment3.config;

import assignment3.entity.Book;
import assignment3.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.count() > 0) {
            log.info("Database already has {} books, skipping initialization", bookRepository.count());
            return;
        }

        log.info("Initializing sample book data...");

        Book book1 = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .price(45.99)
                .category("Programming")
                .featured(false)
                .bestseller(false)
                .build();

        Book book2 = Book.builder()
                .title("Design Patterns")
                .author("Gang of Four")
                .price(59.99)
                .category("Programming")
                .featured(true)
                .bestseller(false)
                .build();

        Book book3 = Book.builder()
                .title("The Pragmatic Programmer")
                .author("David Thomas")
                .price(49.99)
                .category("Programming")
                .featured(true)
                .bestseller(true)
                .build();

        Book book4 = Book.builder()
                .title("Harry Potter")
                .author("J.K. Rowling")
                .price(29.99)
                .category("Fiction")
                .featured(false)
                .bestseller(true)
                .build();

        Book book5 = Book.builder()
                .title("1984")
                .author("George Orwell")
                .price(19.99)
                .category("Fiction")
                .featured(false)
                .bestseller(false)
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);

        log.info("Sample data initialized: {} books created", bookRepository.count());
    }
}