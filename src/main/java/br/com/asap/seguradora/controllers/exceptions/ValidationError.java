package br.com.asap.seguradora.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<ErroDeFormularioDto> erros = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public ValidationError() {
	}

	public List<ErroDeFormularioDto> getErros() {
		return erros;
	}

	public void addErro(ErroDeFormularioDto erro) {
		erros.add(erro);
	}
}
