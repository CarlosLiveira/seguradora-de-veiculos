package br.com.asap.seguradora.utils;

import org.bson.types.ObjectId;

import br.com.asap.seguradora.controllers.dto.ClienteDto;
import br.com.asap.seguradora.controllers.exceptions.EntityCpfInvalido;
import br.com.asap.seguradora.controllers.exceptions.EntityDadosInvalidos;

public class ValidaDadosDeEntrada {

	// verifica se é um cpf válido
	public static void validaCpf(ClienteDto form) {
		if (ValidadorDeCPF.isCPF(form.getCpf().toString()) == false) {
			throw new EntityCpfInvalido("CPF inválido: " + form.getCpf());
		}
	}

	// verifica se o formato do codigo do cliente digitado é ObjectId/converte id e
	// retorna como ObjectId
	public static ObjectId validaObjIdCliente(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um código de cliente válido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}

	// verifica se o formato do codigo da apólice digitado é ObjectId/converte id e
	// retorna como ObjectId
	public static ObjectId validaObjIdApolice(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um código de apólice válido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}

	// valida dados numéricos para consulta completa da apólice
	private static boolean isNumeric(String s) {
		return s.chars().allMatch(Character::isDigit);
	}

	public static Integer validaNumeroApolice(String numero) {
		if (isNumeric(numero.toString()) == false)
			throw new EntityDadosInvalidos("Favor informar um número de apólice válido: " + numero);
		return Integer.parseInt(numero);
	}
}
