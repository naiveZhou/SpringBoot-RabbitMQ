# SpringBoot-RabbitMQ
本案例为springboot整合rabbitmq的五种工作模式。

springboot不需要在启动类前加@EnableRabbit和新建ConnectionFactory(),因为可以在yml里配置rabbitmq的选项。

本案例开启的消息确认机制,持久化了交换机,队列以及消息,消费者端开启了手动ACK,保证非极端情况下消息的可靠性。

配置简单,doem中有详细的注释,通俗易懂

五种工作模式

官网介绍：https://www.rabbitmq.com/getstarted.html

这里简单介绍下五种工作模式的主要特点：

简单模式：一个生产者，一个消费者

work模式：一个生产者，多个消费者，每个消费者获取到的消息唯一。

订阅模式：一个生产者发送的消息会被多个消费者获取。

路由模式：发送消息到交换机并且要指定路由key ，消费者将队列绑定到交换机时需要指定路由key

topic模式：将路由键和某模式进行匹配，此时队列需要绑定在一个模式上，“#”匹配一个词或多个词，“*”只匹配一个词。

