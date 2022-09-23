package br.com.asap.seguradora.controllers.exceptions;

public class EntityDadosInvalidos extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityDadosInvalidos(String msg) {
		super(msg);
	}

}
