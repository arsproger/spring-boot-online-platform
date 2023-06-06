package com.it.academy.entities;

import com.it.academy.enums.Provider;
import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty
    private String fullName;

    private LocalDate dateOfBirth;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String imageUrl;

    private String stripeAccountId;

    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "reset_token")
    private String resetToken;

    private LocalDateTime resetTokenExpireTime;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;

    @OneToOne
    private Cart cart;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
