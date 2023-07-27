package com.huangstomach.springalchemy.user.repository;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import com.huangstomach.springalchemy.user.entity.User;

@EnableJpaRepositories(basePackages = "com.huangstomach.springalchemy.user.repository")
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);
    @Async
    Future<Page<User>> findAll(Pageable pageable);
    void deleteById(Integer id);
}
