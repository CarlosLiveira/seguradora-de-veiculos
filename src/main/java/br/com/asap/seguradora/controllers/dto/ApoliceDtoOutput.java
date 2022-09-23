package br.com.asap.seguradora.controllers.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private LocalDate dataInicial;
	@NotNull
	private LocalDate dataFinal;
	@NotNull
	@NotBlank
	@NotEmpty
	private String placaDoVeiculo;
	@NotNull
	private BigDecimal valor;
	@NotNull
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
