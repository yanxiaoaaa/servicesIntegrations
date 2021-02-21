package cn.bupt.service;

import cn.bupt.utils.ServiceList;
import org.springframework.web.client.RestTemplate;

public interface PlatformService {
    ServiceList initList(ServiceList head);

    String query(ServiceList head);

    void distribution(ServiceList head, RestTemplate restTemplate);
}
