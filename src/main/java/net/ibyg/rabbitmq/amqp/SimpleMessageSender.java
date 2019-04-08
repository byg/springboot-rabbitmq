package net.ibyg.rabbitmq.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: byg
 * @Date: 2019/4/8 0008 上午 10:07
 * @Version: 1.0
 */

@Component
public class SimpleMessageSender {

    @Autowired
    public AmqpTemplate amqpTemplate;

    public void send_A(String msg){
        amqpTemplate.convertAndSend(SimpleRabbitConfig.QUEUE_A, msg);
    }
}
