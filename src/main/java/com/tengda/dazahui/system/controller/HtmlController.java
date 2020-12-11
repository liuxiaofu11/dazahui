package com.tengda.dazahui.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.tengda.dazahui.conmon.utils.CommonResponseDto;
import com.tengda.dazahui.system.dto.TestRequestDto;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author teswell
 * @Date 2020/12/11 15:36
 * @function
 */
@Controller
public class HtmlController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){

        return "test";
    }


    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public  @ResponseBody
    CommonResponseDto test(@RequestBody String request){
        TestRequestDto requestDto = JSONObject.parseObject(request, TestRequestDto.class);
        System.out.println(requestDto);
        return new CommonResponseDto().code(0).success(true);
    }

}
