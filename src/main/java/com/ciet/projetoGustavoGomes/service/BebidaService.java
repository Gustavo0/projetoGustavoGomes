package com.ciet.projetoGustavoGomes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ciet.projetoGustavoGomes.dto.BebidaDto;
import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.repository.BebidaRepository;

@Service
public class BebidaService {
	
	@Autowired
	private BebidaRepository bebidaRepository;
	
	public Page<Bebida> listaTodos(Pageable paginacao) {
		return bebidaRepository.findAll(paginacao);
	}
	
	public Bebida cadastrar(BebidaDto bebida) {
		return bebidaRepository.save(new Bebida(bebida.getTipoBebida(), bebida.isAlcoolica(), bebida.getMarca()));
	}

}
