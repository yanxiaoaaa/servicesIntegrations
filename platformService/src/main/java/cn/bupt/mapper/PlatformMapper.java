package cn.bupt.mapper;

import cn.bupt.entity.Platform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlatformMapper {
//    void insertPlatform();

    List<Platform> listAllPlatform();
}