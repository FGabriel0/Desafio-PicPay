package com.example.Desafio.Pic_Pay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Desafio.Pic_Pay.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> existByEmail(String email);
	
	Optional<Usuario> existByCpf(String cpf);
}
