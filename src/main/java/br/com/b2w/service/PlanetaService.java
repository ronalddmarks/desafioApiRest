package br.com.b2w.service;

import java.util.List;

import br.com.b2w.model.Planeta;

public interface PlanetaService {

	public List<Planeta> obterTodos();

	public Planeta obterPorCodigo(String codigo);

	public Planeta obterPorNome(String nome);

	public Planeta criar(Planeta planeta);

	public void remover(Planeta planeta);

	

}
