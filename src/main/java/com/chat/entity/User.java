package com.chat.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
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

    @OneToMany(mappedBy="admin")
    private Set<Group> ownGroups;

    @OneToMany(mappedBy = "user")
    Set<UserGroups> userGroups;

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
