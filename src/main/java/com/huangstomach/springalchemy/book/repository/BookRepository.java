package com.huangstomach.springalchemy.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.huangstomach.springalchemy.book.entity.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
    List<Book> findByName(String name);
    Page<Book> findAll(Pageable pageable);
}
