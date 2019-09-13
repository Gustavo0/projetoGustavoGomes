package com.ciet.projetoGustavoGomes.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ciet.projetoGustavoGomes.config.AbstractTestJUnit;
import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.entity.TipoBebida;
import com.ciet.projetoGustavoGomes.repository.BebidaRepository;
import com.jayway.restassured.http.ContentType;

@WebAppConfiguration
@SpringBootTest
public class BebidaControllerTest extends AbstractTestJUnit{
	
	@Autowired
	private BebidaRepository bebidaRepository;
	
	@Test
	public void cadastroCreatedTest() {
		String requestBody = "{\"tipoBebida\": \"CERVEJA\", \"isAlcoolica\": true, \"marca\": \"QUALQUER\"}";
		
		Bebida bebida = criaBebida(TipoBebida.CERVEJA, true);
		bebidaRepository.save(bebida);
		
		Bebida bebidaCadastrada = given()
									.contentType(ContentType.JSON)
									.body(requestBody)
									.when()
										.post("/bebidas")
									.then()
										.log().all()
										.assertThat()
											.statusCode(HttpStatus.CREATED.value())
											.extract().as(Bebida.class);
		
		Assert.assertEquals(bebidaCadastrada.getTipoBebida(), bebida.getTipoBebida());
	}

	private Bebida criaBebida(TipoBebida tipoBebida, boolean isAlcoolica) {
		Bebida bebida = Bebida.builder().tipoBebida(tipoBebida).isAlcoolica(isAlcoolica).build();
		return bebida;
	}
	
	@Test
	public void cadastroNOkTest() {
		String requestBody = "{\"tipoBebida\": \"CERVEJA\", \"isAlcoolica\": true, \"marca\": \"AAAAAAAAAAA\"}";
		
		Bebida bebida = criaBebida(TipoBebida.CERVEJA, true);
		bebidaRepository.save(bebida);
		
		given()
		.contentType(ContentType.JSON)
		.body(requestBody)
		.when()
			.post("/bebidas")
		.then()
			.log().all()
			.assertThat()
				.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	
	@Test
	public void buscaBebidasTest() {
		Bebida bebida = criaBebida(TipoBebida.CERVEJA, true);
		
		List<Bebida> bebidas = new ArrayList<Bebida>();
		bebidas.add(bebida);
		bebidaRepository.save(bebida);
		
		given()
		.when()
			.get("/bebidas")
		.then()
			.log().all()
			.assertThat()
				.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void buscaBebidaPorNomeTest() {
		Bebida bebida = criaBebida(TipoBebida.CERVEJA, true);
		bebidaRepository.save(bebida);
		
		given()
			.queryParam("tipoBebida", "CERVEJA")
		.when()
			.get("/bebidas")
		.then()
			.log().all()
			.assertThat()
				.statusCode(HttpStatus.OK.value());
	}
	
}
