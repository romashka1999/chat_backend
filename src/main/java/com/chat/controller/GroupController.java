package com.chat.controller;

import com.chat.dto.AddUserToGroupDto;
import com.chat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/getOwnGroups")
    public ResponseEntity<?> getOwnGroups(Authentication authentication) {
        return groupService.getOwnGroups(authentication);
    }

    @PostMapping("addUserToGroup")
    public ResponseEntity<?> addUserToGroup(
            AddUserToGroupDto addUserToGroupDto,
            Authentication authentication
    ) throws Exception {
        return groupService.addUserToGroup(addUserToGroupDto, authentication);
    }
}
