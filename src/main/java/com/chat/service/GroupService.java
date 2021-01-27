package com.chat.service;

import com.chat.dto.AddUserToGroupDto;
import com.chat.dto.CreateGroupDto;
import com.chat.entity.Group;
import com.chat.entity.UserGroups;
import com.chat.payload.SearchGroupByTitleResponse;
import com.chat.repository.GroupRepository;
import com.chat.repository.UserGroupsRepository;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserGroupsRepository userGroupsRepository;

    @Autowired
    public GroupService(
            GroupRepository groupRepository,
            UserRepository userRepository,
            UserGroupsRepository userGroupsRepository
    ) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userGroupsRepository = userGroupsRepository;
    }

    public ResponseEntity<?> createGroup(CreateGroupDto createGroupDto, Authentication authentication) throws Exception {
        var authenticatedUser = userRepository.findByUsername(authentication.getName());

        System.out.println(authenticatedUser);

        var newGroup = new Group();
        newGroup.setAdmin(authenticatedUser.get());
        newGroup.setTitle(createGroupDto.getTitle());
        newGroup.setIsPublic(Boolean.TRUE);

        System.out.println(newGroup);
        var createdGroup = groupRepository.save(newGroup);

        System.out.println(createdGroup);

        var userGroups = new UserGroups();
        userGroups.setUser(authenticatedUser.get());
        userGroups.setGroup(createdGroup);
        userGroups.setDate(new Date());
        var createdUserGroup = userGroupsRepository.save(userGroups);

        System.out.println(createdUserGroup);
        System.out.println("------------------------------------------------------aaaaaaaaaaaaaaaaa------");

        return ResponseEntity.ok("Group sucessufully created :)");
    }


    public ResponseEntity<?> getOwnGroups(Authentication authentication) {
        var username = authentication.getName();
        var ownGroups = groupRepository.findAllByAdmin_Username(username);
        return  ResponseEntity.ok(ownGroups);
    }

    public ResponseEntity<?> getAllJoinedGroups(Authentication authentication) {
        var username = authentication.getName();
        System.out.println(username);

        var userGroups = this.userGroupsRepository.findAllByUser_Username(username);
        System.out.println(userGroups);

        var groupIds = new ArrayList<Long>();

        if (!userGroups.isEmpty()) {
            userGroups.forEach(userGroup -> {
                groupIds.add(userGroup.getGroup().getId());
            });
        }

        System.out.println(groupIds);

        var groups = this.groupRepository.findAllByIdIn(groupIds);
        System.out.println(groups);

        System.out.println("------------------------------------------------------------");
        return  ResponseEntity.ok(groups);
    }

    public ResponseEntity<?> addUserToGroup(AddUserToGroupDto addUserToGroupDto, Authentication authentication) throws Exception {
        var username = authentication.getName();
        var group = groupRepository.findByAdmin_UsernameAndId(username, addUserToGroupDto.getGroupId());
        if(group.isEmpty()) {
            throw new Exception("YOU_ARE_NOT_ADMIN");
        }
        var user = userRepository.findById(addUserToGroupDto.getUserId());
        if(user.isEmpty()) {
            throw new Exception("USER_NOT_FOUND");
        }

        if (user.get().getUsername().equals(username)) {
            throw new Exception("YOU_CANT_ADD_YOURSELF_IN_YOUR_GROUP");
        }

        var userGroups = new UserGroups();
        userGroups.setUser(user.get());
        userGroups.setGroup(group.get());
        userGroups.setDate(new Date());
        userGroupsRepository.save(userGroups);

        System.out.println(userGroups);

        return ResponseEntity.ok("User successfully added to group");
    }

    public ResponseEntity<?> searchGroupByTitle(String title, Authentication authentication) throws Exception {
        var authenticatedUserGroups = groupRepository.findAllByAdmin_Username(authentication.getName());

        var autenticatedUserJoinedGroupIds = new ArrayList<Long>();

        if (!authenticatedUserGroups.isEmpty()) {
            authenticatedUserGroups.forEach(group -> {
                autenticatedUserJoinedGroupIds.add(group.getId());
            });
        }

        var searchedGroups = groupRepository.findByTitleLikeAndIsPublicIs("%" + title + "%", true);
        if(searchedGroups.isEmpty()) {
            return ResponseEntity.ok(null);
        } else {
            var searchGroupByTitleResponseList = new ArrayList<SearchGroupByTitleResponse>();
            searchedGroups.forEach(group -> {
                var searchGroupByTitleResponse = new SearchGroupByTitleResponse();
                searchGroupByTitleResponse.setGroup(group);
                if(autenticatedUserJoinedGroupIds.contains(group.getId())) {
                    searchGroupByTitleResponse.setIsJoined(true);
                } else {
                    searchGroupByTitleResponse.setIsJoined(false);
                }
                searchGroupByTitleResponseList.add(searchGroupByTitleResponse);
            });
            return ResponseEntity.ok(searchGroupByTitleResponseList);
        }
    }

}
