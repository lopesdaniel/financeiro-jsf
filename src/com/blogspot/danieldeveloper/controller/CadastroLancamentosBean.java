package com.blogspot.danieldeveloper.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.blogspot.danieldeveloper.model.Lancamento;
import com.blogspot.danieldeveloper.model.Pessoa;
import com.blogspot.danieldeveloper.model.TipoLancamento;
import com.blogspot.danieldeveloper.repository.Lancamentos;
import com.blogspot.danieldeveloper.repository.Pessoas;
import com.blogspot.danieldeveloper.service.CadastroLancamentos;
import com.blogspot.danieldeveloper.service.NegocioException;

@Named
@ViewScoped
public class CadastroLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Lancamentos lancamentos;

	@Inject
	private Pessoas pessoas;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas = new ArrayList<>();

	public void getPrepararCadastro() {
		this.setTodasPessoas(this.pessoas.todas());
		for (Pessoa p : this.pessoas.todas()) {
			System.out.println(p.getNome());
		}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.lancamento);

			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso !"));
		} catch (NegocioException ex) {
			FacesMessage message = new FacesMessage(ex.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);
		}
	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}

	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return this.pessoas.todas();
	}

	public TipoLancamento[] getTipoLancamentos() {
		return TipoLancamento.values();
	}

	public void setTodasPessoas(List<Pessoa> todasPessoas) {
		this.todasPessoas = todasPessoas;
	}

}
