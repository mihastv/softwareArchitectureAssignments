package assignment3.utils;

import assignment3.dto.BookDto;
import assignment3.entity.Book;

public class BookMapper {

    public static BookDto toDTO(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .category(book.getCategory())
                .featured(book.isFeatured())
                .bestseller(book.isBestseller())
                .description(book.getDescription())
                .displayPrice(String.format("$%.2f", book.getPrice()))
                .build();
    }

    public static Book toEntity(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .featured(dto.isFeatured())
                .bestseller(dto.isBestseller())
                .build();
    }
}