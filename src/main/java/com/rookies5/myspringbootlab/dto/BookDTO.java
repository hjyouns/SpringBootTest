package com.rookies5.myspringbootlab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

public class BookDTO {

    // 1. 도서 생성 요청 DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookCreateRequest {
        @NotBlank(message = "제목은 필수입니다.")
        private String title;

        @NotBlank(message = "저자는 필수입니다.")
        private String author;

        @NotBlank(message = "ISBN은 필수입니다.")
        private String isbn;

        @Positive(message = "가격은 0보다 커야 합니다.")
        private Integer price;

        private LocalDate publishDate;
    }

    // 2. 도서 수정 요청 DTO (선택적 수정 가능)
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookUpdateRequest {
        private String title;
        private String author;
        private Integer price;
        private LocalDate publishDate;
    }

    // 3. 도서 응답 DTO (클라이언트에게 보내는 데이터)
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BookResponse {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private Integer price;
        private LocalDate publishDate;
    }
}