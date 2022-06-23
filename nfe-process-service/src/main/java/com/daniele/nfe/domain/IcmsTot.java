package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IcmsTot {
    private  BigDecimal  vBC;
    private  BigDecimal  vICMS;
    private  BigDecimal  vICMSDeson;
    private  BigDecimal  vBCST;
    private  BigDecimal  vST;
    private  BigDecimal  vProd;
    private  BigDecimal  vFrete;
    private  BigDecimal  vSeg;
    private  BigDecimal  vDesc;
    private  BigDecimal  vII;
    private  BigDecimal  vIPI;
    private  BigDecimal  vPIS;
    private  BigDecimal  vCOFINS;
    private  BigDecimal  vOutro;
    @JsonProperty(value = "vNF")
    private  BigDecimal  vNF;
    private  BigDecimal  vTotTrib;
}
