package br.com.asap.seguradora.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.asap.seguradora.documents.Apolice;
import br.com.asap.seguradora.repositories.ApoliceRepository;

@Component
public class GeradorDeNumeroDeApolice {
	
	@Autowired
	private  ApoliceRepository apoliceRepository;
	
	private Random aleatorio = new Random();
	private Integer numero;
	
	public Integer gerarNumeroApolice() {
		numero = aleatorio.nextInt();
		// converte número negativo em positivo
		if (numero < 0) {
			numero = numero * -1;
		}

//		 verifica duplicidade de número da apólice, caso sim gera novo
		Optional<Apolice> numeroApolice = apoliceRepository.findByNumero(numero);
		if (numeroApolice.isPresent()) {
			numero = this.gerarNumeroApolice();
		}

		return numero;
	}
}
