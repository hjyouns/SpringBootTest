package com.rookies5.myspringbootlab.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String language;
    private Integer pageCount;
    private String publisher;
    private String coverImageUrl;
    private String edition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", unique = true)
    private BookEntity book;
}