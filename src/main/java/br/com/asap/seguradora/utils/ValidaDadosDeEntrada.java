package br.com.asap.seguradora.utils;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.asap.seguradora.controllers.dto.ClienteDto;
import br.com.asap.seguradora.controllers.exceptions.EntityCpfDuplicado;
import br.com.asap.seguradora.controllers.exceptions.EntityCpfInvalido;
import br.com.asap.seguradora.controllers.exceptions.EntityDadosInvalidos;
import br.com.asap.seguradora.documents.Cliente;
import br.com.asap.seguradora.repositories.ClienteRepository;

@Component
public class ValidaDadosDeEntrada {

	@Autowired
	private ClienteRepository clienteRepository;

	// verifica se é um cpf válido
	public void validaCpf(ClienteDto form) {
		if (ValidadorDeCPF.isCPF(form.getCpf().toString()) == false) {
			throw new EntityCpfInvalido("CPF inválido: " + form.getCpf());
		}
	}

	// verifica duplicidade de cpf
	public void validaDuplicidadeDeCpf(ClienteDto form) {
		Optional<Cliente> cpf = clienteRepository.findByCpf(form.getCpf());
		if (cpf.isPresent()) {
			throw new EntityCpfDuplicado("CPF já possui cadastro: " + form.getCpf());
		}
	}

	//verifica se o formato do codigo do cliente digitado é ObjectId/converte id e retorna como ObjectId
	public ObjectId validaObjIdCliente(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um código de cliente válido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}

	//verifica se o formato do codigo da apólice digitado é ObjectId/converte id e retorna como ObjectId
	public ObjectId validaObjIdApolice(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um código de apólice válido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}

	//valida dados numéricos para consulta completa da apólice
	public static boolean isNumeric(String s) {
		return s.chars().allMatch(Character::isDigit);
	}
	public Integer validaNumeroApolice(String numero) {
		if (isNumeric(numero.toString()) == false)
			throw new EntityDadosInvalidos("Favor informar um número de apólice válido: " + numero);
		return Integer.parseInt(numero);
	}
}
