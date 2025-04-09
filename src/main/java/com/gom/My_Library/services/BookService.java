package com.gom.My_Library.services;

import com.gom.My_Library.clients.OpenLibraryClient;
import com.gom.My_Library.dto.OpenLibraryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private OpenLibraryClient openLibraryClient;

    public List<OpenLibraryResponse.BookDoc> searchBooks(String query, int limit) {
        OpenLibraryResponse response = openLibraryClient.searchBooks(query);
        return response.getDocs().stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
