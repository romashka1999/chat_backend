package com.chat.controller;

import com.chat.dto.AddUserToGroupDto;
import com.chat.dto.CreateGroupDto;
import com.chat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/createGroup")
    public ResponseEntity<?> createGroup(
            CreateGroupDto createGroupDto,
            Authentication authentication
    ) throws Exception{
        return groupService.createGroup(createGroupDto, authentication);
    }


    @GetMapping("/getOwnGroups")
    public ResponseEntity<?> getOwnGroups(Authentication authentication) {
        return groupService.getOwnGroups(authentication);
    }

    @GetMapping("/getAllJoinedGroups")
    public ResponseEntity<?> getAllJoinedGroups(Authentication authentication) {
        return groupService.getAllJoinedGroups(authentication);
    }

    @PostMapping("/addUserToGroup")
    public ResponseEntity<?> addUserToGroup(
            AddUserToGroupDto addUserToGroupDto,
            Authentication authentication
    ) throws Exception {
        return groupService.addUserToGroup(addUserToGroupDto, authentication);
    }

    @GetMapping("/searchGroupByTitle/{title}")
    public ResponseEntity<?> searchGroupByTitle(
            @PathVariable(value = "title") String title,
            Authentication authentication
    ) throws Exception {
        return groupService.searchGroupByTitle(title, authentication);
    }
}
