package com.rookies5.myspringbootlab.repository;

import com.rookies5.myspringbootlab.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn(String isbn);
    List<BookEntity> findByAuthor(String author);
}