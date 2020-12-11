package com.tengda.dazahui.system.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author teswell
 * @Date 2020/12/10 13:39
 * @function
 */
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //由于rabbittemp的scope属性设置为configurableBeanfactory.scope_prototype,所以不能自动注入
    private RabbitTemplate rabbitTemplate;

    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); // rabbittemplate 如果为单例的话,那回调就是最后设置的内容
    }
    public  void sendMsg(String content){
        CorrelationData correlationDataId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTUNGKEY_A  对应的对列当中去,对应的是对列A
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A,RabbitConfig.ROUTINGKEY_A,content,correlationDataId);
    }


    /**
     * 回调
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
      logger.info("回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        }else {
            logger.info("消息消费失败:" + cause);
        }
    }
}
