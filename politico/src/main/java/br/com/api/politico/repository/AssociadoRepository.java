package br.com.api.politico.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.politico.controller.Cargo;
import br.com.api.politico.modelo.Associado;

import java.util.List;

public interface AssociadoRepository extends JpaRepository <Associado, Long>  {

	Page<Associado> findByCargo(Cargo cargo, Pageable paginacao);
	List<Associado> findByPartidoId(Long id);


}
