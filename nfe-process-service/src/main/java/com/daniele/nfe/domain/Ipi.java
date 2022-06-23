package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ipi {
    private  Integer clEnq;
    @JsonProperty(value = "CNPJProd")
    private BigDecimal cnpjProd;
    private  Integer cEnq;
    @JsonProperty(value = "IPINT")
    private  IpiNt ipiNT;

}
