package com.it.academy.models;

import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name must have a maximum of {max} characters")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 100, message = "Surname must have a maximum of {max} characters")
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
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
    @NotEmpty(message = "Cannot be empty")
    private Cart cart;

    @OneToMany(mappedBy = "author")
    private List<Course> courses;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    private String stripeAccountId;

}
