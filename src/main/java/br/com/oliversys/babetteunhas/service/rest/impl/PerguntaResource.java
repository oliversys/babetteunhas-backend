 package br.com.oliversys.babetteunhas.service.rest.impl;
 
 import java.util.Hashtable;
import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.oliversys.babette.bean.entity.Pergunta;
import br.com.oliversys.babetteunhas.control.QuizEJBImpl;
import br.com.oliversys.babetteunhas.service.ejb.IQuizLocalEJB;
import br.com.oliversys.babetteunhas.service.rest.IQuizRESTService;
 
 @TransactionManagement(TransactionManagementType.CONTAINER)
 @Path("/perguntas")
 public class PerguntaResource implements IQuizRESTService
 {
   @Inject
   private IQuizLocalEJB ejb;
   
   public PerguntaResource()
   {
     doSessionEJBlookup();
   }
       
   private void doSessionEJBlookup() {
     Hashtable<String, String> jndiProperties = new Hashtable();
     jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
     Context context = null;
     String appName = "babetteunhas-backend";
     String beanName = QuizEJBImpl.class.getSimpleName();
     String viewClassName = IQuizLocalEJB.class.getName();
     try
     {
       context = new InitialContext(jndiProperties);
       this.ejb = ((IQuizLocalEJB)context.lookup(
         "java:app/babetteunhas-backend/" + beanName + "!" + viewClassName));
     } catch (NamingException e) {
       e.printStackTrace();
     }
   }

   @Path("/todos")
   @GET
   @Produces({"application/json"})
   public Response consultarTodasPerguntas() {
	   List<Pergunta> lista = this.ejb.consultarTodos();	   
	   return Response.ok().entity(lista).build();
   }   
  
   @Path("/remover/{id}")
   @DELETE
   public Response remover(@PathParam("id") String id)
   {
	 Pergunta p = this.ejb.findById(Long.valueOf(id));
	 if (p != null){
		 this.ejb.remover(p);
		 return Response.ok().build();
	 }
	 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }
   
   @Path("/incluir")
   @PUT
   @Consumes({"application/json"})
   public Response incluir(Pergunta p) {
	   Pergunta perg = this.ejb.incluir(p);
	   if (perg != null)
		   return Response.ok().entity(perg).build();
	   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }

   @PUT
   @Consumes({"application/json"})
   public Response atualizar(Pergunta p) {
	   this.ejb.atualizar(p);
	   return Response.ok().build();
   }
 }
