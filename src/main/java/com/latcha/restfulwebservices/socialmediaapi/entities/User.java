package com.latcha.restfulwebservices.socialmediaapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @Size(min=2, message = "Name should hava at least 2 characters")
    private String name;

    @Column
    @Past(message = "Birth Data should be in the past")
    private LocalDate birthDate;
}
