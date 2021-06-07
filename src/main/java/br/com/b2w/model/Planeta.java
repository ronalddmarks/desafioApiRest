package br.com.b2w.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Planeta {

	@Id
	private String codigo;
	private String nome;
	private String clima;
	private String terreno;
	private int numeroDeAparicaoEmFilmes;



	public Planeta(String codigo, String nome, String clima, String terreno, int numeroDeAparicaoEmFilmes) {
		this.codigo = codigo;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.numeroDeAparicaoEmFilmes = numeroDeAparicaoEmFilmes;
	}
	
	
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getNumeroDeAparicaoEmFilmes() {
		return numeroDeAparicaoEmFilmes;
	}

	public void setNumeroDeAparicaoEmFilmes(int numeroDeAparicaoEmFilmes) {
		this.numeroDeAparicaoEmFilmes = numeroDeAparicaoEmFilmes;
	}

	



	
	
	

}
