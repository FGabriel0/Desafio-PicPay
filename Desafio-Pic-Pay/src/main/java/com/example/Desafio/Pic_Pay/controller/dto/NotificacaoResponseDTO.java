package com.example.Desafio.Pic_Pay.controller.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.example.Desafio.Pic_Pay.Entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoResponseDTO {

	private String email;
	private String message;
}
