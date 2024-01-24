package com.srsanjay.twenty.repository;

import com.srsanjay.twenty.model.User;
import com.srsanjay.twenty.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    List<User> findByRole(UserRole role);

}
