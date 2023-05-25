package com.it.academy.models;

import com.it.academy.enums.Provider;
import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "activation_token")
    private String activationToken;
    @Column(name = "reset_token")
    private String resetToken;
    @Column(name = "reset_token_expire_time")
    private LocalDateTime resetTokenExpireTime;
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @OneToOne
    private Cart cart;

    @OneToMany(mappedBy = "author")
    private List<Course> courses;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToOne(mappedBy = "user")
    private S3 s3;

}
