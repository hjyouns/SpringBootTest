package com.rookies5.myspringbootlab.repository;

import com.rookies5.myspringbootlab.entity.BookEntity; // 정확한 임포트 확인
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // 테스트 후 DB 데이터를 롤백하여 깨끗하게 유지합니다.
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testCreateBook() {
        // BookEntity의 빌더를 사용하여 테스트 데이터 생성
        BookEntity book = BookEntity.builder()
                .title("스프링 부트 입문")
                .author("홍길동")
                .isbn("9788956746425")
                .price(30000)
                .publishDate(LocalDate.of(2025, 5, 7))
                .build();

        BookEntity savedBook = bookRepository.save(book);

        // 검증
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("스프링 부트 입문");
    }

    @Test
    public void testFindByIsbn() {
        // 데이터 저장 후 ISBN으로 조회 테스트
        BookEntity book = BookEntity.builder()
                .title("JPA 프로그래밍").author("박둘리").isbn("9788956746432")
                .price(35000).publishDate(LocalDate.of(2025, 4, 30)).build();
        bookRepository.save(book);

        BookEntity foundBook = bookRepository.findByIsbn("9788956746432").orElse(null);
        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getAuthor()).isEqualTo("박둘리");
    }
}