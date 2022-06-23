package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Emitente {
    @JsonProperty(value = "CNPJ")
    private BigDecimal cnpj;
    private  String xNome;
    private  String xFant;

    @JsonProperty(value = "enderEmit")
    private  EnderecoEmitente enderecoEmitente;

    @JsonProperty(value = "IE")
    private  BigDecimal  inscrEstadual;

    @JsonProperty(value = "CRT")
    private  Integer crt;
}
