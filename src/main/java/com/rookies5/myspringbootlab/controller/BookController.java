package com.rookies5.myspringbootlab.controller;

import com.rookies5.myspringbootlab.dto.BookDTO;
import com.rookies5.myspringbootlab.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO.Response> create(@Valid @RequestBody BookDTO.Request request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO.Response>> getAll() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO.Response> getByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO.Response> update(@PathVariable Long id, @RequestBody BookDTO.Request request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }
}