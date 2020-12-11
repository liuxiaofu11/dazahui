package com.tengda.dazahui.system.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author teswell
 * @Date 2020/12/10 16:00
 * 取消订单消息的发出者
 * @function
 */
@Component
public class CancelOrderSender  {
   private  static Logger logger = LoggerFactory.getLogger(CancelOrderSender.class);

   @Autowired
    private AmqpTemplate amqpTemplate;

   public  void sendMessage(Long orderId,final  long delayTimes){
       //给延迟对列发送消息
       amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {
           @Override
           public Message postProcessMessage(Message message) throws AmqpException {
               //给消息设置延迟毫秒值
               message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
               return message;
           }
       });
        logger.info("延迟对列发送成功{}" ,orderId);
   }
}
