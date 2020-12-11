package com.tengda.dazahui.system.service;

import com.tengda.dazahui.system.domian.Order;
import org.springframework.transaction.annotation.Transactional;

public interface RabbitMqService {
    @Transactional
    void orderSave(Order order);
    @Transactional
    void cancelOrder(Long orderId);
}
