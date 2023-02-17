package com.exchangeinformant.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private final CachingConnectionFactory connectionFactory;
    private static final String EXCHANGE_NAME = "stock.ex";
    private static final String QUEUE_NAME = "user.queue";

    public RabbitConfig(CachingConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }


    @Bean
    public Queue createQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Exchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding queueBiding() {
        return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE,EXCHANGE_NAME,"",null);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(simpleRabbitListenerContainerFactory,connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(converter());
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        simpleRabbitListenerContainerFactory.setDefaultRequeueRejected(false);
        return simpleRabbitListenerContainerFactory;
    }
}
