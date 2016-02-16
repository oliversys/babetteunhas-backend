package br.com.oliversys.babetteunhas.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.oliversys.babette.bean.entity.Pergunta;
import br.com.oliversys.babetteunhas.ejb.spec.IQuizLocalEJB;
import br.com.oliversys.babetteunhas.persistence.JPAGenericDAO;
import br.com.oliversys.babetteunhas.persistence.JpaDAO;

@Stateless
@Local({IQuizLocalEJB.class})
public class QuizEJBImpl implements IQuizLocalEJB {

	@Inject
   	@JpaDAO
   	private JPAGenericDAO<Pergunta> jpaDao;
		
	public QuizEJBImpl(){}
	
	@Override
	public List<Pergunta> consultarTodos() {
		return jpaDao.consultarTodos();
	}

	@Override
	public Pergunta findById(Object paramObject) {
		return jpaDao.findById(paramObject);
	}

	@Override
	public List<Pergunta> consultarPorCampo(String paramString,
			String subCampo, Object paramObject) {		
		return jpaDao.consultarPorCampo(paramString,subCampo,paramObject);
	}

	@Override
	public Pergunta incluir(Pergunta paramT) {
		return jpaDao.incluir(paramT);
	}

	@Override
	public void atualizar(Pergunta paramT) {
		jpaDao.atualizar(paramT);
	}

	@Override
	public void remover(Pergunta paramT) {
		jpaDao.remover(paramT);
	}

}
