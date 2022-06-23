package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Produto {

    private  String cProd;
    private  String cEAN;
    private  String xProd;

    @JsonProperty(value = "NCM")
    private  String ncm;

    @JsonProperty(value = "CFOP")
    private  Integer cfop;

    private  String uCom;
    private  BigDecimal  qCom;
    private  BigDecimal  vUnCom;
    private BigDecimal vProd;
    private  String cEANTrib;
    private  String uTrib;
    private  BigDecimal  qTrib;
    private  BigDecimal  vUnTrib;
    private  Integer indTot;

}
