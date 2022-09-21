package com.bridgelabz.bookstorebookservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Response {
    private String message;
    private long errorCode;
    private Object object;


    public Response() {

    }
}