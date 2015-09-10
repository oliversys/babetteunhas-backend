 package br.com.oliversys.babetteunhas.control;
 
 import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import bean.Profissional;
import br.com.oliversys.babetteunhas.persistence.JPAGenericDAO;
import br.com.oliversys.babetteunhas.persistence.JpaDAO;
import br.com.oliversys.babetteunhas.service.ejb.IProfissionalLocalEJB;
 
 @Stateless
 @Local({IProfissionalLocalEJB.class})
 public class ProfissionalEJBImpl implements IProfissionalLocalEJB
 {   
   @Inject
   @JpaDAO
   private JPAGenericDAO<Profissional> jpaDao;
      
   public ProfissionalEJBImpl() {}

   public Profissional incluir(Profissional e)
   {
	   return (Profissional)this.jpaDao.incluir(e);
   }

   public void atualizar(Profissional c)
   {
	   this.jpaDao.atualizar(c);
   }

   public void remover(Profissional e)
   {
	   Profissional c = loadProfissional(e.getId().toString());
	   this.jpaDao.remover(c);
   }

   private Profissional loadProfissional(String id) {
	   Profissional c = (Profissional)this.jpaDao.findById(Long.valueOf(id));
	   if (c == null) {
		   throw new EntityNotFoundException(
				   "Estabelecimento inexistente. ID ESTABELECIMENTO: " + id);
	   }
	   return c;
   }

   public List<Profissional> consultarTodos() {
	   return jpaDao.consultarTodos();
   }

   public Profissional findById(Object paramObject) {
	   return jpaDao.findById(paramObject);
   }

   public List<Profissional> consultarPorCampo(String paramString,String subCampo,
		   Object paramObject) {
	   return jpaDao.consultarPorCampo(paramString,subCampo,paramObject);
   }

	@Override
	public List<Profissional> consultarPorSalao(String idSalao) {
		return consultarPorCampo("estabelecimento","id", idSalao);
	}

	@Override
	public List<Profissional> consultarPorAvaliacao(String a) {
		return consultarPorCampo("avaliacao", null, a);
	}
}
