package com.tengda.dazahui.system.controller;

import com.tengda.dazahui.system.domian.User;
import com.tengda.dazahui.system.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author teswell
 * @Date 2020/11/10 11:41
 * @function
 */
@Controller
public class MomentController {
    @Autowired(required = false)
    private MomentService momentService;

    @RequestMapping("/moment")
    public String moment() {
        return "system/datile";
    }

    @RequestMapping("/aaa")
    public @ResponseBody
    String aa() {
        return "hello word";
    }

    @RequestMapping("/ceshi")
    public @ResponseBody
    List<User> ceshi() {
        List<User> userList = momentService.shuju();
        return userList;
    }
    //增加一个法方测试提交是否成功
}
