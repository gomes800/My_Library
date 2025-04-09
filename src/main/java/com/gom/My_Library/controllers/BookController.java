package com.gom.My_Library.controllers;

import com.gom.My_Library.dto.OpenLibraryResponse;
import com.gom.My_Library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public OpenLibraryResponse searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
}
