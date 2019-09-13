package com.ciet.projetoGustavoGomes.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ciet.projetoGustavoGomes.config.AbstractTestJUnit;
import com.ciet.projetoGustavoGomes.dto.BebidaDto;
import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.entity.TipoBebida;
import com.ciet.projetoGustavoGomes.repository.BebidaRepository;
import com.ciet.projetoGustavoGomes.service.BebidaService;

@WebAppConfiguration
@SpringBootTest
public class BebidaServiceTest extends AbstractTestJUnit{
	
	@Autowired
	private BebidaService bebidaService;
	
	@Autowired
	private BebidaRepository bebidaRepository;
	
	@Test
	public void cadastrar() {
		BebidaDto bebida = criaBebida(TipoBebida.CERVEJA, true);
		
		bebidaService.cadastrar(bebida);
		
		Bebida bebidaDoBanco = bebidaRepository.findByTipoBebida(bebida.getTipoBebida());
		Assert.assertEquals(bebidaDoBanco.getTipoBebida(), bebida.getTipoBebida());
		
	}
	
	private BebidaDto criaBebida(TipoBebida tipoBebida, boolean isAlcoolica) {
		BebidaDto bebida = BebidaDto.builder().tipoBebida(tipoBebida)
											  .isAlcoolica(isAlcoolica)
											  .marca("QUALQUER")
											  .build();
		return bebida;
	}
	
	@Test
	public void listarTodas() {
		BebidaDto bebida  = criaBebida(TipoBebida.SUCO, false);
		BebidaDto bebida2 = criaBebida(TipoBebida.REFRIGERANTE, false);
		
		List<BebidaDto> bebidas = new ArrayList<BebidaDto>();
		bebidas.add(bebida);
		bebidas.add(bebida2);
		
		bebidaService.cadastrar(bebida);
		bebidaService.cadastrar(bebida2);
		
		List<Bebida> bebidasDoBanco = bebidaRepository.findAll();
		Assert.assertEquals(bebidasDoBanco.size(), 2L);
		
	}

}
