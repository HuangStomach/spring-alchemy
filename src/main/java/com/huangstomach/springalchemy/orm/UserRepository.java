package com.huangstomach.springalchemy.orm;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.huangstomach.springalchemy.orm.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);
    User findById(int id);
}
