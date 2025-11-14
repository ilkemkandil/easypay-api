package com.easypay.api.model;

import com.easypay.api.model.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    @Column(updatable = false)
    private Instant createdAt;
    private Instant updatedAt;
    private boolean isDeleted;
    private Instant deletedAt;
    private Instant lastLogin;

    public User () {}

    public User (String fullName, String email, String password, String phone, Set<Role> roles) {
        this.fullName = fullName;
        this.email = email;

        if (password == null || password.isBlank()) {
            this.password = null;
        } else {
            this.password = new BCryptPasswordEncoder().encode(password);
        }

        this.phone = phone;
        this.roles = roles;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        if (this.roles == null) {
            this.roles = Set.of(Role.USER);
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    protected void markAsDeleted() {
        this.isDeleted = true;
        this.deletedAt = Instant.now();
    }

    protected void markLogin() {
        this.lastLogin = Instant.now();
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public Set<Role> getRoles() { return roles; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public boolean getIsDeleted() { return isDeleted; }
    public Instant getDeletedAt() { return deletedAt; }
    public Instant getLastLogin() { return lastLogin; }

    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) {
        if (password == null || password.isBlank()) {
            this.password = null;
        } else {
            this.password = new BCryptPasswordEncoder().encode(password);
        }
    }

    public void setPhone(String phone) { this.phone = phone; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    public void setLastLogin(Instant lastLogin) { this.lastLogin = lastLogin; }

}





















