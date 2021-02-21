package cn.bupt.service.impl;

import cn.bupt.service.PlatformService;
import cn.bupt.utils.ServiceList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("platformService")
public class PlatformServiceImpl implements PlatformService {
    @Override
    public ServiceList initList(ServiceList head) {
        if (head == null) {
            head = new ServiceList();
            ServiceList curService = head;
            List<Integer> list = new ArrayList();
            list.add(1);
            ServiceList newService = new ServiceList("1", list);
            curService.next = newService;
            curService = curService.next;
            list.clear();
            list.add(2);
            list.add(3);
            newService = new ServiceList("1", list);
            curService.next = newService;
            curService = curService.next;
            list.clear();
            list.add(4);
            newService = new ServiceList("1", list);
            curService.next = newService;
        }
        return head;
    }

    public String query(ServiceList head) {
        return head.toString();
    }

    public void distribution(ServiceList head, RestTemplate restTemplate) {
        int completeSize = head.complete.size();
        ServiceList curService = head.next;
        while (completeSize > 0 && curService != null) {
            completeSize -= curService.platform.size();
            curService = curService.next;
        }
        if (completeSize == 0 && curService != null) {
            Map<Integer, String> map = this.getMap();
            for (Integer integer : curService.platform) {
                String serviceName = map.get(integer);
                System.out.println(serviceName);
                restTemplate.getForObject("http://" + serviceName + "/platform/complete?id=1&number=" + integer, String.class);
            }
        }
    }

    private Map<Integer, String> getMap() {
        Map<Integer, String> result = new HashMap();
        result.put(1, "service1");
        result.put(2, "service1");
        result.put(3, "service1");
        result.put(4, "service1");
        return result;
    }
}
