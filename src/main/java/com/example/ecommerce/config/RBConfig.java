package com.example.ecommerce.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RBConfig {

    public static final String MAIL_QUEUE = "email_queue";
    public static final String CUSTOMER_QUEUE = "signup_queue";
    public static final String ORDER_QUEUE = "order_queue";

    public static final String EXCHANGE = "exchange";

    public static final String ROUTING_KEY_EMAIL = "email_routingKey";
    public static final String ROUTING_KEY_CUSTOMER = "customer_routingKey";
    public static final String ROUTING_KEY_ORDER = "user_routingKey";

    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE);
    }
    @Bean
    public Queue customerQueue() {
        return new Queue(CUSTOMER_QUEUE);
    }
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingEmail(Queue mailQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(mailQueue)
                .to(exchange)
                .with(ROUTING_KEY_EMAIL);
    }

    @Bean
    public Binding bindingUser(Queue customerQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(customerQueue)
                .to(exchange)
                .with(ROUTING_KEY_CUSTOMER);
    }

    @Bean
    public Binding bindingOrder(Queue orderQueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(exchange)
                .with(ROUTING_KEY_ORDER);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
