package com.tengda.dazahui.system.domian;

import lombok.Data;
import org.springframework.core.OrderComparator;

import java.io.Serializable;

/**
 * @Author teswell
 * @Date 2020/12/10 15:38
 * @function
 */
@Data
public class Order implements Serializable {

    private Integer id;

    private String orderSn;

    private String createTime;

    private double totalAmount;

    private Integer payType;

    private Integer status;
}
