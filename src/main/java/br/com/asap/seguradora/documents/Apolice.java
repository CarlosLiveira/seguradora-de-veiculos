package br.com.asap.seguradora.documents;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Apolice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;
	private Integer numero;

	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private String placaDoVeiculo;
	private BigDecimal valor;

	@DBRef
	private Cliente cliente;

	public Apolice(Cliente cliente) {
		this.cliente = cliente;
	}

	public Apolice(ObjectId id, Integer numero, LocalDate dataInicial, LocalDate dataFinal, String placaDoVeiculo,
			BigDecimal valor, Cliente cliente) {
		this.id = id;
		this.numero = numero;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.placaDoVeiculo = placaDoVeiculo;
		this.valor = valor;
		this.cliente = cliente;
	}
}
