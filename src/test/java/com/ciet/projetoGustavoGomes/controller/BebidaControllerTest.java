package com.ciet.projetoGustavoGomes.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ciet.projetoGustavoGomes.config.AbstractTestJUnit;
import com.ciet.projetoGustavoGomes.dsl.Dsl;
import com.ciet.projetoGustavoGomes.entity.Bebida;
import com.ciet.projetoGustavoGomes.entity.TipoBebida;
import com.jayway.restassured.http.ContentType;

@WebAppConfiguration
@SpringBootTest
public class BebidaControllerTest extends AbstractTestJUnit{
	
	@Autowired
	private Dsl dsl;
	
	@Test
	public void cadastroCreatedTest() {
		Bebida bebida = dsl.dadoUmaBebidaCadastrada(TipoBebida.CERVEJA, true, "QUALQUER");
		
		Bebida bebidaCadastrada = given()
									.contentType(ContentType.JSON)
									.body(dsl.dadoUmaRequestBody(true))
									.when()
										.post("/bebidas")
									.then()
										.log().all()
										.assertThat()
											.statusCode(HttpStatus.CREATED.value())
											.extract().as(Bebida.class);
		
		Assert.assertEquals(bebidaCadastrada.getTipoBebida(), bebida.getTipoBebida());
	}

	@Test
	public void cadastroNOkTest() {
		given()
		.contentType(ContentType.JSON)
		.body(dsl.dadoUmaRequestBody(false))
		.when()
			.post("/bebidas")
		.then()
			.log().all()
			.assertThat()
				.statusCode(HttpStatus.BAD_REQUEST.value());
		
	}
	
	@Test
	public void buscaBebidasTest() {
		dsl.dadoUmaBebidaCadastrada(TipoBebida.CERVEJA, true, "QUALQUER");
		
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
		dsl.dadoUmaBebidaCadastrada(TipoBebida.CERVEJA, true, "QUALQUER");
		
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
