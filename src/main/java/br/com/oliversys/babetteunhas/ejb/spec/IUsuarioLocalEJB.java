package br.com.oliversys.babetteunhas.ejb.spec;

import java.util.List;

import bean.Usuario;
import br.com.oliversys.babetteunhas.persistence.ICRUD;


public interface IUsuarioLocalEJB extends ICRUD<Usuario>{
	
	public List<Usuario> consultarTodos();
	public Usuario consultarPorNome(String nome);
	public Usuario login(String usuario,String senha);
}
