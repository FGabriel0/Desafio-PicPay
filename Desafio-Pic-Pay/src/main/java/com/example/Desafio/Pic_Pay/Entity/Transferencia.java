package com.example.Desafio.Pic_Pay.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tranferencia")
@Table(name = "tranferencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "usuario_id")
	private Usuario usuario_id;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "emissor_id")
	private Usuario emissor;
	
	@ManyToOne
	@JoinColumn(name = "recptor_id")
	private Usuario receptor;
	
	@Column(name ="data_transferencia")
	private LocalDate data_transferencia;
}
