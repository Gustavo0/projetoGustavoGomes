package com.ciet.projetoGustavoGomes.controller;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@WebAppConfiguration
@SpringBootTest
public class HelloWorldControllerTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
    private WebApplicationContext wac;
 
    @Before
    public void globalSetup() {
    	RestAssuredMockMvc.webAppContextSetup(wac);
    }
	
	@Test
	public void statusHttpSuccessCorrectMessageTest() {
		
		String response = given()
							.when()
								.get("/")
							.then()
								.log().all()
								.assertThat()
									.statusCode(HttpStatus.OK.value())
									.extract().asString();
		
		Assert.assertTrue(response.equals("Hello World!"));
	}
	
	@Test
	public void statusHttpSuccessWrongMessageTest() {
		
		 String response = given()
						.when()
							.get("/")
						.then()
							.log().all()
							.assertThat()
								.statusCode(HttpStatus.OK.value())
								.extract().asString();
		
		Assert.assertFalse(response.equals("Ola mundo!"));
	}
	
	@Test
	public void statusHttpNotFoundTest() {
		
		 given()
			.when()
				.get("/busca")
			.then()
				.log().all()
				.assertThat()
					.statusCode(HttpStatus.NOT_FOUND.value());
		
	}

}
