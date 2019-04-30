package com.plumre.consume;

/*
 * Created by renhongjiang on 2019/3/4.
 */

import com.plumre.common.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * TODO
 *
 * @author renhongjiang
 * @version 1.0
 * @date 2019/3/4 11:12
 */
@Component
public class OrderSender {

//    @EnableRabbit(
//            bindings = @QueueBinding(
//                    value = @Queue(value = "order-queue", durable = "true"),
//                    exchange = @Exchange(value = "order-exchange", type = "topic"),
//                    key = "order.*"
//            ))
//    @RabbitHandler
//    public void onOrderMessage(@Payload Order order,
//                               @Headers Map<String, Object> headers,
//                               Channel channel) throws Exception {
//        // 消费者操作：
//        System.err.println("----------收到消息，开始消费----------");
//        System.err.println("订单ID：" + order.getId());
//        System.err.println("消息ID："  + order.getMessageId());
//
//        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
//        // 手工ACK
//        channel.basicAck(deliveryTag, false);
//        channel.close();
//    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrder(Order order) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend(
                "order-exchange",
                "order.abcd",
                order,
                correlationData);
    }
}