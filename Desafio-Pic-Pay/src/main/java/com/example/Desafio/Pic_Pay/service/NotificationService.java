package com.example.Desafio.Pic_Pay.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Desafio.Pic_Pay.Entity.Usuario;
import com.example.Desafio.Pic_Pay.controller.dto.NotificacaoResponseDTO;
import com.example.Desafio.Pic_Pay.exception.RegradeNegocioException;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(Usuario usuario,String message) {
		String email = usuario.getEmail();
		NotificacaoResponseDTO dto = new NotificacaoResponseDTO(email,message);
		
		ResponseEntity<String> response	=restTemplate.postForEntity("https://util.devi.tools/api/v1/notify",dto,String.class);
		
		if(!(response.getStatusCode() == HttpStatus.OK)) {
			System.out.println("erro ao enviar a notificação ");
			throw new RegradeNegocioException("Serviço de notificação fora do ar");
		}
	}
}
