package com.plumre.consume;

import com.plumre.common.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/*
 * Created by renhongjiang on 2019/4/29.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderSenderTest {

    @Autowired
    private OrderSender orderSender;
    @Test
    public void sendOrder() {
        Order order = new Order("190419001","订单一",System.currentTimeMillis()+"$"+ UUID.randomUUID());
        orderSender.sendOrder(order);
    }
}