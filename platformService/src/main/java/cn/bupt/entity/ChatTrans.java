package cn.bupt.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChatTrans implements Serializable {
    Integer id;
    String from;
    String to;
    String content;
    Date time;
    String chatId;
    Integer read;
}
