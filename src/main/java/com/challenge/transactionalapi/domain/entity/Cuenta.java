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
import lombok.Data;

@Data
@Entity
@Table(name = "CUENTA")
public class Cuenta implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NUMEROCUENTA")
    private String numeroCuenta;

    @Column(name = "IDUSUARIO", nullable = false)
    private Integer idUsuario;

    @Column(name = "IDTIPOCUENTA", nullable = false)
    private Integer idTipoCuenta;

    @Column(name = "SALDO", nullable = false)
    private BigDecimal saldo;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado = true;

    @Column(name = "FCREACION", nullable = false)
    private Date fCreacion;

    @Column(name = "FACTUALIZACION")
    private Date fActualizacion;

    @Column(name = "UCREACION", nullable = false, length = 15)
    private String uCreacion;

    @Column(name = "UACTUALIZACION", length = 15)
    private String uActualizacion;
}
