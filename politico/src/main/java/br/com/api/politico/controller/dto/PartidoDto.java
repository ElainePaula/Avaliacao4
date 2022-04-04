package br.com.api.politico.controller.dto;

import java.time.LocalDate;

import br.com.api.politico.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.domain.Page;

import br.com.api.politico.controller.Ideologia;
import br.com.api.politico.modelo.Partido;

public class PartidoDto {
	
	
    private Long id;
	private String nome_Partido;
	private String sigla;
	private Ideologia ideologia;
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate data_Fundacao;
	
	public PartidoDto(Partido partido) {
		this.id = partido.getId();
		this.nome_Partido = partido.getNome_Partido();
		this.sigla = partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.data_Fundacao = partido.getData_Fundacao(); 
		}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Ideologia getIdeologia() {
		return ideologia;
	}


	public void setIdeologia(Ideologia ideologia) {
		this.ideologia = ideologia;
	}


	public LocalDate getData_Fundacao() {
		return data_Fundacao;
	}


	public void setData_Fundacao(LocalDate data_Fundacao) {
		this.data_Fundacao = data_Fundacao;
	}


	public static Page<PartidoDto> converter(Page<Partido> partido) {
		
		return partido.map(PartidoDto::new);
	}
}
