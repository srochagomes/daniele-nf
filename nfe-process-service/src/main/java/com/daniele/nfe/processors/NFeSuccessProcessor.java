package com.daniele.nfe.processors;

import com.daniele.nfe.domain.FormatData;
import com.daniele.nfe.domain.NfeDocumento;
import com.daniele.nfe.domain.NotaFiscal;
import com.daniele.nfe.domain.NotaFiscalDuplicata;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.SimpleDateFormat;


public class NFeSuccessProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        String fileNameSeq = exchange.getMessage().getHeaders().get("CamelFileName").toString();
        String json = exchange.getMessage().getBody(String.class);
        ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
        mapper.setDateFormat(new SimpleDateFormat(FormatData.FORMATA_FULL));
        NfeDocumento nfe = mapper.readValue(json, NfeDocumento.class);

        Long idDocto = Long.valueOf(fileNameSeq);
        NotaFiscal notaFiscal = NotaFiscal.builder()
                .id(idDocto)
                .numero(nfe.getNfe().getInfNFe().getIde().getCNF())
                .dhRegistro(nfe.getProtNFe().getInformacaoProtocolo().getDhRecbto())
                .nomeEmitente(nfe.getNfe().getInfNFe().getEmit().getXNome())
                .nomeDestinatario(nfe.getNfe().getInfNFe().getDest().getXNome())
                .valorNotaFiscal(nfe.getNfe().getInfNFe().getTotal().getIcmsTot().getVNF())
                .duplicata(NotaFiscalDuplicata.builder()
                        .id(Long.valueOf(fileNameSeq))
                        .dtVencto(nfe.getNfe().getInfNFe().getCobranca().getDuplicata().getDVenc())
                        .valor(nfe.getNfe().getInfNFe().getCobranca().getDuplicata().getVDup())
                        .numero(nfe.getNfe().getInfNFe().getCobranca().getDuplicata().getNDup())
                        .build())
                .build();



        exchange.getIn().setBody(mapper.writeValueAsString(notaFiscal));
    }



}