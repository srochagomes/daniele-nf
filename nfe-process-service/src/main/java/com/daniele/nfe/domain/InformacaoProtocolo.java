package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformacaoProtocolo {
    private  Integer tpAmb;
    private  String verAplic;
    private BigDecimal chNFe;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = FormatData.FORMATA_FULL)
    private LocalDateTime dhRecbto;
    private  BigDecimal  nProt;
    private  String digVal;
    private  Integer cStat;
    private  String xMotivo;

}
