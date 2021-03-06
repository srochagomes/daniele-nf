package com.daniele.nfe.infrastructure.entity;

import com.daniele.nfe.domain.NotaFiscalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "nfe")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class NfeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileNameOriginal;

    private Long numero;

    private LocalDateTime dhRegistro;

    private String nomeEmitente;

    private String nomeDestinatario;

    private BigDecimal valorNotaFiscal;

    @Enumerated(EnumType.STRING)
    private NotaFiscalStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private NfeDuplicataEntity duplicata;


    public String nameFileProcess(){
        return String.format("%010d", this.getId());
    }


}
