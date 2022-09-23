package br.com.asap.seguradora.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.asap.seguradora.controllers.dto.ClienteDto;
import br.com.asap.seguradora.controllers.dto.ClienteDtoOutput;
import br.com.asap.seguradora.controllers.exceptions.EntityNotFoundException;
import br.com.asap.seguradora.documents.Apolice;
import br.com.asap.seguradora.documents.Cliente;
import br.com.asap.seguradora.repositories.ApoliceRepository;
import br.com.asap.seguradora.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ApoliceRepository apoliceRepository;

	@Autowired
	private ValidaCPF validaCPF;

	@Override
	public List<ClienteDtoOutput> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDtoOutput> clientesDto = clientes.stream().map(obj -> ClienteDtoOutput.toClienteDtoOutput(obj))
				.collect(Collectors.toList());
		return clientesDto;
	}

	@Override
	public ClienteDtoOutput findById(ObjectId id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("C�digo do cliente inexistente: " + id));
		return ClienteDtoOutput.toClienteDtoOutput(cliente);
	}

	@Override
	public ObjectId create(ClienteDto form) {
		// valida se o n�mero do cpf � v�lido
		validaCPF.validaCpf(form);
		// valida se o cpf j� consta no banco de dados
		validaCPF.validaDuplicidadeDeCpf(form);

		Cliente cliente = form.toCliente();
//		clienteRepository.findByCpf(cliente.getCpf());
		clienteRepository.save(cliente);
		return cliente.getId();
	}

	@Override
	public ClienteDto update(ObjectId id, ClienteDto form) {

		validaCPF.validaCpf(form);

		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("C�digo do cliente inexistente: " + id));
		cliente.setNome(form.getNome());
		cliente.setCpf(form.getCpf());
		cliente.setCidade(form.getCidade());
		cliente.setUf(form.getUf());
		clienteRepository.save(cliente);
		return ClienteDto.toClienteDto(cliente);
	}

	@Override
	public ClienteDto delete(ObjectId id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("C�digo do cliente inexistente: " + id));
		// exclui todas as ap�lices relacionadas ao cliente deletado
		List<Apolice> listApolices = apoliceRepository.findByCliente(id);
		apoliceRepository.deleteAll(listApolices);
		clienteRepository.deleteById(id);
		return ClienteDto.toClienteDto(cliente);
	}

}
