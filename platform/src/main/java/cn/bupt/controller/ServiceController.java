package cn.bupt.controller;

import cn.bupt.dto.CommonResponseDTO;
import cn.bupt.service.PlatformService;
import cn.bupt.utils.ResponseCodeEnum;
import cn.bupt.utils.ServiceList;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/service")
public class ServiceController {
    ServiceList head;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PlatformService platformService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CommonResponseDTO<String> query(@RequestParam(required = true, value = "id", defaultValue = "1") String id) {
        CommonResponseDTO<String> responseDTO = new CommonResponseDTO();
        try {
            head = platformService.initList(head);
            responseDTO.setData(platformService.query(head));
            responseDTO.setMsg(null);
            responseDTO.setCode(ResponseCodeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            responseDTO.setData(null);
            responseDTO.setCode(ResponseCodeEnum.FAIL.getCode());
            responseDTO.setMsg(e.getMessage());
        }
        return responseDTO;
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public void complete(@RequestParam(required = true, value = "id", defaultValue = "1") String id,
                         @RequestParam(required = true, value = "number") Integer number) {
        head.complete.add(number);
        platformService.distribution(head, restTemplate);
    }

    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    public CommonResponseDTO<String> distribution(@RequestParam(required = true, value = "id", defaultValue = "1") String id) {
        CommonResponseDTO<String> responseDTO = new CommonResponseDTO();
        try {
            head = platformService.initList(head);
            platformService.distribution(head,restTemplate);
            responseDTO.setData(platformService.query(head));
            responseDTO.setMsg(null);
            responseDTO.setCode(ResponseCodeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            responseDTO.setData(null);
            responseDTO.setCode(ResponseCodeEnum.FAIL.getCode());
            responseDTO.setMsg(e.getMessage());
        }
        return responseDTO;
    }
}
