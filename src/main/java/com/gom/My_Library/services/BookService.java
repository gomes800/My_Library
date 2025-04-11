package com.gom.My_Library.services;

import com.gom.My_Library.clients.OpenLibraryClient;
import com.gom.My_Library.models.User;
import com.gom.My_Library.models.UserBook;
import com.gom.My_Library.models.dto.OpenLibraryResponse;
import com.gom.My_Library.models.dto.SaveBookDTO;
import com.gom.My_Library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private OpenLibraryClient openLibraryClient;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public List<OpenLibraryResponse.BookDoc> searchBooks(String query, int limit) {
        OpenLibraryResponse response = openLibraryClient.searchBooks(query);
        return response.getDocs().stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void saveBookForUser(SaveBookDTO dto) {
        User user = userService.getAuthenticatedUser();

        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.setBookName(dto.getTitle());
        userBook.setAuthor(dto.getAuthor() != null ? dto.getAuthor() : "Desconhecido");
        userBook.setPublishedDate(dto.getPublishedDate());
        userBook.setIsbn(dto.getIsbn() != null ? dto.getIsbn() : "N/A");

        user.getBookList().add(userBook);
        userRepository.save(user);
    }
}
