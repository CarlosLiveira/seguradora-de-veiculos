package br.com.asap.seguradora.utils;

import org.bson.types.ObjectId;

import br.com.asap.seguradora.controllers.dto.ClienteDto;
import br.com.asap.seguradora.controllers.exceptions.EntityCpfInvalido;
import br.com.asap.seguradora.controllers.exceptions.EntityDadosInvalidos;

public class ValidaDadosDeEntrada {

	// verifica se � um cpf v�lido
	public static void validaCpf(ClienteDto form) {
		if (ValidadorDeCPF.isCPF(form.getCpf().toString()) == false) {
			throw new EntityCpfInvalido("CPF inv�lido: " + form.getCpf());
		}
	}

	// verifica se o formato do codigo do cliente digitado � ObjectId/converte id e
	// retorna como ObjectId
	public static ObjectId validaObjIdCliente(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um c�digo de cliente v�lido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}

	// verifica se o formato do codigo da ap�lice digitado � ObjectId/converte id e
	// retorna como ObjectId
	public static ObjectId validaObjIdApolice(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um c�digo de ap�lice v�lido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}

	// valida dados num�ricos para consulta completa da ap�lice
	private static boolean isNumeric(String s) {
		return s.chars().allMatch(Character::isDigit);
	}

	public static Integer validaNumeroApolice(String numero) {
		if (isNumeric(numero.toString()) == false)
			throw new EntityDadosInvalidos("Favor informar um n�mero de ap�lice v�lido: " + numero);
		return Integer.parseInt(numero);
	}
}
