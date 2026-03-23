package com.rookies5.myspringbootlab.service;

import com.rookies5.myspringbootlab.dto.BookDTO;
import com.rookies5.myspringbootlab.entity.BookDetailEntity;
import com.rookies5.myspringbootlab.entity.BookEntity;
import com.rookies5.myspringbootlab.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    // 1. 등록 (Cascade 활용)
    @Transactional
    public BookDTO.Response createBook(BookDTO.Request request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("이미 존재하는 ISBN입니다.");
        }

        BookEntity book = BookEntity.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .publishDate(request.getPublishDate())
                .build();

        if (request.getDetailRequest() != null) {
            BookDetailEntity detail = BookDetailEntity.builder()
                    .description(request.getDetailRequest().getDescription())
                    .language(request.getDetailRequest().getLanguage())
                    .pageCount(request.getDetailRequest().getPageCount())
                    .publisher(request.getDetailRequest().getPublisher())
                    .coverImageUrl(request.getDetailRequest().getCoverImageUrl())
                    .edition(request.getDetailRequest().getEdition())
                    .build();
            book.setBookDetail(detail);
        }

        return BookDTO.Response.fromEntity(bookRepository.save(book));
    }

    // 2. 전체 조회
    public List<BookDTO.Response> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO.Response::fromEntity)
                .collect(Collectors.toList());
    }

    // 3. ISBN으로 조회 (Fetch Join 활용 추천)
    public BookDTO.Response getBookByIsbn(String isbn) {
        BookEntity book = bookRepository.findByIsbnWithBookDetail(isbn)
                .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
        return BookDTO.Response.fromEntity(book);
    }

    // 4. 부분 수정 (PATCH 기능 포함)
    @Transactional
    public BookDTO.Response updateBook(Long id, BookDTO.Request request) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("도서가 없습니다."));

        // ISBN 변경 시 중복 체크
        if (request.getIsbn() != null && !book.getIsbn().equals(request.getIsbn())) {
            if (bookRepository.existsByIsbn(request.getIsbn())) {
                throw new RuntimeException("이미 사용 중인 ISBN입니다.");
            }
            book.setIsbn(request.getIsbn());
        }

        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
        if (request.getPrice() != null) book.setPrice(request.getPrice());

        // 상세 정보 업데이트
        if (request.getDetailRequest() != null && book.getBookDetail() != null) {
            BookDetailEntity detail = book.getBookDetail();
            if (request.getDetailRequest().getDescription() != null) detail.setDescription(request.getDetailRequest().getDescription());
            if (request.getDetailRequest().getPublisher() != null) detail.setPublisher(request.getDetailRequest().getPublisher());
        }

        return BookDTO.Response.fromEntity(book);
    }
}