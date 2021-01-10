package com.chat.dto;

import com.chat.model.MessageType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddUserToGroupDto {
    @NotBlank
    private Long groupId;
    @NotBlank
    private Long userId;
}
