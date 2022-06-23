package com.daniele.nfe.config;

import com.rabbitmq.client.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionRabbitMQ {

    @Configuration
    public class CamelConfiguration {



        @Bean
        public ConnectionFactory rabbitConnectionFactoryFirst() {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.1.0.0");
            factory.setPort(5672);
            factory.setUsername("rabbitmq");
            factory.setPassword("rabbitmq");
            return factory;
        }
    }
}
