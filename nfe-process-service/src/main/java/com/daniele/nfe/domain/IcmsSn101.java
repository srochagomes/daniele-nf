package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IcmsSn101 {
    private  Integer orig;
    @JsonProperty(value = "CSOSN")
    private  Integer csosn;
    private  String pCredSN;
    private BigDecimal vCredICMSSN;

}
