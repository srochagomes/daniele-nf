package com.daniele.nfe.processors;

import com.daniele.nfe.domain.FormatData;
import com.daniele.nfe.domain.NfeDocumento;
import com.daniele.nfe.domain.NotaFiscal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.SimpleDateFormat;


public class NFeFailProcessor implements Processor {

    public void process(Exchange exchange) throws Exception{
        String fileNameSeq = exchange.getMessage().getHeaders().get("CamelFileName").toString();
        NotaFiscal notaFiscal = NotaFiscal.builder().id(Long.valueOf(fileNameSeq)).build();
        ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
        mapper.setDateFormat(new SimpleDateFormat(FormatData.FORMATA_FULL));

        exchange.getIn().setBody(mapper.writeValueAsString(notaFiscal));



    }



}