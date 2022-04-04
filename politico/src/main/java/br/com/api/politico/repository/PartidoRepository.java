package br.com.api.politico.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.politico.controller.Ideologia;
import br.com.api.politico.modelo.Partido;

public interface PartidoRepository extends JpaRepository <Partido, Long> {

	Page<Partido> findByIdeologia(Ideologia ideologia, Pageable paginacao);

}
