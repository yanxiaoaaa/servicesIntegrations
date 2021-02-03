package cn.bupt.service.impl;

import cn.bupt.entity.Chat;
import cn.bupt.entity.ChatTrans;
import cn.bupt.entity.Platform;
import cn.bupt.mapper.PlatformMapper;
import cn.bupt.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service("platformService")
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private PlatformMapper platformMapper;

    private static final String USER = "790039090@qq.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "unrpadzwzxabbcej"; // 如果是qq邮箱可以使户端授权码，或者登录密码


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
        this.sendMail("2018140485@bupt.cn","您好，集成平台中有用户咨询您问题:"+content+"--------请及时回复","集成平台咨询提醒");
        return chat.getId();
    }

    /**
     * @param to    收件人邮箱
     * @param text  邮件正文
     * @param title 标题
     */
    private Boolean sendMail(String to, String text, String title){
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");

            // 发件人的账号
            props.put("mail.user", USER);
            //发件人的密码
            props.put("mail.password", PASSWORD);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);

            // 设置邮件标题
            message.setSubject(title);

            // 设置邮件的内容体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
