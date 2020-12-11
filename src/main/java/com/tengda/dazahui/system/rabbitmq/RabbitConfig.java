package com.tengda.dazahui.system.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author teswell
 * @Date 2020/12/10 10:17
 * @function
 */
@Configuration
public class RabbitConfig {

    /**
     * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
     * Queue:消息的载体,每个消息都会被投到一个或多个队列。
     * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
     * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
     * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
     * Producer:消息生产者,就是投递消息的程序.
     * Consumer:消息消费者,就是接受消息的程序.
     * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     */

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    //交换机
    public static final String EXCHANGE_A = "my_mq_exchange_A";
    public static final String EXCHANGE_B = "mu_mq_exchange_B";
    public static final String EXCHANGE_C = "my_mq_exchange_C";

    //对列
    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    //路由
    public static final String ROUTINGKEY_A = "spring_boot_routingkey_A";
    public static final String ROUTINGKEY_B = "spring_boot_routingkey_B";
    public static final String ROUTINGKEY_C = "spring-boot_routingkey_C";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 针对消费者配置
     * 1设置交换机类型
     * 2将对列绑定到交换机
     * FanoutExchange: 将消息分发到所有的绑定对列,无routingkey的概念
     * HeadersExchange: 将通过添加属性key-value匹配
     * DirectExchangge: 按照rouutingkey分发到指定对列
     * TopicExchange:多关键字匹配
     * 交换机
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 获取对列A
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true);
    }

    /**
     * 对列B
     */
    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B, true);
    }

    /**
     * 对列A绑定到交换机A,通过路由key_A
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

    /**
     * 对列B绑定到交换机A ,通过路由key_A   ....此对列B与对列A形成一个交换机绑定多个对列的案例
     *
     * @return
     */
    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_B);
    }



}
