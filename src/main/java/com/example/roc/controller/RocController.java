package com.example.roc.controller;

import com.example.roc.common.RespJson;
import com.example.roc.config.RabbitMQConfig;
import com.example.roc.entity.Order;
import com.example.roc.service.RocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(tags = "控制器")
@RestController
public class RocController {

    @Autowired
    private RocService rocService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation("模拟下单")
    @PostMapping("/order")
    public RespJson order() {
        Order order = new Order();
        String orderId = UUID.randomUUID().toString();
        order.setOrderNum(orderId);
        int row = rocService.order(order);
        order = rocService.selectById(orderId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.WAIT_ORDER_EXCHANGE, RabbitMQConfig.WAIT_ORDER_ROUTING_KEY, orderId);
        return RespJson.operationResult(row, order);
    }

    @ApiOperation("订单列表")
    @GetMapping("/orderList")
    public RespJson orderList() {
        return rocService.orderList();
    }
}
