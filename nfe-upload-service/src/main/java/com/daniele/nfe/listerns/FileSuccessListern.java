package com.daniele.nfe.listerns;

import com.daniele.nfe.domain.NotaFiscal;
import com.daniele.nfe.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FileSuccessListern {

    private StorageService storageService;

    @RabbitListener(queues = "nfe.file.success")
    public void execute(NotaFiscal notaFiscal) {

        storageService.fileUploadSuccess(notaFiscal);
        System.out.println("Mensagem success = "+notaFiscal);

    }
}
