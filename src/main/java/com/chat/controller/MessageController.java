package com.chat.controller;

import com.chat.dto.SendeMessageToGroupDto;
import com.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("")
    public ResponseEntity<?> sendMessageToGroup(
            @Valid @RequestBody SendeMessageToGroupDto sendeMessageToGroupDto,
            Authentication authentication
    ) throws Exception {
        return messageService.sendMessageToGroup(sendeMessageToGroupDto, authentication);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getMessagesByGroupId(
            @PathVariable(value = "groupId") Long groupId,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
            Authentication authentication
    ) throws Exception {
        return messageService.getMessagesByGroupId(groupId, page, pageSize, authentication);
    }
}
