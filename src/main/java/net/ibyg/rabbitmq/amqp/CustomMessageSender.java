package net.ibyg.rabbitmq.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description:
 * @Author: byg
 * @Date: 2019/4/8 0008 上午 10:07
 * @Version: 1.0
 */

@Component
@Slf4j
public class CustomMessageSender implements RabbitTemplate.ConfirmCallback {

    // 由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    public RabbitTemplate rabbitTemplate;

    public CustomMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    public void send_B(Object msg) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(CustomRabbitConfig.EXCHANGE_B, CustomRabbitConfig.QUEUE_B, msg, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id:{}", correlationData);
        if (ack) {
            log.info("成功消费");
        } else {
            log.error("消费失败：" + cause);
        }
    }
}
