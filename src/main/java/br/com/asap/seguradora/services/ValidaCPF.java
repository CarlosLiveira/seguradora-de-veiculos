package br.com.asap.seguradora.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.asap.seguradora.controllers.dto.ClienteDto;
import br.com.asap.seguradora.controllers.exceptions.EntityCpfDuplicado;
import br.com.asap.seguradora.controllers.exceptions.EntityCpfInvalido;
import br.com.asap.seguradora.documents.Cliente;
import br.com.asap.seguradora.repositories.ClienteRepository;
import br.com.asap.seguradora.utils.ValidadorDeCPF;

@Component
public class ValidaCPF {

	@Autowired
	private ClienteRepository clienteRepository;

	public void validaCpf(ClienteDto form) {
		// verifica se � um cpf v�lido
		if (ValidadorDeCPF.isCPF(form.getCpf().toString()) == false) {
			throw new EntityCpfInvalido("CPF inv�lido: " + form.getCpf());
		}
	}

	public void validaDuplicidadeDeCpf(ClienteDto form) {
		// verifica duplicidade de cpf
		Optional<Cliente> cpf = clienteRepository.findByCpf(form.getCpf());
		if (cpf.isPresent()) {
			throw new EntityCpfDuplicado("CPF j� possui cadastro: " + form.getCpf());
		}
	}

}
