package com.gom.My_Library.clients;

import com.gom.My_Library.dto.OpenLibraryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openLibraryClient", url = "https://openlibrary.org")
public interface OpenLibraryClient {

    @GetMapping("/search.json")
    OpenLibraryResponse searchBooks(@RequestParam("q") String query);
}
