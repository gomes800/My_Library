package com.gom.My_Library.services;

import com.gom.My_Library.clients.OpenLibraryClient;
import com.gom.My_Library.dto.OpenLibraryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private OpenLibraryClient openLibraryClient;

    public OpenLibraryResponse searchBooks(String query) {
        return openLibraryClient.searchBooks(query);
    }
}
