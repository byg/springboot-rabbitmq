为什么使用消息队列?
- 解耦
- 异步
- 流量削峰

使用消息队列的坏处
- 系统可用性降低，如果消息队列挂掉，则系统就会异常
- 增加系统的复杂性，考虑高可用集群则更复杂

RabbitMQ 的几个概念

- 生产端（publisher）

- 代理服务端（broker）
    - 交换器（Exchange）：负责接收并转发消息到指定的队列
    - 队列（Queue）：存储消息，供消费者消费
    - 绑定（Binding）：实现交换器到队列的一对多绑定

- 消费端（consumer）

![image](https://upload-images.jianshu.io/upload_images/4237997-edeb65e914ef98ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/554/format/webp)


Exchange-Type 分四类

- direct（单播）
- fanout（广播）
- topic（发布订阅模式）
- headers（消息头匹配，已废弃）


VHost：实现权限分离，每个用户只能访问自己的一块。默认为 "/"


常见的问题：

1. 如何避免重复消费？
    - 数据库，唯一主键
    - redis，set 操作直接覆盖，幂等性
    - 保存消费记录的全局id，重复消费前先查询是否已消费过

1. 如果避免数据丢失？
    - 生产者数据丢失：事物模式或者confirm模式(返回ack)
    - 消息队列丢数据：持久化到磁盘。设置队列模式为durable，配合confirm，在返回ack之前持久化到磁盘中
    - 消费者丢数据：启动手动确认模式，如果消费者来不及消费就挂掉，没有相应ack，会重发给其他消费者

1. 保证消息的有序性
    - 将先后的消息放入到同一个消息队列中，然后用一个消费者去消费。
    - 保证入队有序，出队消费的顺序交给消费者自己保证

原理参考：https://www.jianshu.com/p/25816ae3d8db
代码实现参考：https://blog.csdn.net/qq_38455201/article/details/80308771