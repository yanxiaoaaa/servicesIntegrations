package cn.bupt.controller;

import cn.bupt.dto.CommonResponseDTO;
import cn.bupt.entity.Platform;
import cn.bupt.service.PlatformService;
import cn.bupt.utils.ResponseCodeEnum;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/platform")
public class PlatformJoinController {

    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResponseDTO<List<Platform>> listAllPlatform() {
        CommonResponseDTO<List<Platform>> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.listAllPlatform());
            responseDTO.setMsg(null);
            responseDTO.setCode(ResponseCodeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setData(null);
            responseDTO.setCode(ResponseCodeEnum.FAIL.getCode());
            responseDTO.setMsg(e.getMessage());
        }
        return responseDTO;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResponseDTO<String> savePlatform(@RequestParam(required = true, value = "name")String name) {
        CommonResponseDTO<String> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.savePlatform());
            responseDTO.setMsg(null);
            responseDTO.setCode(ResponseCodeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDTO.setData(null);
            responseDTO.setCode(ResponseCodeEnum.FAIL.getCode());
            responseDTO.setMsg(e.getMessage());
        }
        return responseDTO;
    }
}
