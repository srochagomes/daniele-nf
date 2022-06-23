package com.daniele.nfe.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "nfe_dupl")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class NfeDuplicataEntity {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @JoinColumn(name = "id")
    @OneToOne(mappedBy = "duplicata", cascade = CascadeType.ALL)
    private NfeEntity nfe;

    private Integer numero;

    private LocalDate dtVencto;

    private BigDecimal valor;


}
