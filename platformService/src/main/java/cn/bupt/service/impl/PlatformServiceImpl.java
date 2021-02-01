package cn.bupt.service.impl;

import cn.bupt.entity.Platform;
import cn.bupt.mapper.PlatformMapper;
import cn.bupt.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("platformService")
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private PlatformMapper platformMapper;

    public List<Platform> listAllPlatform() {
        return platformMapper.listAllPlatform();
    }

    public String savePlatform() {

        return "保存成功";
    }
}
