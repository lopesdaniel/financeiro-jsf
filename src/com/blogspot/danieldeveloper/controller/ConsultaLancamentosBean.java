package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
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
	
	@Inject
	private Lancamento lancamentoSelecionado;
	
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void excluir() {
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				this.cadastro.excluir(this.lancamentoSelecionado);
				this.consultar();
				context.addMessage(null, new FacesMessage(
				"Lançamento excluído com sucesso!"));
			} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
			}
		}
	
	
}
