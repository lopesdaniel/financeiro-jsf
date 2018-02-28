package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
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


@Named
@ViewScoped
public class CadastroLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	
	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {			
			this.cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso !"));
		}catch (NegocioException ex) {			
			FacesMessage message = new FacesMessage(ex.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);	
		}
	}
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if(this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}

	
	public Lancamento getLancamento() {
		return lancamento;
	}
	

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}
	

	public TipoLancamento[] getTipoLancamentos() {
		return TipoLancamento.values();
	}
	
	
	
	
	
	
	
	
	
}
