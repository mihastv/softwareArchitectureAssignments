package assignment3.repository;

import assignment3.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategory(String category);

    List<Book> findByFeaturedTrue();

    List<Book> findByBestsellerTrue();

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPriceLessThanEqual(double maxPrice);

    List<Book> findByPriceBetween(double minPrice, double maxPrice);
}