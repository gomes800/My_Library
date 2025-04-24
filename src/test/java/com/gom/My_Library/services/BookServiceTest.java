package com.gom.My_Library.services;

import com.gom.My_Library.clients.OpenLibraryClient;
import com.gom.My_Library.models.User;
import com.gom.My_Library.models.UserBook;
import com.gom.My_Library.models.dto.OpenLibraryResponse;
import com.gom.My_Library.repositories.UserBookRepository;
import com.gom.My_Library.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private OpenLibraryClient openLibraryClient;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserBookRepository userBookRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mustReturnSearchBook() {
        OpenLibraryResponse.BookDoc book1 = new OpenLibraryResponse.BookDoc();
        book1.setTitle("Livro teste 1");
        book1.setAuthor_name(List.of("autor teste 1"));
        book1.setFirst_publish_year(2022);
        book1.setIsbn(List.of("123456789"));

        OpenLibraryResponse.BookDoc book2 = new OpenLibraryResponse.BookDoc();
        book2.setTitle("Livro teste 2");
        book2.setAuthor_name(List.of("autor teste 2"));
        book2.setFirst_publish_year(2023);
        book2.setIsbn(List.of("987456321"));

        OpenLibraryResponse response = new OpenLibraryResponse();
        response.setDocs(List.of(book1, book2, new OpenLibraryResponse.BookDoc()));

        when(openLibraryClient.searchBooks("teste")).thenReturn(response);

        List<OpenLibraryResponse.BookDoc> result = bookService.searchBooks("teste", 2);

        assertEquals(2, result.size(), "Must return only 2 books");
        assertEquals("Livro teste 1", result.get(0).getTitle(), "First book title is incorrect.");
        assertEquals("Livro teste 2", result.get(1).getTitle(), "Second book title is incorrect.");

        verify(openLibraryClient, times(1)).searchBooks("teste");
    }

    @Test
    public void mustExcludeBook() {
        Long bookId = 1L;
        User mockUser = new User();
        UserBook bookToDelete = new UserBook();
        bookToDelete.setId(bookId);

        List<UserBook> bookList = new ArrayList<>();
        bookList.add(bookToDelete);
        mockUser.setBookList(bookList);

        when(userService.getAuthenticatedUser()).thenReturn(mockUser);

        bookService.deleteBook(bookId);

        assertTrue(mockUser.getBookList().isEmpty(), "The list should be empty.");
        verify(userBookRepository, times(1)).delete(bookToDelete);
    }

    @Test
    public void mustThrowExceptionWhenBookNotFound() {
        Long bookId = 999L;
        User mockUser = new User();
        mockUser.setBookList(new ArrayList<>());

        when(userService.getAuthenticatedUser()).thenReturn(mockUser);

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> bookService.deleteBook(bookId),
                "Should throw EntityNotFoundException."
        );

        assertEquals("Book not found.", exception.getMessage());
    }
}
