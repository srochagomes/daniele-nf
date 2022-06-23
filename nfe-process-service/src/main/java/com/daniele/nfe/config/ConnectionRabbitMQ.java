package com.daniele.nfe.config;

import com.rabbitmq.client.ConnectionFactory;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionRabbitMQ {


    @Value("${rabbitmq.address}")
    private String address;

    @Value("${rabbitmq.port}")
    private Integer port;

    @Bean
    public ConnectionFactory rabbitConnectionFactoryFirst() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.address);
        factory.setPort(this.port);
        factory.setUsername("rabbitmq");
        factory.setPassword("rabbitmq");
        return factory;
    }


}
