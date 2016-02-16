 package br.com.oliversys.babetteunhas.rest;
 
 import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.oliversys.babette.bean.entity.Pergunta;
import br.com.oliversys.babetteunhas.ejb.spec.IQuizLocalEJB;
import br.com.oliversys.babetteunhas.rest.spec.IQuizRESTService;
 
 @TransactionManagement(TransactionManagementType.CONTAINER)
 @Path("/perguntas")
 public class PerguntaResource implements IQuizRESTService
 {
   @Inject
   private IQuizLocalEJB ejb;
   
   public PerguntaResource(){}
       
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
