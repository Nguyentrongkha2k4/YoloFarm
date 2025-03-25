package com.web.yolofarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.yolofarm.entity.User;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
