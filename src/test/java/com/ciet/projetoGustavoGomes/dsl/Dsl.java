package com.ciet.projetoGustavoGomes.dsl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ciet.projetoGustavoGomes.dto.BebidaDto;
import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.entity.TipoBebida;
import com.ciet.projetoGustavoGomes.repository.BebidaRepository;
import com.ciet.projetoGustavoGomes.service.BebidaService;

@Component
public class Dsl {
	
	@Autowired
	private BebidaService bebidaService;
	
	@Autowired
	private BebidaRepository bebidaRepository;
	
	public String dadoUmaRequestBody(boolean isValida) {
		if(isValida) {
			return "{\"tipoBebida\": \"CERVEJA\", \"isAlcoolica\": true, \"marca\": \"QUALQUER\"}";
		}else {
			return "{\"tipoBebida\": \"CERVEJA\", \"isAlcoolica\": true, \"marca\": \"AAAAAAAAAAA\"}";
		}
		
	}
	
	public Bebida dadoUmaBebidaCadastrada(TipoBebida tipoBebida, boolean isAlcoolica, String marca) {
		BebidaDto bebida = BebidaDto.builder().tipoBebida(tipoBebida)
						  .isAlcoolica(isAlcoolica)
						  .marca(marca)
						  .build();
		return bebidaService.cadastrar(bebida);
	}
	
	public Bebida quandoBuscarPorTipoBebida(Bebida bebida) {
		return bebidaRepository.findByTipoBebida(bebida.getTipoBebida());
	}
	
	public List<Bebida> quandoBuscarTodasAsBebidas() {
		return bebidaRepository.findAll();
	}

}
