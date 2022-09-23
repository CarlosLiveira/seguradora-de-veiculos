package br.com.asap.seguradora.controllers.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;

import br.com.asap.seguradora.documents.Apolice;
import br.com.asap.seguradora.documents.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApoliceDtoOutput implements Serializable {
	private static final long serialVersionUID = 1L;

	private ObjectId id;

	private Integer numero;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private String placaDoVeiculo;
	private BigDecimal valor;
	private Cliente cliente;

	public ApoliceDtoOutput(Apolice apolice) {
		id = apolice.getId();
		numero = apolice.getNumero();
		dataInicial = apolice.getDataInicial();
		dataFinal = apolice.getDataFinal();
		placaDoVeiculo = apolice.getPlacaDoVeiculo();
		valor = apolice.getValor();
		cliente = apolice.getCliente();
	}

	public static ApoliceDtoOutput toApoliceDtoOutput(Apolice apolice) {
		return new ApoliceDtoOutput(apolice);
	}

	public Apolice toApolice() {
		return new Apolice(this.id, this.numero, this.dataInicial, this.dataFinal, this.placaDoVeiculo, this.valor,
				this.cliente);
	}

}
