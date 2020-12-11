package com.tengda.dazahui.system.dao;

import com.tengda.dazahui.system.domian.Order;
import org.apache.ibatis.annotations.Param;

public interface RabbitMqMapper {
    void orderSave(@Param("order") Order order);

    void cancelOrder(@Param("orderId") Long orderId);

    Integer findByOrderId(@Param("orderId") Long orderId);

}
