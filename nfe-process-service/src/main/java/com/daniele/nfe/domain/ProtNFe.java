package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProtNFe {

    @JsonProperty(value = "infProt")
    private  InformacaoProtocolo informacaoProtocolo;
    private String versao;



}
