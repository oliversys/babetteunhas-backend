package br.com.oliversys.babetteunhas.persistence;


import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@javax.inject.Qualifier
@java.lang.annotation.Target(value={ElementType.TYPE,
									ElementType.PARAMETER,
									ElementType.METHOD,
									ElementType.FIELD})
@java.lang.annotation.Retention(value=RetentionPolicy.RUNTIME)
public abstract @interface JpaDAO{

}