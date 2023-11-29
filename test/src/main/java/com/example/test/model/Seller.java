package com.example.test.model;

import com.example.test.enums.TypeOfUser;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sellers")
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    private String sellerName;
    private String phoneNumber;
    private BigDecimal balance;

    private String username;
    private String email;
    private String password;
    private String address;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TypeOfUser status;
}
