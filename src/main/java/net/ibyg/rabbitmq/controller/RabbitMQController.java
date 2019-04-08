package net.ibyg.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import net.ibyg.rabbitmq.amqp.CustomMessageSender;
import net.ibyg.rabbitmq.amqp.SimpleMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: byg
 * @Date: 2019/4/8 0008 上午 10:29
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Autowired
    public SimpleMessageSender sender;

    @Autowired
    public CustomMessageSender customMessageSender;

    @RequestMapping("/sendA")
    public String sendAMessage() {
        log.info("sender: {}",sender.toString());
        sender.send_A("hello from RabbitMQController.sendAMessage " + new Date());
        return "SendA success " + new Date();
    }

    @RequestMapping("/sendB")
    public String sendBMessage() {
        log.info("sender: {}",customMessageSender.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("time", new Date());
        map.put("content", "hello from RabbitMQController.sendBMessage");
        customMessageSender.send_B(map);
        return "SendB success " + new Date();
    }
}
