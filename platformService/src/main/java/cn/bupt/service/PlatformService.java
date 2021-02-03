package cn.bupt.service;

import cn.bupt.entity.ChatTrans;
import cn.bupt.entity.Platform;

import java.util.List;
import java.util.Map;

public interface PlatformService {
    List<Platform> listAllPlatform();

    String savePlatform(String name, String url, String phone, String mail);

    Integer saveChat(String from, String to, String content, String chatId);

    String read(Integer id);

    Integer query(String from);

    List<List<ChatTrans>> queryAll(String from);

    List<ChatTrans> queryContent(String from, String to, String chatId);

    Map<String,Object> platform(String serviceArea);
}
