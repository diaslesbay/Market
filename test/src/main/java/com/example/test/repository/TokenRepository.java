package com.example.test.repository;

import com.example.test.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("select r FROM Token r where r.user.username = :username")
    Optional<Token> findTokensByUser(String username);
}
