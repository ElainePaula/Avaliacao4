package br.com.api.politico.modelo;

import java.time.LocalDate;

import javax.persistence.*;

import br.com.api.politico.controller.Cargo;
import br.com.api.politico.controller.Sexo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	private LocalDate data_Nascimento;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@ManyToOne
	@JoinColumn(name="partido_id")
	private Partido partido;

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public LocalDate getData_Nascimento() {
		return data_Nascimento;
	}
	public void setData_Nascimento(LocalDate data_Nascimento) {
		this.data_Nascimento = data_Nascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Associado() {
		
	}
	
	public Associado(String nome, Cargo cargo, LocalDate data_Nascimento, Sexo sexo) {
		this.nome = nome;
		this.cargo = cargo;
		this.data_Nascimento = data_Nascimento;
		this.sexo = sexo;
	}

	}
