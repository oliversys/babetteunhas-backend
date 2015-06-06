package br.com.oliversys.babetteunhas.service.ejb;

import java.util.List;

import bean.Estabelecimento;
import br.com.oliversys.babetteunhas.persistence.ICRUD;


public interface IEstabelecimentoLocalEJB extends ICRUD<Estabelecimento>{
	
	public List<Estabelecimento> consultarTodos();
	public List<Estabelecimento> consultarPorBairro(String b);
	public List<Estabelecimento> consultarPorCidade(String c);
}
