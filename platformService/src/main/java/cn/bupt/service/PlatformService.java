package cn.bupt.service;

import cn.bupt.entity.Platform;

import java.util.List;

public interface PlatformService {
    List<Platform> listAllPlatform();

    String savePlatform();
}
