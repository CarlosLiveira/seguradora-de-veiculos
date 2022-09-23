package br.com.asap.seguradora.controllers.exceptions;

public class EntityCpfDuplicado extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityCpfDuplicado(String msg) {
		super(msg);
	}

}
