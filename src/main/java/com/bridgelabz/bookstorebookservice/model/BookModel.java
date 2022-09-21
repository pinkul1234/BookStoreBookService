package com.bridgelabz.bookstorebookservice.model;

import com.bridgelabz.bookstorebookservice.dto.BookDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Book")
@Data
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bookName;
    private String author;
    private String description;
    private String logo;
    private Long price;
    private Long quantity;

    public BookModel(BookDto bookDto){
        this.bookName = bookDto.getBookName();
        this.author = bookDto.getAuthor();
        this.description = bookDto.getDescription();
        this.logo = bookDto.getLogo();
        this.price = bookDto.getPrice();
        this.quantity = bookDto.getQuantity();
    }


    public BookModel() {

    }
}
