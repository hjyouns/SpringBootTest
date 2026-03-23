package com.rookies5.myspringbootlab.dto;

import com.rookies5.myspringbootlab.entity.BookEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

public class BookDTO {

    // 1. 등록/수정 요청 (Request)
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Request {
        @NotBlank(message = "제목은 필수입니다.")
        private String title;

        @NotBlank(message = "저자는 필수입니다.")
        private String author;

        @NotBlank(message = "ISBN은 필수입니다.")
        @Pattern(regexp = "^[0-9-]*$", message = "ISBN 형식이 올바르지 않습니다.")
        private String isbn;

        @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
        private Integer price;

        @Past(message = "출판일은 과거 날짜여야 합니다.")
        private LocalDate publishDate;

        // 상세 정보 요청 DTO 포함
        @Valid
        private BookDetailDTO detailRequest;
    }

    // 2. 상세 정보 요청/응답용 DTO
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookDetailDTO {
        private String description;
        private String language;
        private Integer pageCount;
        private String publisher;
        private String coverImageUrl;
        private String edition;
    }

    // 3. 전체 응답 DTO (Response)
    @Data @NoArgsConstructor @AllArgsConstructor @Builder
    public static class Response {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;

        // 상세 정보 응답 포함
        private BookDetailDTO detail;

        // Entity -> DTO 변환 메서드 (Service에서 사용)
        public static Response fromEntity(BookEntity book) {
            BookDetailDTO detailDTO = null;
            if (book.getBookDetail() != null) {
                detailDTO = BookDetailDTO.builder()
                        .description(book.getBookDetail().getDescription())
                        .language(book.getBookDetail().getLanguage())
                        .pageCount(book.getBookDetail().getPageCount())
                        .publisher(book.getBookDetail().getPublisher())
                        .coverImageUrl(book.getBookDetail().getCoverImageUrl())
                        .edition(book.getBookDetail().getEdition())
                        .build();
            }

            return Response.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .price(book.getPrice())
                    .publishDate(book.getPublishDate())
                    .detail(detailDTO)
                    .build();
        }
    }
}