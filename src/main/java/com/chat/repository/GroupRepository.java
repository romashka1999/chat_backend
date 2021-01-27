package com.chat.repository;

import com.chat.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByIdIn(ArrayList<Long> groupIds);
    List<Group> findAllByAdmin_Username(String username);
    Optional<Group> findByAdmin_UsernameAndId(String username, Long id);
    List<Group> findByTitleLikeAndIsPublicIs(String title, Boolean isPublic);
}
