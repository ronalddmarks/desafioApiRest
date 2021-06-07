package br.com.b2w.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.model.Planeta;
import br.com.b2w.model.PlanetsJsonToObject;
import br.com.b2w.service.PlanetaService;
import br.com.b2w.util.JsonUtil;

@RestController
@RequestMapping("/planetas")
public class PlanetasController {

	@Autowired
	private PlanetaService planetaService;

	@GetMapping
	@ResponseBody
	public List<Planeta> obterTodos() {
		return this.planetaService.obterTodos();

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Planeta> obterPorCodigo(@PathVariable String codigo) {

		Planeta planeta = this.planetaService.obterPorCodigo(codigo);

		if (planeta == null) {
			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(planeta);

	}

	@GetMapping("buscar/por-nome")
	public ResponseEntity<Planeta> obterPorNome(@RequestParam("nome") String nome) {

		Planeta planeta = this.planetaService.obterPorNome(nome);

		if (planeta == null) {
			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(planeta);

	}

	@PostMapping
	public ResponseEntity<Planeta> criar(@RequestBody Planeta planeta) throws IOException {

		PlanetsJsonToObject objPlanets = JsonUtil.JsonToObjectPlanets(planeta.getNome());

				
		
		try {
			planeta.setNumeroDeAparicaoEmFilmes(objPlanets.getFilms().length);
		} catch (Exception e) {
			planeta.setNumeroDeAparicaoEmFilmes(0);
		}

		planeta = this.planetaService.criar(planeta);

		if (planeta.getCodigo() == null) {
			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(planeta);

	}

	@GetMapping("remover/por-id")
	public void removerPorId(@RequestParam("codigo") String codigo) {
		this.planetaService.remover(this.planetaService.obterPorCodigo(codigo));
	

	}

	@GetMapping("remover/por-nome")
	public void removerPorNome(@RequestParam("nome") String nome) {

		this.planetaService.remover(this.planetaService.obterPorNome(nome));

	}

}
