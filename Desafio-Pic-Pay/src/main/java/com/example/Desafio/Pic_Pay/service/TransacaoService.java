package com.example.Desafio.Pic_Pay.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Desafio.Pic_Pay.Entity.Transferencia;
import com.example.Desafio.Pic_Pay.Entity.Usuario;
import com.example.Desafio.Pic_Pay.controller.form.TransacaoForm;
import com.example.Desafio.Pic_Pay.exception.RegradeNegocioException;
import com.example.Desafio.Pic_Pay.repository.TranferenciaRepository;

@Service
public class TransacaoService {

	@Autowired
	private TranferenciaRepository repository;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NotificationService notificationService;
	
	
	public Transferencia criarTransferencia(TransacaoForm form) {
		
		Usuario emissor = serviceUsuario.buscarPorId(form.getEmissor_id());
		Usuario recepto = serviceUsuario.buscarPorId(form.getReceptor_id());
		
		serviceUsuario.validarTransacao(emissor, form.getValor());
		
		if(!authTransaction(emissor, form.getValor())) {
			throw new RegradeNegocioException("Transalção não autorizada");
		}
		
		Transferencia transferencia = Transferencia.builder()
				.valor(form.getValor())
				.emissor(emissor)
				.receptor(recepto)
				.data_transferencia(LocalDate.now())
				.build();
		
		emissor.setBalanca(emissor.getBalanca().subtract(form.getValor()));
		recepto.setBalanca(recepto.getBalanca().add(form.getValor()));
		
		notificationService.sendNotification(emissor, "Transação realizada com sucesso");
		
		notificationService.sendNotification(recepto, "Transação recebida com sucesso");

		
		return repository.save(transferencia);
			
	}
	
	public boolean authTransaction(Usuario emissor, BigDecimal value) {
        ResponseEntity<Map<String, Object>> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", (Class<Map<String, Object>>) (Class<?>) Map.class);
		
		if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            @SuppressWarnings("unchecked")
            Map<String, Object> responseBody = authorizationResponse.getBody();
            if (responseBody != null) {
                String message = (String) responseBody.get("message");
                return "Autorizado".equalsIgnoreCase(message);
            }
        	}return false;
        }
		
}
