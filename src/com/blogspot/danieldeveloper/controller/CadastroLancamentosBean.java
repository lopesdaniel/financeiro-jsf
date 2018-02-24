package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.blogspot.danieldeveloper.model.Lancamento;
import com.blogspot.danieldeveloper.model.Pessoa;
import com.blogspot.danieldeveloper.model.TipoLancamento;
import com.blogspot.danieldeveloper.repository.Lancamentos;
import com.blogspot.danieldeveloper.repository.Pessoas;
import com.blogspot.danieldeveloper.service.CadastroLancamentos;
import com.blogspot.danieldeveloper.service.NegocioException;
import com.blogspot.danieldeveloper.util.JPAUtil;


@ManagedBean
@ViewScoped
public class CadastroLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	
	public void prepararCadastro() {
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			Pessoas pessoas = new Pessoas(manager);
			this.todasPessoas = pessoas.todas();
		} finally {
			manager.close();
		}
	}
	
	public void salvar() {
		EntityManager manager = JPAUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			CadastroLancamentos cadastro = new CadastroLancamentos(new Lancamentos(manager));
			cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso !"));
			
			trx.commit();
		}catch (NegocioException ex) {
			trx.rollback();
			
			FacesMessage message = new FacesMessage(ex.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);	
		}finally {
			manager.close();
		}
	}

	
	public Lancamento getLancamento() {
		return lancamento;
	}
	

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	

	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}
	

	public TipoLancamento[] getTipoLancamentos() {
		return TipoLancamento.values();
	}
	
	
	
	
	
	
	
	
	
}
