package com.projects.dadosldr.entity;

public class Escola {

	private int id;
	private String lista;
	private String nome;
	private int inep;
	private String uf;
	private String municipio;
	private String endereco;
	private String telefone;
	private String porte;
	private String emeo;
	private String cnpj;
	private String email;
	private String telefone2;
	private String telefone3;
	private String telefone4;
	private String site;
	private String instagram;
	private String sistemaDeEnsino;
	private String agendaDigital;
	private String anotacoes;
	
	public Escola() {
		
	}

	
	public Escola(int id, String lista, String nome, int inep, String uf, String municipio, String endereco,
			String telefone, String porte, String emeo, String cnpj, String email, String telefone2, String telefone3,
			String telefone4, String site, String instagram, String sistemaDeEnsino, String agendaDigital,
			String anotacoes) {
		super();
		this.id = id;
		this.lista = lista;
		this.nome = nome;
		this.inep = inep;
		this.uf = uf;
		this.municipio = municipio;
		this.endereco = endereco;
		this.telefone = telefone;
		this.porte = porte;
		this.emeo = emeo;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.telefone4 = telefone4;
		this.site = site;
		this.instagram = instagram;
		this.sistemaDeEnsino = sistemaDeEnsino;
		this.agendaDigital = agendaDigital;
		this.anotacoes = anotacoes;
	}



	public Escola(String lista, String nome, int inep, String uf,
			String municipio, String endereco, String telefone,
			String porte, String emeo, String cnpj, String email,
			String telefone2, String telefone3, String telefone4,
			String site, String instagram, String sistemaDeEnsino,
			String agendaDigital, String anotacoes) {
		
		this.lista = lista;
		this.nome = nome;
		this.inep = inep;
		this.uf = uf;
		this.municipio = municipio;
		this.endereco = endereco;
		this.telefone = telefone;
		this.porte = porte;
		this.emeo = emeo;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.telefone4 = telefone4;
		this.site = site;
		this.instagram = instagram;
		this.sistemaDeEnsino = sistemaDeEnsino;
		this.agendaDigital = agendaDigital;
		this.anotacoes = anotacoes;
		
	}
	

	public Escola(String lista, String nome, int inep, String uf, String municipio, String endereco,
			String telefone, String porte, String emeo) {
		
		this.lista = lista;
		this.nome = nome;
		this.inep = inep;
		this.uf = uf;
		this.municipio = municipio;
		this.endereco = endereco;
		this.telefone = telefone;
		this.porte = porte;
		this.emeo = emeo;
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLista() {
		return lista;
	}

	public void setLista(String lista) {
		this.lista = lista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getInep() {
		return inep;
	}

	public void setInep(int inep) {
		this.inep = inep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getEmeo() {
		return emeo;
	}

	public void setEmeo(String emeo) {
		this.emeo = emeo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getTelefone4() {
		return telefone4;
	}

	public void setTelefone4(String telefone4) {
		this.telefone4 = telefone4;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getSistemaDeEnsino() {
		return sistemaDeEnsino;
	}

	public void setSistemaDeEnsino(String sistemaDeEnsino) {
		this.sistemaDeEnsino = sistemaDeEnsino;
	}

	public String getAgendaDigital() {
		return agendaDigital;
	}

	public void setAgendaDigital(String agendaDigital) {
		this.agendaDigital = agendaDigital;
	}

	public String getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	@Override
	public String toString() {
		return "Escola [id=" + id + ", lista=" + lista + ", nome=" + nome + ", inep=" + inep + ", uf=" + uf + ", municipio="
				+ municipio + ", endereco=" + endereco + ", telefone=" + telefone + ", porte=" + porte + ", emeo="
				+ emeo + ", cnpj=" + cnpj + ", email=" + email + ", telefone2=" + telefone2 + ", telefone3=" + telefone3
				+ ", telefone4=" + telefone4 + ", site=" + site + ", instagram=" + instagram + ", sistemaDeEnsino="
				+ sistemaDeEnsino + ", agendaDigital=" + agendaDigital + ", anotacoes=" + anotacoes + "]";
	}

	
	
}
