package com.bridgelabz.bookstorebookservice.dto;

import lombok.Data;

@Data
public class BookDto {
    private String bookName;
    private String author;
    private String description;
    private String logo;
    private Long price;
    private Long quantity;


}
