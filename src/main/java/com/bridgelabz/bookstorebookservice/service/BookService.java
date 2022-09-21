package com.bridgelabz.bookstorebookservice.service;

import com.bridgelabz.bookstorebookservice.dto.BookDto;
import com.bridgelabz.bookstorebookservice.exception.BookNotFoundException;
import com.bridgelabz.bookstorebookservice.model.BookModel;
import com.bridgelabz.bookstorebookservice.repository.BookRepository;
import com.bridgelabz.bookstorebookservice.util.Response;
import com.bridgelabz.bookstorebookservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class BookService implements IBookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Response addBook(BookDto bookDto, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            BookModel bookModel = new BookModel(bookDto);
            bookRepository.save(bookModel);
            return new Response("success", 200, bookModel);
        }
        throw new BookNotFoundException(400, "Not found");
    }

    @Override
    public Response updateBook(Long userId, String token, BookDto bookDto) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<BookModel> isBookPresent = bookRepository.findById(userId);
            if (isBookPresent.isPresent()) {
                isBookPresent.get().setBookName(bookDto.getBookName());
                isBookPresent.get().setAuthor(bookDto.getAuthor());
                isBookPresent.get().setDescription(bookDto.getDescription());
                isBookPresent.get().setLogo(bookDto.getLogo());
                isBookPresent.get().setPrice(bookDto.getPrice());
                isBookPresent.get().setQuantity(bookDto.getQuantity());
                bookRepository.save(isBookPresent.get());
                return new Response("success", 200, isBookPresent.get());
            }
        }
        throw new BookNotFoundException(400, "Not found");
    }

    @Override
    public List<BookModel> getBookData(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            List<BookModel> isBookPresent = bookRepository.findAll();
            return isBookPresent;
        }
        throw new BookNotFoundException(400, "Not found");
    }
    @Override
    public Response deleteBook(Long userId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/user/validate/" + token, Boolean.class);
        if(isUserPresent){
            Optional<BookModel> isBookPresent = bookRepository.findById(userId);
            if (isBookPresent.isPresent()){
                return new Response("success", 200, isBookPresent.get());
            }
            throw new BookNotFoundException(400, "Not found");
        }
        throw new BookNotFoundException(400, "Token is wrong");
    }
}
