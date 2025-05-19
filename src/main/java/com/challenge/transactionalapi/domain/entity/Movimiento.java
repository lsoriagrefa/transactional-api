package com.challenge.transactionalapi.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "MOVIMIENTO")
public class Movimiento implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "IDCUENTA", nullable = false)
    private Integer idCuenta;

    @Column(name = "IDTIPOMOVIMIENTO", nullable = false)
    private Integer idTipoMovimiento;

    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;

    @Column(name = "SALDO", nullable = false)
    private BigDecimal saldo;

    @Column(name = "FCREACION", nullable = false)
    private Date fCreacion;

    @Column(name = "FACTUALIZACION")
    private Date fActualizacion;

    @Column(name = "UCREACION", nullable = false, length = 15)
    private String uCreacion;

    @Column(name = "UACTUALIZACION", length = 15)
    private String uActualizacion;
}
