package br.com.asap.seguradora.controllers.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.asap.seguradora.documents.Apolice;
import br.com.asap.seguradora.documents.Cliente;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApoliceDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private Cliente cliente;

	@NotNull
	@ApiModelProperty(value = "Data inicial da apólice não pode ser vazio")
	private LocalDate dataInicial;

	@NotNull
	@ApiModelProperty(value = "Data final da apólice não pode ser vazio")
	private LocalDate dataFinal;

	@NotNull
	@NotBlank
	@NotEmpty
	@ApiModelProperty(value = "A placa do veículo não pode ser vazio")
	private String placaDoVeiculo;

	@NotNull
	@ApiModelProperty(value = "O valor não pode ser vazio")
	private BigDecimal valor;

	public ApoliceDto(Apolice apolice) {
		cliente = apolice.getCliente();
		dataInicial = apolice.getDataInicial();
		dataFinal = apolice.getDataFinal();
		placaDoVeiculo = apolice.getPlacaDoVeiculo();
		valor = apolice.getValor();
	}

	public static ApoliceDto toApoliceDto(Apolice apolice) {
		return new ApoliceDto(apolice);
	}

	public Apolice toApolice() {
		return new Apolice(this.dataInicial, this.dataFinal, this.placaDoVeiculo, this.valor, this.cliente);
	}

}
