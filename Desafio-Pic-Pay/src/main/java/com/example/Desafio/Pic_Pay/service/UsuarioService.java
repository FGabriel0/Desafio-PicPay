package com.example.Desafio.Pic_Pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Desafio.Pic_Pay.Entity.Usuario;
import com.example.Desafio.Pic_Pay.controller.form.UsuarioForm;
import com.example.Desafio.Pic_Pay.exception.RegradeNegocioException;
import com.example.Desafio.Pic_Pay.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	public Usuario salvarUsuario(UsuarioForm form) {
		String emailExist = form.getEmail();
		String cpfExist = form.getCpf();
		
		Usuario usuariosEmail = repository.existByEmail(emailExist)
				.orElseThrow(() -> new RegradeNegocioException(("Email já existe")));
		
		Usuario usuarioCpf = repository.existByCpf(cpfExist)
				.orElseThrow(() -> new RegradeNegocioException("Cpf já existe"));
		
		Usuario usuario = Usuario.builder()
				.nome(form.getNome())
				.cpf(form.getCpf())
				.email(form.getEmail())
				.senha(form.getSenha())
				.build();
		
		return repository.save(usuario);
	}
}
