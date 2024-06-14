package com.example.Desafio.Pic_Pay.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {
	
	private String nome;
	private String cpf;
	private String email;
	private String senha;
}
