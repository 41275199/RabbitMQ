package com.example.roc.service.impl;

import com.example.roc.common.RespJson;
import com.example.roc.dao.RocDao;
import com.example.roc.entity.Order;
import com.example.roc.service.RocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RocServiceImpl implements RocService {

    @Autowired
    private RocDao rocDao;

    @Override
    public int order(Order order) {
        return rocDao.insert(order);
    }

    @Override
    public Order selectById(String orderId) {
        return rocDao.selectById(orderId);
    }

    @Override
    public RespJson orderList() {
        List<Order> orderList = rocDao.orderList();
        return RespJson.success(orderList);
    }

    @Override
    public void closeOrder(String orderNum) {
        rocDao.updOrder(orderNum);
    }
}
