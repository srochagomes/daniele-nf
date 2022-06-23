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
public class QueueSuccesProcess {

    @Bean
    public Queue fileNfeSuccessQueue() {
        return QueueBuilder
                .durable("nfe.file.success")
                .deadLetterExchange("nfe")
                .deadLetterRoutingKey("nf.imported.success.dead-letter")
                .build();

    }

    @Bean
    public Exchange fileNfeExchange() {
        return ExchangeBuilder.topicExchange("nfe").durable(true).build();
    }

    @Bean
    public Binding criaNfeSuccessBinding() {
        return BindingBuilder
                .bind(this.fileNfeSuccessQueue())
                .to(this.fileNfeExchange())
                .with("nf.imported.success")
                .noargs();
    }

    @Bean
    public Queue fileSuccessQueueDeadLetter() {
        return QueueBuilder
                .durable("nfe.file.success.deadletter")
                .build();

    }

    @Bean
    public Binding fileSuccessQueueDeadLetterBinding() {
        return BindingBuilder
                .bind(this.fileSuccessQueueDeadLetter())
                .to(this.fileNfeExchange())
                .with("nf.imported.success.dead-letter")
                .noargs();
    }


}
