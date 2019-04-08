package net.ibyg.rabbitmq.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 使用默认的 ConnectionFactory
 * @Author: byg
 * @Date: 2019/4/8 0008 上午 9:59
 * @Version: 1.0
 */

//@Configuration
public class SimpleRabbitConfig {

    public static final String QUEUE_A = "queue_a";
    public static final String EXCHANGE_A = "exchange_a";
    public static final String ROUTING_A = "routing_a";

    public static final String QUEUE_B = "queue_b";
    public static final String EXCHANGE_B = "exchange_b";
    public static final String ROUTING_B = "routing_b";

    @Bean
    public Queue queueA(){
        return new Queue(QUEUE_A);
    }

    @Bean
    public Queue queueB(){
        return new Queue(QUEUE_B);
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(ROUTING_A);
    }

    // 一个交换机可以绑定多个队列
    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(ROUTING_B);
    }

}
