package com.challenge.transactionalapi.domain.entity;

import java.io.Serializable;
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
@Table(name = "USUARIO")
public class Usuario implements Serializable{
	
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ID")
	    private Integer id;

	    @Column(name = "IDENTIFICACION", nullable = false, unique = true, length = 15)
	    private String identificacion;

	    @Column(name = "NOMBRES", nullable = false, length = 50)
	    private String nombres;

	    @Column(name = "APELLIDOS", nullable = false, length = 50)
	    private String apellidos;

	    @Column(name = "CLAVE", nullable = false, length = 255)
	    private String clave;

	    @Column(name = "SEXO", nullable = false, length = 1)
	    private String sexo;

	    @Column(name = "FECHANACIMIENTO", nullable = false)
	    private Date fechaNacimiento;

	    @Column(name = "DIRECCION", nullable = false, length = 100)
	    private String direccion;

	    @Column(name = "TELEFONO", nullable = false, length = 12)
	    private String telefono;

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
