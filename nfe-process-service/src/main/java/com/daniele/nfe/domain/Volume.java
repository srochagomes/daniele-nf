package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Volume {

    @JsonProperty(value = "qVol")
    private  Integer quantidadeVolume;
    private  String marca;
    @JsonProperty(value = "nVol")
    private  String numerol;
    @JsonProperty(value = "pesoL")
    private BigDecimal pesoLIquido;

    @JsonProperty(value = "pesoB")
    private  BigDecimal  pesoBruto;

}
