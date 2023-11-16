package com.example.test.repository;

import com.example.test.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findRefreshTokenByToken(String refreshToken);

    @Query("select r FROM RefreshToken r where r.user.username = :username")
    List<RefreshToken> findRefreshTokensByUser(String username);

}
