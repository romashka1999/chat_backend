package com.chat.service;

import com.chat.dto.AddUserToGroupDto;
import com.chat.repository.GroupRepository;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<?> getOwnGroups(Authentication authentication) {
        var username = authentication.getName();
        var ownGroups = groupRepository.findAllByAdmin_Username(username);
        return  ResponseEntity.ok(ownGroups);
    }

    public ResponseEntity<?> addUserToGroup(AddUserToGroupDto addUserToGroupDto, Authentication authentication) throws Exception {
        var username = authentication.getName();
        var group = groupRepository.findByAdmin_UsernameAndId(username, addUserToGroupDto.getGroupId());
        if(group.isEmpty()) {
            throw new Exception("YOU_ARE_NOT_ADMIN");
        }


        return ResponseEntity.ok("ola");
    }

}
