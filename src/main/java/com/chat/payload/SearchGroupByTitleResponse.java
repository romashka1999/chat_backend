package com.chat.payload;

import com.chat.entity.Group;
import lombok.Data;

@Data
public class SearchGroupByTitleResponse {
    private Group group;
    private Boolean isJoined;
}
