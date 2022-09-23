package br.com.asap.seguradora.utils;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import br.com.asap.seguradora.controllers.exceptions.EntityDadosInvalidos;

@Component
public class ValidaObjId {

	public ObjectId validaObjIdCliente(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um c�digo de cliente v�lido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}
	
	public ObjectId validaObjIdApolice(String id) {
		if (ObjectId.isValid(id) == false)
			throw new EntityDadosInvalidos("Favor informar um c�digo de ap�lice v�lido: " + id);
		ObjectId objId = new ObjectId(id);
		return objId;
	}
}
