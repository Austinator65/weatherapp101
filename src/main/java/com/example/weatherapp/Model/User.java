package com.example.weatherapp.Model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}
