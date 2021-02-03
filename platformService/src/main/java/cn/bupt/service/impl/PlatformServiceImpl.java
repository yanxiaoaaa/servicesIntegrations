package cn.bupt.service.impl;

import cn.bupt.entity.Chat;
import cn.bupt.entity.ChatTrans;
import cn.bupt.entity.Platform;
import cn.bupt.mapper.PlatformMapper;
import cn.bupt.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("platformService")
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private PlatformMapper platformMapper;

    public List<Platform> listAllPlatform() {
        return platformMapper.listAllPlatform();
    }

    public String savePlatform(String name, String url, String phone, String mail) {
        Platform platform = new Platform();
        platform.setMail(mail);
        platform.setName(name);
        platform.setPhone(phone);
        platform.setUrl(url);
        platform.setStatus(0);
        platformMapper.savePlatform(platform);
        return "保存成功";
    }

    public Integer saveChat(String from, String to, String content, String chatId) {
        Chat chat = new Chat();
        chat.setFromId(from);
        chat.setToId(to);
        chat.setContent(content);
        chat.setChatId(chatId);
        chat.setStatus(0);
        chat.setChatTime(new Date());
        platformMapper.saveChat(chat);
        return chat.getId();
    }

    public String read(Integer id) {
        platformMapper.read(id);
        return "保存成功";
    }

    public Integer query(String from) {
        List<Chat> chatList = platformMapper.query(from);
        return chatList.size();
    }

    public List<List<ChatTrans>> queryAll(String from) {
        List<List<ChatTrans>> result = new ArrayList();
        List<Chat> chatList = platformMapper.queryAll(from);
        Map<String, List<ChatTrans>> resultMap = new HashMap();
        for (Chat chat : chatList) {
            String chatId = chat.getChatId();
            ChatTrans chatTrans = new ChatTrans();
            chatTrans.setId(chat.getId());
            chatTrans.setFrom(chat.getFromId());
            chatTrans.setTo(chat.getToId());
            chatTrans.setContent(chat.getContent());
            chatTrans.setChatId(chatId);
            chatTrans.setRead(chat.getStatus());
            chatTrans.setTime(chat.getChatTime());
            if (resultMap.containsKey(chatId)) {
                List<ChatTrans> chatTransList = resultMap.get(chatId);
                chatTransList.add(chatTrans);
            } else {
                List<ChatTrans> chatTransList = new ArrayList();
                chatTransList.add(chatTrans);
                resultMap.put(chatId, chatTransList);
            }
        }
        for (List<ChatTrans> chatTransList : resultMap.values()) {
            result.add(chatTransList);
        }
        return result;
    }

    public List<ChatTrans> queryContent(String from, String to, String chatId) {
        Chat chatQuery = new Chat();
        chatQuery.setFromId(from);
        chatQuery.setToId(to);
        chatQuery.setChatId(chatId);
        List<Chat> chatList = platformMapper.queryContent(chatQuery);
        List<ChatTrans> chatTransList = new ArrayList();
        for (Chat chat : chatList) {
            ChatTrans chatTrans = new ChatTrans();
            chatTrans.setId(chat.getId());
            chatTrans.setFrom(chat.getFromId());
            chatTrans.setTo(chat.getToId());
            chatTrans.setContent(chat.getContent());
            chatTrans.setChatId(chatId);
            chatTrans.setRead(chat.getStatus());
            chatTrans.setTime(chat.getChatTime());
            chatTransList.add(chatTrans);
        }
        return chatTransList;
    }

    public Map<String, Object> platform(String serviceArea) {
        Map<String, Object> platformInformation = new HashMap();
        List<Platform> platformList = listAllPlatform();
        Platform platform = platformList.get(platformList.size() - 1);
        platformInformation.put("id", platform.getId());
        platformInformation.put("url", platform.getUrl());
        return platformInformation;
    }
}
