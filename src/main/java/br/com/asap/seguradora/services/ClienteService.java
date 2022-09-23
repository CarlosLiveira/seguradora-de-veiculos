package br.com.asap.seguradora.services;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.asap.seguradora.controllers.dto.ClienteDto;
import br.com.asap.seguradora.controllers.dto.ClienteDtoOutput;

public interface ClienteService {

	public List<ClienteDtoOutput> findAll();

	public ClienteDtoOutput findById(ObjectId id);
	
	public ObjectId create(ClienteDto clienteDto);

	public ClienteDto update(ObjectId id, ClienteDto clienteDto);

	public ClienteDto delete(ObjectId id);
	
}
