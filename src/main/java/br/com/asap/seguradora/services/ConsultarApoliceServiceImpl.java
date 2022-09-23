package br.com.asap.seguradora.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.asap.seguradora.controllers.dto.ConsultarApoliceDto;
import br.com.asap.seguradora.controllers.exceptions.EntityNotFoundException;
import br.com.asap.seguradora.documents.Apolice;
import br.com.asap.seguradora.repositories.ApoliceRepository;

@Service
public class ConsultarApoliceServiceImpl implements ConsultarApoliceService {

	@Autowired
	ApoliceRepository apoliceRepository;

	@Override
	public ConsultarApoliceDto findByApolice(Integer numero) {
		Apolice apolice = apoliceRepository.findByNumero(numero)
				.orElseThrow(() -> new EntityNotFoundException("Número de apólice inexistente: " + numero));
		return ConsultarApoliceDto.toConsultaApoliceDto(apolice);
	}
}
