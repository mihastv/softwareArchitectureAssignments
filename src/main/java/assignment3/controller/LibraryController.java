package assignment3.controller;

import assignment3.dto.BookDto;
import assignment3.service.LibraryFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Library", description = "Book Management API")
public class LibraryController {

    private final LibraryFacade libraryFacade;

    @Operation(summary = "Retrieve all books", description = "Returns a list of all books with decorated pricing")
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.info("GET /api/books - Retrieving all books");
        return ResponseEntity.ok(libraryFacade.getAllBooks());
    }

    @Operation(summary = "Get a book by ID", description = "Returns a specific book with decorated information")
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(
            @Parameter(description = "ID of the book") @PathVariable Long id) {
        log.info("GET /api/books/{} - Retrieving book", id);
        return ResponseEntity.ok(libraryFacade.getBookById(id));
    }

    @Operation(summary = "Add a new book", description = "Adds a new book to the library")
    @PostMapping
    public ResponseEntity<BookDto> addBook(
            @Parameter(description = "Book data") @RequestBody BookDto bookDto) {
        log.info("POST /api/books - Adding new book: {}", bookDto.getTitle());
        BookDto savedBook = libraryFacade.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @Operation(summary = "Update a book", description = "Updates an existing book by ID")
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @Parameter(description = "ID of the book") @PathVariable Long id,
            @Parameter(description = "Updated book data") @RequestBody BookDto bookDto) {
        log.info("PUT /api/books/{} - Updating book", id);
        return ResponseEntity.ok(libraryFacade.updateBook(id, bookDto));
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book") @PathVariable Long id) {
        log.info("DELETE /api/books/{} - Deleting book", id);
        libraryFacade.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get featured books", description = "Returns all featured books with special pricing")
    @GetMapping("/featured")
    public ResponseEntity<List<BookDto>> getFeaturedBooks() {
        log.info("GET /api/books/featured - Retrieving featured books");
        return ResponseEntity.ok(libraryFacade.getFeaturedBooks());
    }

    @Operation(summary = "Get bestseller books", description = "Returns all bestseller books with special pricing")
    @GetMapping("/bestsellers")
    public ResponseEntity<List<BookDto>> getBestsellerBooks() {
        log.info("GET /api/books/bestsellers - Retrieving bestseller books");
        return ResponseEntity.ok(libraryFacade.getBestsellerBooks());
    }

    @Operation(summary = "Get books by category", description = "Returns books filtered by category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<BookDto>> getBooksByCategory(
            @Parameter(description = "Category name") @PathVariable String category) {
        log.info("GET /api/books/category/{} - Retrieving books by category", category);
        return ResponseEntity.ok(libraryFacade.getBooksByCategory(category));
    }

    @Operation(summary = "Search by author", description = "Search books by author name")
    @GetMapping("/search/author")
    public ResponseEntity<List<BookDto>> searchByAuthor(
            @Parameter(description = "Author name") @RequestParam String name) {
        log.info("GET /api/books/search/author?name={}", name);
        return ResponseEntity.ok(libraryFacade.searchByAuthor(name));
    }

    @Operation(summary = "Search by title", description = "Search books by title")
    @GetMapping("/search/title")
    public ResponseEntity<List<BookDto>> searchByTitle(
            @Parameter(description = "Book title") @RequestParam String title) {
        log.info("GET /api/books/search/title?title={}", title);
        return ResponseEntity.ok(libraryFacade.searchByTitle(title));
    }

    @Operation(summary = "Mark book as featured", description = "Marks a book as featured")
    @PatchMapping("/{id}/featured")
    public ResponseEntity<BookDto> markAsFeatured(
            @Parameter(description = "ID of the book") @PathVariable Long id) {
        log.info("PATCH /api/books/{}/featured - Marking as featured", id);
        return ResponseEntity.ok(libraryFacade.markAsFeatured(id));
    }

    @Operation(summary = "Mark book as bestseller", description = "Marks a book as bestseller")
    @PatchMapping("/{id}/bestseller")
    public ResponseEntity<BookDto> markAsBestseller(
            @Parameter(description = "ID of the book") @PathVariable Long id) {
        log.info("PATCH /api/books/{}/bestseller - Marking as bestseller", id);
        return ResponseEntity.ok(libraryFacade.markAsBestseller(id));
    }

    @Operation(summary = "Get library statistics", description = "Returns overall library statistics")
    @GetMapping("/stats")
    public ResponseEntity<LibraryFacade.LibraryStats> getLibraryStats() {
        log.info("GET /api/books/stats - Retrieving library statistics");
        return ResponseEntity.ok(libraryFacade.getLibraryStats());
    }
}