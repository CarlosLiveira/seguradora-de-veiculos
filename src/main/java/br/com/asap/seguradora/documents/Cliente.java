package br.com.asap.seguradora.documents;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;
	@ApiModelProperty(value = "Nome do cliente não pode ser vazio")
	private String nome;
	@ApiModelProperty(value = "CPF do cliente não pode ser vazio")
	private Long cpf;
	@ApiModelProperty(value = "Cidade do cliente não pode ser vazio")
	private String cidade;
	@ApiModelProperty(value = "UF do cliente não pode ser vazio")
	private String uf;

}
