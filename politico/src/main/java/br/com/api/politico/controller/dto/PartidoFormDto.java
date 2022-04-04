package br.com.api.politico.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.api.politico.controller.Ideologia;
import br.com.api.politico.modelo.Partido;
import br.com.api.politico.repository.PartidoRepository;

public class PartidoFormDto {
	
	@NotNull
	@NotEmpty
	private String nome_Partido;
	@NotNull
	private String sigla;
	@NotNull
	private LocalDate data_Fundacao;
	@NotNull
	private Ideologia ideologia;
	

			public Partido atualizar(Long id, PartidoRepository partidoRepository) {

		Partido partido = partidoRepository.getOne(id);
		
		partido.setNome_Partido(this.nome_Partido);
		partido.setSigla(this.sigla);
		partido.setData_Fundacao(this.data_Fundacao);
		partido.setIdeologia(this.ideologia);
	
		return partido;
	}
    public PartidoFormDto() {
    	
    }
    public Partido converter(PartidoRepository partidoRepository) {
    	return new Partido(nome_Partido, sigla, ideologia, data_Fundacao); 
    	}
	public PartidoFormDto(String nome_Partido,String sigla,
			LocalDate data_Fundacao,Ideologia ideologia) {
		this.nome_Partido = nome_Partido;
		this.sigla = sigla;
		this.data_Fundacao = data_Fundacao;
		this.ideologia = ideologia;
	}

	public String getNome_Partido() {
		return nome_Partido;
	}

	public void setNome_Partido(String nome_Partido) {
		this.nome_Partido = nome_Partido;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public LocalDate getData_Fundacao() {
		return data_Fundacao;
	}

	public void setData_Fundacao(LocalDate data_Fundacao) {
		this.data_Fundacao = data_Fundacao;
	}

	public Ideologia getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(Ideologia ideologia) {
		this.ideologia = ideologia;
	}
}
