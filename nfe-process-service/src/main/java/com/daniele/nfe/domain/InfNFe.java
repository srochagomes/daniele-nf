package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfNFe {

    @JsonProperty(value = "ide")
    private  Ide ide;

    @JsonProperty(value = "emit")
    private  Emitente emit;

    @JsonProperty(value = "dest")
    private  Destinatario dest;

    @JsonProperty(value = "det")
    private List<ItensDetalhes> det;


    private  Total total;


    private  Transporte transp;

    @JsonProperty(value = "cobr")
    private  Cobranca cobranca;

    @JsonProperty(value = "infAdic")
    private  InformacaoAdicional informacaoAdicional;

    private  String Id;

    private  String versao;

    private  String text;
}
