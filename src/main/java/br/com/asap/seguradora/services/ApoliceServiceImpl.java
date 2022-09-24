package br.com.asap.seguradora.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.asap.seguradora.controllers.dto.ApoliceDto;
import br.com.asap.seguradora.controllers.dto.ApoliceDtoOutput;
import br.com.asap.seguradora.controllers.exceptions.EntityNotFoundException;
import br.com.asap.seguradora.documents.Apolice;
import br.com.asap.seguradora.documents.Cliente;
import br.com.asap.seguradora.repositories.ApoliceRepository;
import br.com.asap.seguradora.utils.GeradorDeNumeroDeApolice;

@Service
public class ApoliceServiceImpl implements ApoliceService {

	@Autowired
	GeradorDeNumeroDeApolice geradorDeNumeroDeApolice;

	@Autowired
	ApoliceRepository apoliceRepository;

	@Autowired
	ClienteServiceImpl clienteServiceImpl;

	@Override
	public List<ApoliceDtoOutput> findAll() {
		List<Apolice> apolices = apoliceRepository.findAll();
		List<ApoliceDtoOutput> apolicesDto = apolices.stream().map(obj -> ApoliceDtoOutput.toApoliceDtoOutput(obj))
				.collect(Collectors.toList());
		return apolicesDto;
	}

	@Override
	public ApoliceDtoOutput findById(ObjectId id) {
		Apolice apolice = apoliceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Código de apólice inexistente: " + id));
		return ApoliceDtoOutput.toApoliceDtoOutput(apolice);
	}

	@Override
	public ObjectId create(ApoliceDto form) {
		Cliente cliente = clienteServiceImpl.findById(form.getCliente().getId()).toCliente();
		Apolice apolice = form.toApolice();
		apolice.setNumero(geradorDeNumeroDeApolice.gerarNumeroApolice());
		apolice.setCliente(cliente);
		apoliceRepository.save(apolice);
		return apolice.getId();
	}

	@Override
	public ApoliceDto update(ObjectId id, ApoliceDto form) {
		Apolice apolice = apoliceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Código da apólice inexistente: " + id));
		apolice.setDataInicial(form.getDataInicial());
		apolice.setDataFinal(form.getDataFinal());
		apolice.setPlacaDoVeiculo(form.getPlacaDoVeiculo());
		apolice.setValor(form.getValor());
		apoliceRepository.save(apolice);
		return ApoliceDto.toApoliceDto(apolice);
	}

	@Override
	public ApoliceDto delete(ObjectId id) {
		Apolice apolice = apoliceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Código da apólice inexistente: " + id));
		apoliceRepository.delete(apolice);
		return ApoliceDto.toApoliceDto(apolice);
	}

}
