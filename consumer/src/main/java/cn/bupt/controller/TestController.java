package cn.bupt.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    //这里注入的restTemplate就是在com.sam.ConsumerApp中通过@Bean配置的实例
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/platform-consumer/get", method = RequestMethod.GET)
    public String testGet(@RequestParam(required = false, value = "name", defaultValue = "test-service") String name) {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
        String result = restTemplate.getForObject("http://platform-service/test/platform/get?name=" + name, String.class);
        return result;
    }

    @RequestMapping(value = "/platform-consumer/post", method = RequestMethod.GET)
    public String testPost(@RequestParam(required = false, value = "name", defaultValue = "test-service") String name) {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("name", name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> result = restTemplate.postForEntity("http://service/test/platform/post", request, String.class);
        return result.getBody();
    }

    @RequestMapping(value = "/platform-consumer/post/json", method = RequestMethod.GET)
    public String testPostJson(@RequestParam(required = false, value = "name", defaultValue = "test-service") String name) {
        //调用hello-service服务，注意这里用的是服务名，而不是具体的ip+port
        JSONObject postData = new JSONObject();
        postData.put("name", name);
        ResponseEntity<String> result = restTemplate.postForEntity("http://service/test/platform/post", postData, String.class);
//        JSONObject result = restTemplate.postForEntity("http://service/test/platform/post", postData, JSONObject.class).getBody();
        return result.getBody();
    }

    @RequestMapping(value = "/platform-consumer/fuse", method = RequestMethod.GET)
    public String testFuse(@RequestParam(required = false, value = "name", defaultValue = "test-service") String name) {
        String result = restTemplate.getForObject("http://service/test/platform/fuse?name=" + name, String.class);
        return result;
    }
}