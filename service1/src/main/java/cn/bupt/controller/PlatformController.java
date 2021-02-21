package cn.bupt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping(value = "/platform")
public class PlatformController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public void complete(@RequestParam(required = true, value = "id", defaultValue = "1") String id,
                         @RequestParam(required = true, value = "number") Integer number) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int time = random.nextInt(30);
                try {
                    Thread.sleep(time * 1000);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                restTemplate.getForObject("http://platform/service/complete?id=" + id + "&number=" + number, String.class);
            }
        }).start();
    }
}
