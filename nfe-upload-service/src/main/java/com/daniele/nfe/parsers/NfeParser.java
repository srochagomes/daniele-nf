package com.daniele.nfe.parsers;

import com.daniele.nfe.domain.NotaFiscal;
import com.daniele.nfe.infrastructure.entity.NfeEntity;
import lombok.Getter;

@Getter
public class NfeParser<DOMAIN extends NotaFiscal, ENTITY extends NfeEntity> {
    private DOMAIN domain;
    private ENTITY entity;

    private NfeParser(DOMAIN pDomain) {
        this.entity = this.transformTo(pDomain);
        this.domain = pDomain;
    }

    private NfeParser(ENTITY pEntity, boolean full) {
        this.entity = pEntity;
        this.domain = this.transformTo(pEntity, full);
    }

    public static NfeEntity parser(NotaFiscal pDomain){
        return new NfeParser(pDomain).getEntity();
    }
    public static NotaFiscal parser(NfeEntity pEntity){
        return new NfeParser(pEntity,true).getDomain();
    }

    public static NotaFiscal parserShort(NfeEntity pEntity){
        return new NfeParser(pEntity,false).getDomain();
    }

    private ENTITY transformTo(DOMAIN parameter) {
        return  (ENTITY)NfeEntity
                .builder()
                .id(parameter.getId())
                .fileNameOriginal(parameter.getFileNameOriginal())
                .dhRegistro(parameter.getDhRegistro())
                .status(parameter.getStatus())
                .nomeDestinatario(parameter.getNomeDestinatario())
                .nomeEmitente(parameter.getNomeEmitente())
                .numero(parameter.getNumero())
                .valorNotaFiscal(parameter.getValorNotaFiscal())
                .build();
    }


    private DOMAIN transformTo(ENTITY parameter, boolean full) {
        return  (DOMAIN) NotaFiscal
                .builder()
                .id(parameter.getId())
                .fileNameOriginal(parameter.getFileNameOriginal())
                .dhRegistro(parameter.getDhRegistro())
                .status(parameter.getStatus())
                .nomeDestinatario(parameter.getNomeDestinatario())
                .nomeEmitente(parameter.getNomeEmitente())
                .numero(parameter.getNumero())
                .valorNotaFiscal(parameter.getValorNotaFiscal())

                .build();

    }


}
