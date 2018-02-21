package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.blogspot.danieldeveloper.model.Lancamento;
import com.blogspot.danieldeveloper.util.JPAUtil;

public class ConsultaLancamentosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos;

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void consultar() {
		EntityManager manager = JPAUtil.getEntityManager();
		TypedQuery<Lancamento> query = manager.createQuery("form Lancamento", Lancamento.class);
		this.lancamentos = query.getResultList();
		
		manager.close();
	}
	
}
