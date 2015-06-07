 package br.com.oliversys.babetteunhas.control;
 
 import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import bean.Estabelecimento;
import br.com.oliversys.babetteunhas.persistence.JPAGenericDAO;
import br.com.oliversys.babetteunhas.persistence.JpaDAO;
import br.com.oliversys.babetteunhas.service.ejb.IEstabelecimentoLocalEJB;
 
 @Stateless
 @Local({IEstabelecimentoLocalEJB.class})
 public class EstabelecimentoEJBImpl implements IEstabelecimentoLocalEJB
 {   
   @Inject
   @JpaDAO
   private JPAGenericDAO<Estabelecimento> jpaDao;
      
   public EstabelecimentoEJBImpl() {}

   public Estabelecimento incluir(Estabelecimento e)
   {
	   return (Estabelecimento)this.jpaDao.incluir(e);
   }

   public void atualizar(Estabelecimento c)
   {
	   this.jpaDao.atualizar(c);
   }

   public void remover(Estabelecimento e)
   {
	   Estabelecimento c = loadEstabelecimento(e.getId().toString());
	   this.jpaDao.remover(c);
   }

   private Estabelecimento loadEstabelecimento(String id) {
	   Estabelecimento c = (Estabelecimento)this.jpaDao.findById(Long.valueOf(id));
	   if (c == null) {
		   throw new EntityNotFoundException(
				   "Estabelecimento inexistente. ID ESTABELECIMENTO: " + id);
	   }
	   return c;
   }

   public List consultarTodos() {
	   return jpaDao.consultarTodos();
   }

   public Estabelecimento findById(Object paramObject) {
	   return jpaDao.findById(paramObject);
   }

   // associacao com embedded document "endereco"
   public List<Estabelecimento> consultarPorBairro(String b) {
	   return this.consultarPorCampo("endereco.bairro", b);
   }

   public List<Estabelecimento> consultarPorCidade(String c) {	   
	   return this.consultarPorCampo("endereco.cidade", c);
   }

   @Override
   public List<Estabelecimento> consultarPorCampo(String paramString,
		   Object paramObject) {
	   return jpaDao.consultarPorCampo(paramString,paramObject);
   }

	@Override
	public List<Estabelecimento> consultarPorCEP(String c) {
		return jpaDao.consultarPorCampoLike("endereco","CEP", c);
	}

}
