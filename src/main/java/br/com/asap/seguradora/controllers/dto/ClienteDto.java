package br.com.asap.seguradora.controllers.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.asap.seguradora.documents.Cliente;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@NotBlank
	@NotNull
	@ApiModelProperty(value = "Nome do cliente não pode ser vazio")
	private String nome;

	@NotNull
	@ApiModelProperty(value = "CPF do cliente não pode ser vazio")
	private Long cpf;

	@NotEmpty
	@NotBlank
	@NotNull
	@ApiModelProperty(value = "Cidade do cliente não pode ser vazio")
	private String cidade;

	@NotEmpty
	@NotBlank
	@NotNull
	@ApiModelProperty(value = "UF do cliente não pode ser vazio")
	private String uf;

	public ClienteDto(Cliente cliente) {
		nome = cliente.getNome();
		cpf = cliente.getCpf();
		cidade = cliente.getCidade();
		uf = cliente.getUf();
	}

	public static ClienteDto toClienteDto(Cliente cliente) {
		return new ClienteDto(cliente);
	}

	public Cliente toCliente() {
		return new Cliente(null, this.nome, this.cpf, this.cidade, this.uf);
	}

}
