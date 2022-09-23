package br.com.asap.seguradora.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDeFormularioDto {

	private String campo;
	private String erro;
}
