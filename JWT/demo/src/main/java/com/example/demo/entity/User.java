package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_db")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    private String password;


}
