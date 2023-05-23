package com.it.academy.models;

import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotNull
    @Size(max = 155)
    private String name;

    @NotNull
    @Size(max = 155)
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Email
    private String email;

    @NotNull
    @Size(min = 8)
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

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @OneToOne
    private Cart cart;

    @OneToMany(mappedBy = "author")
    private List<Course> courses;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    private String stripeAccountId;

}
