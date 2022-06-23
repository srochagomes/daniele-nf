package com.daniele.nfe.listerns;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FileFailListern {

    @RabbitListener(queues = "nfe.file.fail")
    public void execute(Object notaFiscal) {

        System.out.println("Mensagem falha = "+notaFiscal);

    }
}
