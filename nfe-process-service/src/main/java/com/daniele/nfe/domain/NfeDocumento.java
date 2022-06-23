package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NfeDocumento {

    private ProtNFe protNFe;

    @JsonProperty(value = "NFe")
    private NFe nfe;

    private String versao;

    private  String xmlns;
    private  String text;


}
