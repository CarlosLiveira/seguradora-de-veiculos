package br.com.asap.seguradora.services;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.asap.seguradora.controllers.dto.ApoliceDto;
import br.com.asap.seguradora.controllers.dto.ApoliceDtoOutput;

public interface ApoliceService {

	public List<ApoliceDtoOutput> findAll();

	public ApoliceDtoOutput findById(ObjectId id);

	public ObjectId create(ApoliceDto apoliceDto);

	public ApoliceDto update(ObjectId id, ApoliceDto apoliceDto);

	public ApoliceDto delete(ObjectId id);

}
