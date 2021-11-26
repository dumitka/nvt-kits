package com.backend.springboot.repository;

import com.backend.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User save(User user);

	List<User> findByFired(boolean fired); //pageable?
}
