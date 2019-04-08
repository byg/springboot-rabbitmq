package net.ibyg.rabbitmq.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: byg
 * @Date: 2019/4/8 0008 上午 10:21
 * @Version: 1.0
 */

@Component
@Slf4j
//@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MessageReceiver {

    @RabbitListener(queues = SimpleRabbitConfig.QUEUE_A)
    public void process_a(String content) {
        log.info("消费队列A: {}", content);
    }

    // 支持多个消费这同时消费队列A
    @RabbitListener(queues = SimpleRabbitConfig.QUEUE_A)
    public void process_a_1(String content) {
        log.info("消费队列A_1: {}", content);
    }

    @RabbitListener(queues = CustomRabbitConfig.QUEUE_B)
    public void process_b(Object object) {
        log.info("消费队列B: " + object);
        if (object instanceof Map) {
            Map<String, Object> map = (Map) object;
            log.info(map.get("content") + " " + map.get("time"));
        }
    }
}
