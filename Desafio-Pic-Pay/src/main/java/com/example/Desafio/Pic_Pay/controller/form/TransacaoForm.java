package com.example.Desafio.Pic_Pay.controller.form;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoForm {

	private BigDecimal valor;
	private Integer emissor_id;
	private Integer receptor_id;
}
