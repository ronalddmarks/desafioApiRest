package br.com.b2w.test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.b2w.controller.PlanetasController;
import br.com.b2w.model.Planeta;
import br.com.b2w.service.PlanetaService;
import io.restassured.http.ContentType;

@SpringBootTest
class B2WApplicationTests {
	
	@Autowired
	private PlanetasController planetasController;
	
	@MockBean
	private PlanetaService planetaService;
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.planetasController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPlanetaPorCodigo() {
		
		when(this.planetaService.obterPorCodigo("1"))
		.thenReturn(new Planeta("1", "Tatooine", "árido","deserto", 5));
		
		given()
			.accept(ContentType.JSON)
		.when()
		   	.get("/planetas/{codigo}", "1")
		.then()
		.statusCode(org.springframework.http.HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarPlanetaPorCodigo() {
		
		when(this.planetaService.obterPorCodigo("5"))
		.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
		   	.get("/planetas/{codigo}", "5")
		.then()
		.statusCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPlanetaPorNome() {
		
		when(this.planetaService.obterPorNome("Tatooine"))
		.thenReturn(new Planeta("1", "Tatooine", "árido","deserto", 5));
		
		given()
			.accept(ContentType.JSON)
		.when()
		.get("/buscar/por-nome?nome=Tatooine")
		.then()
		.statusCode(org.springframework.http.HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarPlanetaPorNome() {
		
		when(this.planetaService.obterPorNome("Tatooine"))
		.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
		   	.get("/buscar/por-nome?nome=Tatooine")
		.then()
		.statusCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPlanetas() {
		
		
		List<Planeta> planetas = new ArrayList<Planeta>();
		
		planetas.add(new Planeta("1", "Tatooine", "árido","deserto", 5));
		planetas.add(new Planeta("2", "Alderaan", "temperate","grasslands, mountains", 2));
		
		when(this.planetaService.obterTodos())
		.thenReturn(planetas);
		
		given()
			.accept(ContentType.JSON)
		.when()
		   	.get("/planetas")
		.then()
		.statusCode(org.springframework.http.HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandocadastrarPlaneta() {
		
		
		Planeta planeta = new Planeta("1", "Tatooine", "árido","deserto", 5);
	
		
		when(this.planetaService.criar(planeta))
		.thenReturn(planeta);
		
		given()
			.accept(ContentType.JSON)
		.when()
		   	.get("/planetas")
		.then()
		.statusCode(org.springframework.http.HttpStatus.OK.value());
		
	}
	

	
	
}
