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

    private final BookService bookService; // 모든 비즈니스 로직은 Service가 처리함

    // [등록] DTO 사용
    @PostMapping
    public ResponseEntity<BookDTO.BookResponse> create(@Valid @RequestBody BookDTO.BookCreateRequest request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    // [전체 조회] DTO 리스트 반환
    @GetMapping
    public ResponseEntity<List<BookDTO.BookResponse>> getAll() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // [상세 조회] ID 기반
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO.BookResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id)); // Service에 추가 필요
    }

    // [수정] 실습 2-3의 핵심 (부분 업데이트)
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO.BookResponse> update(@PathVariable Long id, @RequestBody BookDTO.BookUpdateRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    // [삭제]
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id); // Service에 추가 필요
        return ResponseEntity.noContent().build();
    }
}