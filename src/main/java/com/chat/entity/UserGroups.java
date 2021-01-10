package com.chat.entity;

import com.chat.entity.Group;
import com.chat.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_groups")
public class UserGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne()
    @JoinColumn(name = "group_id")
    Group group;

    @Column
    Date date;
}
