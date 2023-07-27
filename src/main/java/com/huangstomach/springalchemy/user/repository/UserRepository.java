package com.huangstomach.springalchemy.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.huangstomach.springalchemy.user.entity.User;

@EnableJpaRepositories(basePackages = "com.huangstomach.springalchemy.user.repository")
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);
    Page<User> findAll(Pageable pageable);
    void deleteById(Integer id);
}
