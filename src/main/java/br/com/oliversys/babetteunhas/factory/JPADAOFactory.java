 package br.com.oliversys.babetteunhas.factory;
 
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import bean.IBabetteUnhasEntity;
import br.com.oliversys.babetteunhas.persistence.JPAGenericDAO;
import br.com.oliversys.babetteunhas.persistence.JpaDAO;
 
 public class JPADAOFactory<T>
 {
   @javax.persistence.PersistenceContext(unitName="babette-unhas")
   private EntityManager entityManager;
   
   public JPADAOFactory() {}
   
   @Produces
   @JpaDAO
   public <T extends IBabetteUnhasEntity> JPAGenericDAO<T> create(InjectionPoint p)
   {
     if (p.getAnnotated().isAnnotationPresent(JpaDAO.class)) {
       ParameterizedType type = (ParameterizedType)p.getType();
       Type[] typeArgs = type.getActualTypeArguments();
       Class<T> entityClass = (Class)typeArgs[0];
       CriteriaBuilder cb = this.entityManager.getEntityManagerFactory().getCriteriaBuilder();
       return new JPAGenericDAO(this.entityManager, cb, entityClass);
     }
     throw new IllegalArgumentException("Anotacao @JpaDao eh obrigatoria na injecao do JPAGenericDAO");
   }
 }
