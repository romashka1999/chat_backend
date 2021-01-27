package com.chat.payload;

import com.chat.entity.Message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetMessageByGroupIdResponse {
    private Integer currentPage;
    private Integer totalPages;
    private Long totalItems;
    private List<Message> messages;
}
