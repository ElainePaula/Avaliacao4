package br.com.api.politico.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.api.politico.controller.Ideologia;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome_Partido;
	private String sigla;
	@Enumerated(EnumType.STRING)
	private Ideologia ideologia;
	private LocalDate data_Fundacao;
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
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Partido() {
		
	}
	public Partido(String nome_Partido, String sigla, Ideologia ideologia, LocalDate data_Fundacao) {
		this.nome_Partido = nome_Partido;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.data_Fundacao = data_Fundacao;
	}
	
	
}
