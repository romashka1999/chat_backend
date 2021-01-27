package com.chat.dto;

import com.chat.model.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AddUserToGroupDto {
    @NotBlank
    private Long groupId;
    @NotBlank
    private Long userId;
}
