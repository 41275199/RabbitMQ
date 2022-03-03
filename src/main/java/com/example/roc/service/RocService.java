package com.example.roc.service;

import com.example.roc.common.RespJson;
import com.example.roc.entity.Order;

public interface RocService {
    int order(Order order);

    Order selectById(String orderId);

    RespJson orderList();

    void closeOrder(String orderNum);
}
