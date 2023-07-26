package com.huangstomach.springalchemy.orm;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByName(String name);
}
