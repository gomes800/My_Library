package com.gom.My_Library.dto;

import lombok.Data;
import java.util.List;

@Data
public class OpenLibraryResponse {
    private int numFound;
    private List<BookDoc> docs;

    @Data
    public static class BookDoc {
        private String title;
        private List<String> author_name;
        private Integer first_publish_year;
        private List<String> isbn;
    }
}