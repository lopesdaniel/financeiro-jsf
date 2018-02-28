package com.blogspot.danieldeveloper.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.blogspot.danieldeveloper.model.Pessoa;
import com.blogspot.danieldeveloper.repository.Pessoas;
import com.blogspot.danieldeveloper.util.JPAUtil;

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	// Classe que é capaz de converter uma string com o código da pessoa
	// em objeto do tipo Pessoa, e vice-versa.

	@Inject // Graças ao Omnifaces
	private Pessoas pessoas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;
		
		if(value != null) {
			retorno = this.pessoas.porId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Pessoa) value).getId().toString();
		}

		return null;
	}

}
