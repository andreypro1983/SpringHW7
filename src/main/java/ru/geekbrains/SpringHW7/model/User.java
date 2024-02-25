package ru.geekbrains.SpringHW7.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    Long age;
    @Column(nullable = false)
    String city;
}
