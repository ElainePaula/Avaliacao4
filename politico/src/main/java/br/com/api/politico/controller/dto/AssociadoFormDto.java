package br.com.api.politico.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.api.politico.controller.Cargo;
import br.com.api.politico.controller.Sexo;
import br.com.api.politico.modelo.Associado;
import br.com.api.politico.repository.AssociadoRepository;


public class AssociadoFormDto {
	

		@NotNull
		@NotEmpty
		private String nome;
		@NotNull
		private Cargo cargo;
		@NotNull
		private LocalDate data_Nascimento;
		@NotNull
		private Sexo sexo;
		

				public Associado atualizar(Long id, AssociadoRepository associadoRepository) {

			Associado associado = associadoRepository.getOne(id);

		associado.setNome(this.nome);
		associado.setCargo(this.cargo);
		associado.setData_Nascimento(this.data_Nascimento);
		associado.setSexo(this.sexo);
		
			return associado;
		}
        public AssociadoFormDto() {
        	
        }
        public Associado converter(AssociadoRepository associadoRepository) {
    		
    		return new Associado(nome, cargo, data_Nascimento, sexo);
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
}
