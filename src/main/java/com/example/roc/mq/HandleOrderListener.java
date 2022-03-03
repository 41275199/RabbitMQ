package com.example.roc.mq;

import com.example.roc.config.RabbitMQConfig;
import com.example.roc.service.RocService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMQConfig.HANDLE_ORDER_DEAD_QUEUE)
public class HandleOrderListener {

    @Autowired
    private RocService rocService;

    @RabbitHandler
    public void releaseCouponRecord(String orderNum, Message message) {
        rocService.closeOrder(orderNum);
    }
}
