package com.tengda.dazahui.system.service.impl;

import com.tengda.dazahui.conmon.utils.DateUtil;
import com.tengda.dazahui.system.dao.RabbitMqMapper;
import com.tengda.dazahui.system.domian.Order;
import com.tengda.dazahui.system.rabbitmq.CancelOrderReceiver;
import com.tengda.dazahui.system.rabbitmq.CancelOrderSender;
import com.tengda.dazahui.system.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author teswell
 * @Date 2020/12/10 15:33
 * @function
 */
@Service
public class RabbitMqServiceImpl implements RabbitMqService {
    private static Logger logger = LoggerFactory.getLogger(RabbitMqServiceImpl.class);


    @Autowired(required = false)
    private RabbitMqMapper rabbitMqMapper;

    @Autowired
    private CancelOrderReceiver cancelOrderReceiver;
    @Autowired
    private CancelOrderSender cancelOrderSender;

    /**
     * 新增订单
     *
     * @param order
     */
    @Override
    public void orderSave(Order order) {
        order.setOrderSn(UUID.randomUUID().toString());
        order.setCreateTime(DateUtil.getCurrentTime());
        rabbitMqMapper.orderSave(order);
        logger.info("新增数据库成功");
        //下单完成后开启一个延迟消息,用于用户没有付款时取消订单,(orderId应该在下单后生成)
        sendDelayMessageCncelOrder(Long.valueOf(order.getId()));
    }

    /**
     * 取消订单操作
     *
     * @param orderId
     */
    @Override
    public void cancelOrder(Long orderId) {
        try {
            Integer aa = rabbitMqMapper.findByOrderId(orderId);
            if (aa != 0) {
                rabbitMqMapper.cancelOrder(orderId);
            }
            logger.info("取消订单成功", orderId);
        } catch (Exception e) {
            e.getMessage();
            logger.info("取消订单失败", orderId);
        }
    }

    private void sendDelayMessageCncelOrder(Long orderId) {
        //获取订单超时,假设为1分钟
        long dalayTimes = 1000 ;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, dalayTimes);
    }

}
