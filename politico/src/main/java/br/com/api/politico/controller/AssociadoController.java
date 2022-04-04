package br.com.api.politico.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.api.politico.controller.dto.VinculaFormDto;
import br.com.api.politico.modelo.Partido;
import br.com.api.politico.repository.PartidoRepository;
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

import br.com.api.politico.controller.dto.AssociadoDto;
import br.com.api.politico.controller.dto.AssociadoFormDto;
import br.com.api.politico.modelo.Associado;
import br.com.api.politico.repository.AssociadoRepository;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

			@Autowired
			private AssociadoRepository associadoRepository;

	        @Autowired
			private PartidoRepository partidoRepository;

			@GetMapping
			public Page<AssociadoDto> lista(@RequestParam(required = false) Cargo cargo,
					@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao) {

				if (cargo == null) {
					Page<Associado> associado = associadoRepository.findAll(paginacao);
					return AssociadoDto.converter(associado);
				} else {
					Page<Associado> associado = associadoRepository.findByCargo(cargo, paginacao);
					return AssociadoDto.converter(associado);
				}
			}

			@PostMapping
			@Transactional
			public ResponseEntity<AssociadoDto> cadastrar(@RequestBody @Valid AssociadoFormDto associadoForm, UriComponentsBuilder uriBuilder) {
				Associado associado = associadoForm.converter(associadoRepository);
				associadoRepository.save(associado);

				URI uri = uriBuilder.path("/associados").buildAndExpand(associado.getId()).toUri();
				return ResponseEntity.created(uri).body(new AssociadoDto(associado));
			}

			@GetMapping("/{id}")
			public ResponseEntity<Associado> detalhar(@PathVariable Long id) {
				Optional<Associado> associado = associadoRepository.findById(id);
				if (associado.isPresent()) {
					return ResponseEntity.ok(associado.get());
				}
				return ResponseEntity.notFound().build();
			}

			@PutMapping("/{id}")
			@Transactional
			public ResponseEntity<AssociadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AssociadoFormDto form) {
				Optional<Associado> optional = associadoRepository.findById(id);
				if (optional.isPresent()) {
					Associado associado = form.atualizar(id, associadoRepository);
					return ResponseEntity.ok(new AssociadoDto(associado));

				}
				return ResponseEntity.notFound().build();

			}

			@DeleteMapping("/{id}")
			@Transactional
			public ResponseEntity<?> remover(@PathVariable Long id) {
				Optional<Associado> optional = associadoRepository.findById(id);
				if (optional.isPresent()) {
					associadoRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}

				return ResponseEntity.notFound().build();
			}

	@PostMapping("/partidos")
	@Transactional
	public ResponseEntity<?> vinculandoAssociadoAUmPartido(@RequestBody @Valid VinculaFormDto form, UriComponentsBuilder uriBuilder){

		Optional<Associado> associado = associadoRepository.findById(form.getIdAssociado());
		Optional<Partido> partido = partidoRepository.findById(form.getIdPartido());

		if(associado.isPresent() && partido.isPresent()) {

			associado.get().setPartido(partido.get());
			associadoRepository.save(associado.get());

			URI uri = uriBuilder.path("/associados/partidos").buildAndExpand(associado.get().getId()).toUri();
			return ResponseEntity.created(uri).body("Associado Inserido com Sucesso ");
		}
		throw new RuntimeException("Associado/Partido nao encontrado");
	}

	@DeleteMapping("/{idAssociado}/partidos/{idPartido}")
	public ResponseEntity<?> deletarAssociadoPartido(@PathVariable Long idAssociado, @PathVariable Long idPartido){
		Associado associado = associadoRepository.getById(idAssociado);
		associado.setPartido(null);
		associadoRepository.save(associado);
		return ResponseEntity.ok().body("Associado desvinculado com sucesso!");
	}
		}

