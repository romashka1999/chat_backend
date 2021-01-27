package com.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreateGroupDto {
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message ="isPublic is required")
    private Boolean isPublic;
}
