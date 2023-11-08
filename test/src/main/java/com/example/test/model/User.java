package com.example.test.model;

import com.example.test.enums.TypeOfCar;
import com.example.test.enums.TypeOfUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private String firstname;
    private String lastname;

    private String address;

    @Column(unique = true)
    private String phone_number;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TypeOfUser status;
}
