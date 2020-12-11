package com.tengda.dazahui.system.controller;

import com.tengda.dazahui.system.domian.Order;
import com.tengda.dazahui.system.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author teswell
 * @Date 2020/12/10 14:56
 * @function
 */
@Controller
public class RabbitMqController {

    @Autowired
    private RabbitMqService rabbitMqService;

    @RequestMapping("/rabbitmq")
    @ResponseBody
    public void rabbitMq(@RequestBody Order order) {
        rabbitMqService.orderSave(order);
    }
}
