package assignment3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private double price;
    private String category;
    private boolean featured;
    private boolean bestseller;
    private String description;
    private String displayPrice;
}