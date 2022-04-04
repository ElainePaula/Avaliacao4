package br.com.api.politico.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.domain.Page;

import br.com.api.politico.controller.Cargo;
import br.com.api.politico.controller.Sexo;
import br.com.api.politico.modelo.Associado;

public class AssociadoDto {
	
	private Long id;
	private String nome;
	private Cargo cargo;
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate data_Nascimento;
	private Sexo sexo;
	
	public AssociadoDto (Associado associado) {
		this.id = associado.getId();
		this.nome = associado.getNome();
		this.cargo = associado.getCargo();
		this.data_Nascimento = associado.getData_Nascimento();
		this.sexo = associado.getSexo();
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static Page<AssociadoDto> converter(Page<Associado> associado) {
		
		return associado.map(AssociadoDto::new);
	}

}
