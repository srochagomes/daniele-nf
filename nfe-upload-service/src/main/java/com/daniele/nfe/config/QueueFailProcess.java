package com.daniele.nfe.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueueFailProcess {

    @Bean
    public Queue fileNfeFailQueue() {
        return QueueBuilder
                .durable("nfe.file.fail")
                .deadLetterExchange("nfe")
                .deadLetterRoutingKey("nf.imported.fail.dead-letter")
                .build();

    }

    @Bean
    public Exchange fileFileNfeExchange() {
        return ExchangeBuilder.topicExchange("nfe").durable(true).build();
    }

    @Bean
    public Binding criaNfeFailBinding() {
        return BindingBuilder
                .bind(this.fileNfeFailQueue())
                .to(this.fileFileNfeExchange())
                .with("nf.imported.fail")
                .noargs();
    }

    @Bean
    public Queue fileFailQueueDeadLetter() {
        return QueueBuilder
                .durable("nfe.file.fail.deadletter")
                .build();

    }

    @Bean
    public Binding fileFailQueueDeadLetterBinding() {
        return BindingBuilder
                .bind(this.fileFailQueueDeadLetter())
                .to(this.fileFileNfeExchange())
                .with("nf.imported.fail.dead-letter")
                .noargs();
    }


}
