package cn.bupt.mapper;

import cn.bupt.entity.Chat;
import cn.bupt.entity.Platform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlatformMapper {
    List<Platform> listAllPlatform();

    void savePlatform(Platform platform);

    Integer saveChat(Chat chat);

    void read(Integer id);

    List<Chat> query(String from);

    List<Chat> queryAll(String from);

    List<Chat> queryContent(Chat chat);
}