package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoEmitente {
    private  String xLgr;
    private  Integer nro;
    private  String xBairro;
    private  Integer cMun;
    private  String xMun;

    @JsonProperty(value = "UF")
    private  String uf;

    @JsonProperty(value = "CEP")
    private  Integer cep;
    private  Integer cPais;
    private  String xPais;
    private  Integer fone;

}
