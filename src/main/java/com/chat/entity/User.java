package com.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Column
    @NotBlank
    @Size(min = 3, max = 255)
    @Email
    private String email;

    @Column
    @Size(min = 8)
    private String password;

    public User(@NotBlank @Size(min = 3, max = 255) String username, @Size(min = 8) String password) {
        this.username = username;
        this.password = password;
    }

    public User(@NotBlank @Size(min = 3, max = 20) String username, @NotBlank @Size(min = 3, max = 255) @Email String email, @Size(min = 8) String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
