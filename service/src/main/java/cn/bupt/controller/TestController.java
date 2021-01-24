package cn.bupt.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

//    @Autowired
//    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/platform/post", method = RequestMethod.POST)
    public String testPost(@RequestParam(required = false, value = "name", defaultValue = "test-service") String name) {
//        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        return "hello " + name + ",this is platform-service";
    }

    @RequestMapping(value = "/platform/get", method = RequestMethod.GET)
    public String testGet(@RequestParam(required = false, value = "name", defaultValue = "test-service") String name) {
//        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        return "hello " + name + ",this is platform-service";
    }

    @RequestMapping(value = "/platform/fuse", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getFail")
    public String testFuse(@RequestParam(required = false, value = "name", defaultValue = "test-service") String name) {
//        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        return String.valueOf(1 / 0);
    }

    public String getFail(String name) {
        return name+" 触发熔断";
    }
}
