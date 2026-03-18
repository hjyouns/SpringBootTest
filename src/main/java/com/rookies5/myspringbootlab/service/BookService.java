package com.rookies5.myspringbootlab.service;

import com.rookies5.myspringbootlab.dto.BookDTO;
import com.rookies5.myspringbootlab.entity.BookEntity;
import com.rookies5.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용 (성능 최적화)
public class BookService {

    private final BookRepository bookRepository;

    // 1. 도서 등록
    @Transactional
    public BookDTO.BookResponse createBook(BookDTO.BookCreateRequest request) {
        BookEntity book = BookEntity.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .publishDate(request.getPublishDate())
                .build();

        BookEntity savedBook = bookRepository.save(book);
        return convertToResponse(savedBook);
    }

    // 2. 도서 수정 (실습 2-3 핵심: 입력값이 있는 경우만 변경)
    @Transactional
    public BookDTO.BookResponse updateBook(Long id, BookDTO.BookUpdateRequest request) {
        BookEntity existBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 도서를 찾을 수 없습니다: " + id));

        // 변경이 필요한 필드만 업데이트 (Null 체크)
        if (request.getTitle() != null) {
            existBook.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            existBook.setAuthor(request.getAuthor());
        }
        if (request.getPrice() != null) {
            existBook.setPrice(request.getPrice());
        }
        if (request.getPublishDate() != null) {
            existBook.setPublishDate(request.getPublishDate());
        }

        // 별도의 save() 호출 없이도 @Transactional에 의해 자동 반영(Dirty Checking)됩니다.
        return convertToResponse(existBook);
    }

    // 3. 전체 도서 조회
    public List<BookDTO.BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 4. 엔티티를 응답 DTO로 변환 (공통 로직)
    private BookDTO.BookResponse convertToResponse(BookEntity book) {
        return BookDTO.BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .publishDate(book.getPublishDate())
                .build();
    }
    // BookService 내부 추가 메서드
    public BookDTO.BookResponse getBookById(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
        return convertToResponse(book); // 기존에 만든 변환 메서드 사용
    }

    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("삭제할 도서가 없습니다.");
        }
        bookRepository.deleteById(id);
    }
}