package com.example.roc.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    public static final String HANDLE_ORDER_DEAD_EXCHANGE = "handle_order_dead_exchange";
    public static final String HANDLE_ORDER_DEAD_QUEUE = "handle_order_dead_queue";
    public static final String HANDLE_ORDER_ROUTING_KEY = "handle_order_routing_key";

    @Bean
    public Exchange handleOrderDeadExchange() {
        return new TopicExchange(HANDLE_ORDER_DEAD_EXCHANGE, true, false);
    }

    @Bean
    public Queue handleOrderDeadQueue() {
        return QueueBuilder.durable(HANDLE_ORDER_DEAD_QUEUE).build();
    }

    @Bean
    public Binding handleOrderBinding() {
        return new Binding(HANDLE_ORDER_DEAD_QUEUE, Binding.DestinationType.QUEUE, HANDLE_ORDER_DEAD_EXCHANGE, HANDLE_ORDER_ROUTING_KEY, null);
    }

    public static final String WAIT_ORDER_EXCHANGE = "wait_order_exchange";
    public static final String WAIT_ORDER_QUEUE = "wait_order_queue";
    public static final String WAIT_ORDER_ROUTING_KEY = "wait_order_routing_key";

    @Bean
    public Exchange waitOrderExchange() {
        return new TopicExchange(WAIT_ORDER_EXCHANGE, true, false);
    }

    @Bean
    public Queue waitOrderQueue() {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-dead-letter-exchange", HANDLE_ORDER_DEAD_EXCHANGE);
        args.put("x-dead-letter-routing-key", HANDLE_ORDER_ROUTING_KEY);
        args.put("x-message-ttl", 1000 * 60); //time to live    set 60s
        return QueueBuilder.durable(WAIT_ORDER_QUEUE).withArguments(args).build();
    }

    @Bean
    public Binding waitOrderBinding() {
        return new Binding(WAIT_ORDER_QUEUE, Binding.DestinationType.QUEUE, WAIT_ORDER_EXCHANGE, WAIT_ORDER_ROUTING_KEY, null);
    }
}
