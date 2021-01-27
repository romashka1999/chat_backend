package com.chat.repository;

import com.chat.entity.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserGroupsRepository extends JpaRepository<UserGroups, Long> {
    List<UserGroups> findAllByUser_Username(String username);
}
