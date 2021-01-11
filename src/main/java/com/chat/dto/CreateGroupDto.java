package com.chat.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateGroupDto {
    @NotBlank
    private String title;
    @NotBlank
    private Boolean isPublic;
}
