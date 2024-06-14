package com.example.Desafio.Pic_Pay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Desafio.Pic_Pay.Entity.Usuario;
import com.example.Desafio.Pic_Pay.controller.form.UsuarioForm;
import com.example.Desafio.Pic_Pay.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody UsuarioForm form){
		Usuario user = service.salvarUsuario(form);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodos(){
		List<Usuario> usuario = service.getAll();
		return ResponseEntity.ok().body(usuario);
	}
}
