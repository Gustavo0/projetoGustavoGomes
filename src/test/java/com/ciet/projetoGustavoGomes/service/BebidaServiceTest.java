package com.ciet.projetoGustavoGomes.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ciet.projetoGustavoGomes.config.AbstractTestJUnit;
import com.ciet.projetoGustavoGomes.dsl.Dsl;
import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.entity.TipoBebida;

@WebAppConfiguration
@SpringBootTest
public class BebidaServiceTest extends AbstractTestJUnit{
	
	@Autowired
	private Dsl dsl;
	
	@Test
	public void cadastrar() {
		Bebida bebida = dsl.dadoUmaBebidaCadastrada(TipoBebida.CERVEJA, true, "QUALQUER");
		Bebida bebidaDoBanco = dsl.quandoBuscarPorTipoBebida(bebida);
		
		Assert.assertEquals(bebidaDoBanco.getTipoBebida(), bebida.getTipoBebida());
		
	}
	
	@Test
	public void listarTodas() {
		dsl.dadoUmaBebidaCadastrada(TipoBebida.SUCO, false, "QUALQUER");
		dsl.dadoUmaBebidaCadastrada(TipoBebida.REFRIGERANTE, false, "QUALQUER");
		
		List<Bebida> bebidasDoBanco = dsl.quandoBuscarTodasAsBebidas();
		Assert.assertEquals(bebidasDoBanco.size(), 2L);
		
	}

}
