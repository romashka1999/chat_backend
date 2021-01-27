package com.chat.dto;

import com.chat.model.MessageType;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class SendeMessageToGroupDto {
    @NotBlank
    private Long groupId;
    @NotBlank
    private Long userId;
    @NotBlank
    private MessageType type;
    @NotBlank
    private String content;
}
