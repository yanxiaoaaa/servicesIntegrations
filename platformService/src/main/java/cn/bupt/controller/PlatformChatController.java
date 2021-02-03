package cn.bupt.controller;

import cn.bupt.dto.CommonResponseDTO;
import cn.bupt.entity.ChatTrans;
import cn.bupt.service.PlatformService;
import cn.bupt.utils.ResponseCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/chat")
public class PlatformChatController {
    @Autowired
    private PlatformService platformService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResponseDTO<Integer> savePlatform(@RequestParam(required = true, value = "from") String from,
                                                   @RequestParam(required = true, value = "to") String to,
                                                   @RequestParam(required = true, value = "content") String content,
                                                   @RequestParam(required = true, value = "chatId") String chatId) {
        CommonResponseDTO<Integer> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.saveChat(from, to, content, chatId));
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

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public CommonResponseDTO<String> read(@RequestParam(required = true, value = "id") Integer id) {
        CommonResponseDTO<String> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.read(id));
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

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public CommonResponseDTO<Integer> query(@RequestParam(required = true, value = "from") String from) {
        CommonResponseDTO<Integer> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.query(from));
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

    @RequestMapping(value = "/query/all", method = RequestMethod.GET)
    public CommonResponseDTO<List<List<ChatTrans>>> queryAll(@RequestParam(required = true, value = "from") String from) {
        CommonResponseDTO<List<List<ChatTrans>>> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.queryAll(from));
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

    @RequestMapping(value = "/query/content", method = RequestMethod.GET)
    public CommonResponseDTO<List<ChatTrans>> queryContent(@RequestParam(required = true, value = "from") String from,
                                                           @RequestParam(required = true, value = "to") String to,
                                                           @RequestParam(required = true, value = "chatId") String chatId) {
        CommonResponseDTO<List<ChatTrans>> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.queryContent(from, to, chatId));
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

    @RequestMapping(value = "/platform", method = RequestMethod.GET)
    public CommonResponseDTO<Map<String, Object>> platform(@RequestParam(required = true, value = "serviceArea") String serviceArea) {
        CommonResponseDTO<Map<String, Object>> responseDTO = new CommonResponseDTO();
        try {
            responseDTO.setData(platformService.platform(serviceArea));
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
