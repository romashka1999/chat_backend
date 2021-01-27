package com.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(
        name = "groups",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        }
)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isPublic;

    @ManyToOne()
    @JoinColumn(name="admin_id", nullable=false)
    private User admin;

    @OneToMany(mappedBy = "group")
    Set<UserGroups> userGroups;
}
