package com.bridgelabz.bookstorebookservice.controller;

import com.bridgelabz.bookstorebookservice.dto.BookDto;
import com.bridgelabz.bookstorebookservice.model.BookModel;
import com.bridgelabz.bookstorebookservice.service.IBookService;
import com.bridgelabz.bookstorebookservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;
    @PostMapping("/addbook")
    public ResponseEntity<Response> addBook(@RequestBody BookDto bookDto, @RequestHeader String token){
        Response response = bookService.addBook(bookDto, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateBook(@RequestHeader String token, @RequestBody BookDto bookDto, @PathVariable Long userId){
        Response response = bookService.updateBook(userId, token, bookDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getbookdata")
    public ResponseEntity<List<?>> getBookData(@RequestParam String token){
        List<BookModel> response = bookService.getBookData(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/deletebook")
    public ResponseEntity<Response> deleteBook(@PathVariable Long userId, @RequestHeader String token){
        Response response = bookService.deleteBook(userId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
