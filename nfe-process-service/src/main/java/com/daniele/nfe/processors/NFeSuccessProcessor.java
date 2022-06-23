package com.daniele.nfe.processors;

import com.daniele.nfe.domain.FormatData;
import com.daniele.nfe.domain.NfeDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.SimpleDateFormat;


public class NFeSuccessProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        String json = exchange.getMessage().getBody(String.class);
        ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
        mapper.setDateFormat(new SimpleDateFormat(FormatData.FORMATA_FULL));
        NfeDocumento nfe = mapper.readValue(json, NfeDocumento.class);
        exchange.getIn().setBody(mapper.writeValueAsString(nfe));
    }



}