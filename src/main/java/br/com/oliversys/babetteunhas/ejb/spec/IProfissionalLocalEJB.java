package br.com.oliversys.babetteunhas.ejb.spec;

import java.util.List;

import bean.Profissional;
import br.com.oliversys.babetteunhas.persistence.ICRUD;


public interface IProfissionalLocalEJB extends ICRUD<Profissional>{
	
	public List<Profissional> consultarTodos();
	public List<Profissional> consultarPorSalao(String c);
	public List<Profissional> consultarPorAvaliacao(String c);
}
