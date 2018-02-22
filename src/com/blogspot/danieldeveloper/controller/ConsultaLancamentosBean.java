package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import com.blogspot.danieldeveloper.model.Lancamento;
import com.blogspot.danieldeveloper.repository.Lancamentos;
import com.blogspot.danieldeveloper.util.JPAUtil;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		EntityManager manager = JPAUtil.getEntityManager();
		Lancamentos lancamentoss = new Lancamentos(manager);
		
		this.lancamentos = lancamentoss.todos();
		
		manager.close();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	
	
}
