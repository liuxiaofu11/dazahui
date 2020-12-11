package com.tengda.dazahui.system.rabbitmq;

import com.tengda.dazahui.system.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author teswell
 * 取消订单消息的消费者
 * @Date 2020/12/10 17:15
 * @function
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static Logger logger = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private RabbitMqService rabbitMqService;

    @RabbitHandler
    public void handle(Long orderId) {
        logger.info("获取到取消订单消息对列", orderId);
        rabbitMqService.cancelOrder(orderId);
    }

}
