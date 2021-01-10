package com.chat.entity;

import com.chat.model.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private Long groupId;
    private Long userId;
    private Date date;
    private MessageType type;
    private String content;
}
