package com.sda.project.wypozyczalnia.dao;

import com.sda.project.wypozyczalnia.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    User findByLogin (String email);
}
