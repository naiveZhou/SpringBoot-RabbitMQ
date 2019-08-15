package com.example.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title
 * @Autor zxf
 * @Date 2019/8/15
 */
@Configuration
public class QueueConfig {

    private  static final Logger logger = LoggerFactory.getLogger(QueueConfig.class);

    //队列1
    private static final String QUEUE_1 = "queue_1";

    //队列2
    private static final String QUEUE_2 = "queue_2";

    //订阅模式交换机
    private static final String FANOUT_EXCHANGE = "fanout_exchange";

    //路由模式交换机
    private static final String DIRECT_EXCHANGE = "direct_exchange";

    //主题模式交换机
    private static final String TOPIC_EXCHANGE = "topic_exchange";

    //路由键routing_key1
    private static final String ROUTING_KEY1 = "routing_key1";

    //路由键routing_key2
    private static final String ROUTING_KEY2 = "routing_key2";

    //符号*匹配一个词
    public static final String TOPIC_ROUTINGKEY1 = "hello.*";

    //符号#匹配一个或多个词
    public static final String TOPIC_ROUTINGKEY2 = "hello.#";


    /**
     *  创建订阅模式交换机
     *  @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE,true, false);
    }

    /**
     * 创建路由模式交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE,true, false);
    }

    /**
     * 创建主题模式交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE,true, false);
    }



    /**
     * 创建队列1
     * @return
     */
    @Bean
    public Queue Queue1() {
        //队列持久化
        return new Queue(QUEUE_1, true);
    }

    /**
     * 创建队列2
     * @return
     */
    @Bean
    public Queue Queue2() {
        return new Queue(QUEUE_2, true);
    }

    /**
     * 订阅模式队列1绑定交换机
     * @return
     */
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(Queue1()).to(fanoutExchange());
    }

    /**
     * 订阅模式队列2绑定交换机
     * @return
     */
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(Queue2()).to(fanoutExchange());
    }

    /**
     * 路由模式队列1绑定交换机,通过key1发送
     * @return
     */
    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(Queue1()).to(directExchange()).with(ROUTING_KEY1);
    }

    /**
     * 路由模式队列2绑定交换机,通过key2发送
     * @return
     */
    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(Queue2()).to(directExchange()).with(ROUTING_KEY2);
    }

    /**
     * 主题模式队列1绑定交换机
     * 符号“#”匹配一个或多个词，符号“*”匹配一个词。比如“hello.#”能够匹配到“hello.123.456”，但是“hello.*”只能匹配到“hello.123”
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(Queue1()).to(topicExchange()).with(TOPIC_ROUTINGKEY1);
    }

    /**
     * 主题模式队列1绑定交换机
     * 符号“#”匹配一个或多个词，符号“*”匹配一个词。比如“hello.#”能够匹配到“hello.123.456”，但是“hello.*”只能匹配到“hello.123”
     * @return
     */
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(Queue2()).to(topicExchange()).with(TOPIC_ROUTINGKEY2);
    }


}
