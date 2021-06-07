package br.com.b2w.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.model.Planeta;
import br.com.b2w.repository.PlanetaRepository;
import br.com.b2w.service.PlanetaService;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Override
	public List<Planeta> obterTodos() {
		
		return this.planetaRepository.findAll();
		
	}

	@Override
	public Planeta obterPorCodigo(String codigo) {
    return this.planetaRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Planeta não existe."));
	
	}

	@Override
	public Planeta obterPorNome(String nome) {
		 return this.planetaRepository.findByNome(nome);
		
	}

	@Override
	public Planeta criar(Planeta planeta) {
		
		return this.planetaRepository.save(planeta);
		
	}

	@Override
	public void remover(Planeta planeta) {
		
		this.planetaRepository.delete(planeta);
		// TODO Auto-generated method stub
		
	}

	
}
