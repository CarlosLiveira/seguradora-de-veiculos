package br.com.asap.seguradora.services;

import br.com.asap.seguradora.controllers.dto.ConsultarApoliceDto;

public interface ConsultarApoliceService {

	public ConsultarApoliceDto findByApolice(Integer numero);

}
