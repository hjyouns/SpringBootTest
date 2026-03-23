package com.rookies5.myspringbootlab.repository;

import com.rookies5.myspringbootlab.entity.BookDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailRepository extends JpaRepository<BookDetailEntity, Long> {
    // 특정 출판사로 조회
    java.util.List<BookDetailEntity> findByPublisher(String publisher);
}