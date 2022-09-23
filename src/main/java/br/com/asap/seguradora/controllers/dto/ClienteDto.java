package br.com.asap.seguradora.controllers.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;

import br.com.asap.seguradora.documents.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private ObjectId id;

	@NotEmpty
	@NotBlank
	@NotNull
	private String nome;
	@NotNull
	private Long cpf;
	@NotEmpty
	@NotBlank
	@NotNull
	private String cidade;
	@NotEmpty
	@NotBlank
	@NotNull
	private String uf;

	public ClienteDto(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		cpf = cliente.getCpf();
		cidade = cliente.getCidade();
		uf = cliente.getUf();
	}

	public static ClienteDto toClienteDto(Cliente cliente) {
		return new ClienteDto(cliente);
	}

	public Cliente toCliente() {
		return new Cliente(this.id, this.nome, this.cpf, this.cidade, this.uf);
	}

}
