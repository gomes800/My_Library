package com.gom.My_Library.controllers;

import com.gom.My_Library.models.dto.OpenLibraryResponse;
import com.gom.My_Library.models.dto.SaveBookDTO;
import com.gom.My_Library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<OpenLibraryResponse.BookDoc> searchBooks(
            @RequestParam String query,
            @RequestParam(defaultValue = "8") int limit) {
        return bookService.searchBooks(query, limit);
    }

    @PostMapping("/books/save")
    public ResponseEntity<Void> saveBook(@RequestBody SaveBookDTO dto) {
        bookService.saveBookForUser(dto);
        return ResponseEntity.ok().build();
    }
}
