package com.rookies5.myspringbootlab.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String author;

    @Column(unique = true)
    private String isbn;
    private Integer price;
    private LocalDate publishDate;

    // mappedBy: 관계의 주인이 아님을 표시 (BookDetailEntity의 'book' 필드에 의해 매핑됨)
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private BookDetailEntity bookDetail;

    // 연관관계 편의 메서드 (양방향 연결)
    public void setBookDetail(BookDetailEntity detail) {
        this.bookDetail = detail;
        if (detail != null) {
            detail.setBook(this);
        }
    }
}