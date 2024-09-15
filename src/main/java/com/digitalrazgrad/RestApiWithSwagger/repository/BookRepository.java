package com.digitalrazgrad.RestApiWithSwagger.repository;

import com.digitalrazgrad.RestApiWithSwagger.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
