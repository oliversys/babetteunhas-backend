 package br.com.oliversys.babetteunhas.control;
 
 import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import bean.Usuario;
import br.com.oliversys.babetteunhas.persistence.JPAGenericDAO;
import br.com.oliversys.babetteunhas.persistence.JpaDAO;
import br.com.oliversys.babetteunhas.service.ejb.IUsuarioLocalEJB;
 
 @Stateless
 @Local({IUsuarioLocalEJB.class})
 public class UsuarioEJBImpl implements IUsuarioLocalEJB
 {   
   @Inject
   @JpaDAO
   private JPAGenericDAO<Usuario> jpaDao;
      
   public UsuarioEJBImpl() {}

   public Usuario incluir(Usuario u)
   {
	   return (Usuario)this.jpaDao.incluir(u);
   }

   public void atualizar(Usuario u)
   {
	   this.jpaDao.atualizar(u);
   }

   public void remover(Usuario u)
   {
	   Usuario usuario = loadUsuario(u.getNomeUsuario());
	   this.jpaDao.remover(usuario);
   }

   private Usuario loadUsuario(String username) {
	   Usuario c = (Usuario)this.jpaDao.findById(username);
	   if (c == null) {
		   throw new EntityNotFoundException(
				   "Usuario inexistente: " + username);
	   }
	   return c;
   }

   public List<Usuario> consultarTodos() {
	   return jpaDao.consultarTodos();
   }

   public List<Usuario> consultarPorCampo(String paramString,String subCampo,
		   Object paramObject) {
	   return jpaDao.consultarPorCampo(paramString,subCampo,paramObject);
   }

	@Override
	public Usuario consultarPorNome(String username) {
		return jpaDao.consultarPorCampo("nome",null,username).get(0);
	}

	@Override
	public Usuario findById(Object paramObject) {
		return jpaDao.findById(paramObject);
	}

	@Override
	public Usuario login(String usuario, String senha) {
		String jpql = "from Usuario where nome = "+usuario+" and senha = "+senha;
		return jpaDao.executarJPQL(jpql).get(0);
	}

}
