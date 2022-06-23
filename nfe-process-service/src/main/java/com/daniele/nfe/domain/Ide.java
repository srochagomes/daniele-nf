package com.daniele.nfe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ide {
    private  Integer cUF;
    @JsonProperty(value = "cNF")
    private  Integer cNF;
    private  String natOp;
    private  Integer indPag;
    private  Integer mod;
    private  Integer serie;
    private  Integer nNF;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = FormatData.FORMATA_FULL)
    private LocalDateTime dhEmi;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = FormatData.FORMATA_FULL)
    private LocalDateTime dhSaiEnt;
    private  Integer tpNF;
    private  Integer idDest;
    private  Integer cMunFG;
    private  Integer tpImp;
    private  Integer tpEmis;
    private  Integer cDV;
    private  Integer tpAmb;
    private  Integer finNFe;
    private  Integer indFinal;
    private  Integer indPres;
    private  Integer procEmi;
    private  String verProc;
}
