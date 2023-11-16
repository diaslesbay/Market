package com.example.test.model;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accessToken;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "userId")
    private  User user;
}
