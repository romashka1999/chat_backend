package com.chat.service;

import com.chat.dto.SendeMessageToGroupDto;
import com.chat.entity.Message;
import com.chat.model.MessageType;
import com.chat.payload.GetMessageByGroupIdResponse;
import com.chat.repository.MessageRepository;
import com.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> sendMessageToGroup(SendeMessageToGroupDto sendeMessageToGroupDto, Authentication authentication) throws Exception {
        var username = authentication.getName();
        var currentUser = userRepository.findByUsername(username);
        if(currentUser.isEmpty()) {
            throw new Exception("CURRENT_USER_NOT_FOUND");
        }

        var message = new Message();
        message.setGroupId(sendeMessageToGroupDto.getGroupId());
        message.setUserId(sendeMessageToGroupDto.getUserId());
        message.setDate(new Date());
        message.setType(sendeMessageToGroupDto.getType());
        if(sendeMessageToGroupDto.getType() == MessageType.TEXT) {
            message.setContent(sendeMessageToGroupDto.getContent());
        } else {
            // avitanot amazonze da imis misamarti chavuwerot contentshi
        }

        messageRepository.save(message);

        return ResponseEntity.ok("ola");
    }

    public ResponseEntity<?> getMessagesByGroupId(Long groupId, String pageStr, String pageSizeStr, Authentication authentication) throws Exception {
        var username = authentication.getName();
        var currentUser = userRepository.findByUsername(username);
        if(currentUser.isEmpty()) {
            throw new Exception("CURRENT_USER_NOT_FOUND");
        }

        var page = Integer.parseInt(pageStr);
        var pageSize = Integer.parseInt(pageSizeStr);

        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

        Page<Message> pageMessage = messageRepository.findByGroupId(groupId, pageable);

        var getMessageByGroupIdResponse = new GetMessageByGroupIdResponse();
        getMessageByGroupIdResponse.setCurrentPage(page);
        getMessageByGroupIdResponse.setTotalPages(pageMessage.getTotalPages());
        getMessageByGroupIdResponse.setTotalItems(pageMessage.getTotalElements());
        getMessageByGroupIdResponse.setMessages(pageMessage.getContent());

        return ResponseEntity.ok(getMessageByGroupIdResponse);
    }
}
