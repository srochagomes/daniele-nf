package com.daniele.nfe.listerns;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FileSuccessListern {

    @RabbitListener(queues = "nfe.file.success")
    public void execute(Object notaFiscal) {

        System.out.println("Mensagem success = "+notaFiscal);

    }
}
