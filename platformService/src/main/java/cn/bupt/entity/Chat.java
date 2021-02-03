package cn.bupt.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Chat {
    Integer id;
    String fromId;
    String toId;
    String content;
    Date chatTime;
    String chatId;
    Integer status;
}
