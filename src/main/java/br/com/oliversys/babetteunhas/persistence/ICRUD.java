package br.com.oliversys.babetteunhas.persistence;


import java.util.List;

import bean.IBabetteUnhasEntity;

public abstract interface ICRUD<T extends IBabetteUnhasEntity>
{
  public abstract List<T> consultarTodos();
  
  public abstract T findById(Object paramObject);
  
  public abstract List<T> consultarPorCampo(String paramString,Object paramObject);
  
  public abstract T incluir(T paramT);
  
  public abstract void atualizar(T paramT);
  
  public abstract void remover(T paramT);
}