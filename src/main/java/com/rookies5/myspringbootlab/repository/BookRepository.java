package com.rookies5.myspringbootlab.repository;

import com.rookies5.myspringbootlab.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    // ISBN 중복 체크용
    boolean existsByIsbn(String isbn);

    // Fetch Join을 사용하여 Detail까지 한 번에 조회 (N+1 문제 방지)
    @Query("SELECT b FROM BookEntity b LEFT JOIN FETCH b.bookDetail WHERE b.id = :id")
    Optional<BookEntity> findByIdWithBookDetail(@Param("id") Long id);

    @Query("SELECT b FROM BookEntity b LEFT JOIN FETCH b.bookDetail WHERE b.isbn = :isbn")
    Optional<BookEntity> findByIsbnWithBookDetail(@Param("isbn") String isbn);

    Optional<BookEntity> findByIsbn(String isbn);
}