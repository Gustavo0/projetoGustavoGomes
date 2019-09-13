package com.ciet.projetoGustavoGomes.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ciet.projetoGustavoGomes.dto.BebidaDto;
import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.service.BebidaService;

@RestController
@RequestMapping("/bebidas")
public class BebidaController {

	@Autowired
	private BebidaService bebidaService;
	
	@GetMapping
	@Cacheable(value = "listaDeBebidas")
	public Page<Bebida> listarTodos(@RequestParam(required= false) String tipoBebida,
			@PageableDefault(sort = "id",direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		Page<Bebida> bebidas = bebidaService.listaTodos(paginacao);

		return bebidas;
	}

	@PostMapping
	@CacheEvict(value = "listaDeBebidas", allEntries = true)
	public ResponseEntity<BebidaDto> incluir(@RequestBody @Valid BebidaDto bebida, UriComponentsBuilder uriBuilder) {
		Bebida bebidaCadastrada = bebidaService.cadastrar(bebida);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(bebidaCadastrada.getId()).toUri();
		return ResponseEntity.created(uri).body(new BebidaDto(bebidaCadastrada));
	}
}
