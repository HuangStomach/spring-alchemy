package com.huangstomach.springalchemy.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.huangstomach.springalchemy.user.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);
    Page<User> findAll(Pageable pageable);
    void deleteById(Integer id);
}
