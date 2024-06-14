package com.example.Desafio.Pic_Pay.Entity;
import java.math.BigDecimal;

import com.example.Desafio.Pic_Pay.enums.UserTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@Column(unique = true)
	private String cpf;
	
	@Column(unique = true)
	private String email;
	
	private BigDecimal balanca;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private UserTypes tipo;
}
