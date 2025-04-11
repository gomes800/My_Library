package com.gom.My_Library.models.dto;

import lombok.Data;

@Data
public class SaveBookDTO {
    private String title;
    private String author;
    private Integer publishedDate;
    private String isbn;
}
