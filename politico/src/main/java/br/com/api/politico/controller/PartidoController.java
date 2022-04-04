package br.com.api.politico.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.api.politico.controller.dto.AssociadoDto;
import br.com.api.politico.modelo.Associado;
import br.com.api.politico.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.politico.controller.dto.PartidoDto;
import br.com.api.politico.controller.dto.PartidoFormDto;
import br.com.api.politico.modelo.Partido;
import br.com.api.politico.repository.PartidoRepository;


@RestController
@RequestMapping("/partidos")
public class PartidoController {

		@Autowired
		private PartidoRepository partidoRepository;

	   @Autowired
	   private AssociadoRepository associadoRepository;

		@GetMapping
		public Page<PartidoDto> lista(@RequestParam(required = false) Ideologia ideologia,
				@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao) {

			if (ideologia == null) {
				Page<Partido> partido = partidoRepository.findAll(paginacao);
				return PartidoDto.converter(partido);
			} else {
				Page<Partido> partido = partidoRepository.findByIdeologia(ideologia, paginacao);
				return PartidoDto.converter(partido);
			}
		}

		@PostMapping
		@Transactional
		public ResponseEntity<PartidoDto> cadastrar(@RequestBody @Valid PartidoFormDto partidoForm, UriComponentsBuilder uriBuilder) {
			Partido partido = partidoForm.converter(partidoRepository);
			partidoRepository.save(partido);

			URI uri = uriBuilder.path("/partidos").buildAndExpand(partido.getId()).toUri();
			return ResponseEntity.created(uri).body(new PartidoDto(partido));
		}

		@GetMapping("/{id}")
		public ResponseEntity<PartidoDto> detalhar(@PathVariable Long id) {
			Optional<Partido> partido = partidoRepository.findById(id);
			if (partido.isPresent()) {
				return ResponseEntity.ok(new PartidoDto(partido.get()));
			}
			return ResponseEntity.notFound().build();
		}

		@GetMapping("/{id}/associados")
	    public List<AssociadoDto> listaTodosAssociados(@PathVariable Long id){

		List<Associado> associado = associadoRepository.findByPartidoId(id);
		List<AssociadoDto> associadoDto = associado.stream().map(AssociadoDto::new).collect(Collectors.toList());
		return associadoDto;

	}
		@PutMapping("/{id}")
		@Transactional
		public ResponseEntity<PartidoDto> atualizar(@PathVariable Long id, @RequestBody @Valid PartidoFormDto form) {
			Optional<Partido> optional = partidoRepository.findById(id);
			if (optional.isPresent()) {
				Partido partido = form.atualizar(id, partidoRepository);
				return ResponseEntity.ok(new PartidoDto(partido));

			}
			return ResponseEntity.notFound().build();

		}

		@DeleteMapping("/{id}")
		@Transactional
		public ResponseEntity<?> remover(@PathVariable Long id) {
			Optional<Partido> optional = partidoRepository.findById(id);
			if (optional.isPresent()) {
				partidoRepository.deleteById(id);
				return ResponseEntity.ok().build();
			}

			return ResponseEntity.notFound().build();
		}


	}