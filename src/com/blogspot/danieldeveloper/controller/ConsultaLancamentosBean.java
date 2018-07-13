package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.blogspot.danieldeveloper.model.Lancamento;
import com.blogspot.danieldeveloper.repository.Lancamentos;

@Named("consultaLancamentosBean")
@ViewScoped
public class ConsultaLancamentosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Lancamentos lancamentosRepository;
	
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	
	
}
