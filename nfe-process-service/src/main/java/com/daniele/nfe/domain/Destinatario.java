package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Destinatario {

    @JsonProperty(value = "CNPJ")
    private BigDecimal cnpj;

    private  String xNome;

    @JsonProperty(value = "enderDest")
    private  EnderecoDestinatario enderecoDestinatario;

    @JsonProperty(value = "indIEDest")
    private  Integer indIEDestinatario;

    private  String email;

}
