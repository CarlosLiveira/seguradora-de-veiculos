package br.com.asap.seguradora.controllers.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;

import br.com.asap.seguradora.documents.Apolice;
import br.com.asap.seguradora.documents.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultarApoliceDto implements Serializable {
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

	private String vencimento;
	private String prazo;

	public ConsultarApoliceDto(Apolice apolice, ConsultarApoliceDto consultarApoliceDto) {
		id = apolice.getId();
		numero = apolice.getNumero();
		dataInicial = apolice.getDataInicial();
		dataFinal = apolice.getDataFinal();
		placaDoVeiculo = apolice.getPlacaDoVeiculo();
		valor = apolice.getValor();
		cliente = apolice.getCliente();
		vencimento = consultarApoliceDto.getVencimento();
		prazo = consultarApoliceDto.getPrazo();
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
		this.calculaVencimento();
	}

	public Apolice toApolice() {
		return new Apolice(this.id, this.numero, this.dataInicial, this.dataFinal, this.placaDoVeiculo, this.valor,
				this.cliente);
	}

	public static ConsultarApoliceDto toConsultaApoliceDto(Apolice apolice) {
		ConsultarApoliceDto consultarApoliceDto = new ConsultarApoliceDto();
		BeanUtils.copyProperties(apolice, consultarApoliceDto);

		return consultarApoliceDto;
	}

	private void calculaVencimento() {
		LocalDate dataAtual = LocalDate.now();

		long dateDiff = ChronoUnit.DAYS.between(dataAtual, this.dataFinal);

		if (dateDiff > 0) {
			this.setVencimento("válida");
			this.setPrazo("Faltam " + dateDiff + " dias para o vencimento");
		} else {
			this.setVencimento("vencida");
			this.setPrazo("Vencida a " + (dateDiff * -1) + " dias");
		}
	}

}
