package com.example.Desafio.Pic_Pay.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Desafio.Pic_Pay.Entity.Usuario;
import com.example.Desafio.Pic_Pay.controller.form.UsuarioForm;
import com.example.Desafio.Pic_Pay.enums.UserTypes;
import com.example.Desafio.Pic_Pay.exception.RegradeNegocioException;
import com.example.Desafio.Pic_Pay.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public Usuario salvarUsuario(UsuarioForm form) {
		
		Usuario usuario = Usuario.builder()
				.nome(form.getNome())
				.cpf(form.getCpf())
				.email(form.getEmail())
				.senha(form.getSenha())
				.build();
		
		return repository.save(usuario);
	}
	
	
	public void validarTransacao(Usuario user, BigDecimal value){
		if(user.getTipo() == UserTypes.LOJISTAS) {
			throw new RegradeNegocioException("Usuario Lojista não pode fazer transação");
		}
		
		if(user.getBalanca().compareTo(value) < 0) {
			throw new RegradeNegocioException("Usuario com saldo insuficiente");

		}
	}
	
	
	public Usuario buscarPorId(Integer id) {
		return repository.findById(id)
				.orElseThrow(()-> new RegradeNegocioException("Usuario não encontrado"));
	}
	
	public List<Usuario> getAll(){
		return repository.findAll();
	}
}
