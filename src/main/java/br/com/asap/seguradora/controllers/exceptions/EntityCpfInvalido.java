package br.com.asap.seguradora.controllers.exceptions;

public class EntityCpfInvalido extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityCpfInvalido(String msg) {
		super(msg);
	}

}
