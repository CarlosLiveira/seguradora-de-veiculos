package br.com.asap.seguradora.documents;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private String nome;
	private Long cpf;
	private String cidade;
	private String uf;

}
