package br.com.asap.seguradora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.asap.seguradora.controllers.dto.ConsultarApoliceDto;
import br.com.asap.seguradora.services.ConsultarApoliceServiceImpl;
import br.com.asap.seguradora.utils.ValidaDadosDeEntrada;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/consulta_apolice")
@Api(value = "API REST Consulta Apolice")
@CrossOrigin(origins = "*")

public class ConsultarApoliceController {

	@Autowired
	ConsultarApoliceServiceImpl consultarApoliceServiceImpl;

	@Autowired
	ValidaDadosDeEntrada validaDadosDeEntrada;

	@GetMapping(value = "/{numero}")
	@ApiOperation(value = "Consulta uma apólice por número")
	public ResponseEntity<ConsultarApoliceDto> findByApolice(@PathVariable String numero) {
		Integer obj = validaDadosDeEntrada.validaNumeroApolice(numero);
		return ResponseEntity.ok().body(consultarApoliceServiceImpl.findByApolice(obj));
	}

}
