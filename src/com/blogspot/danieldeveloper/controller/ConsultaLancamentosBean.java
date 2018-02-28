package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.blogspot.danieldeveloper.model.Lancamento;
import com.blogspot.danieldeveloper.repository.Lancamentos;
import com.blogspot.danieldeveloper.util.JPAUtil;

@Named
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
