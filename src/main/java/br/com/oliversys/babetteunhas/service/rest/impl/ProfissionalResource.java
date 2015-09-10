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

import bean.Profissional;
import br.com.oliversys.babetteunhas.control.ProfissionalEJBImpl;
import br.com.oliversys.babetteunhas.service.ejb.IProfissionalLocalEJB;
import br.com.oliversys.babetteunhas.service.rest.IProfissionalRESTService;
 
 @TransactionManagement(TransactionManagementType.CONTAINER)
 @Path("/profissionais")
 public class ProfissionalResource implements IProfissionalRESTService
 {
   @Inject
   private IProfissionalLocalEJB ejb;
   
   public ProfissionalResource()
   {
     doSessionEJBlookup();
   }
       
   private void doSessionEJBlookup() {
     Hashtable<String, String> jndiProperties = new Hashtable();
     jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
     Context context = null;
     String appName = "babetteunhas-backend";
     String beanName = ProfissionalEJBImpl.class.getSimpleName();
     String viewClassName = IProfissionalLocalEJB.class.getName();
     try
     {
       context = new InitialContext(jndiProperties);
       this.ejb = ((IProfissionalLocalEJB)context.lookup(
         "java:app/babetteunhas-backend/" + beanName + "!" + viewClassName));
     } catch (NamingException e) {
       e.printStackTrace();
     }
   }

   @Path("/todos")
   @GET
   @Produces({"application/json"})
   public Response consultarTodos() {
	   List<Profissional> lista = this.ejb.consultarTodos();
	   return Response.ok().entity(lista).build();
   }   
   
   @Path("/remover/{id}")
   @DELETE
   public Response remover(@PathParam("id") String id)
   {
	 Profissional p = this.ejb.findById(Long.valueOf(id));
	 if (p != null){
		 this.ejb.remover(p);
		 return Response.ok().build();
	 }
	 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }
   
   @Path("/incluir")
   @PUT
   @Consumes({"application/json"})
   public Response incluir(Profissional p) {
	   Profissional profissional = this.ejb.incluir(p);
	   if (profissional != null)
		   return Response.ok().entity(profissional).build();
	   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }

   @PUT
   @Consumes({"application/json"})
   public Response atualizar(Profissional p) {
	   this.ejb.atualizar(p);
	   return Response.ok().build();
   }

   @Path("/estab/{idSalao}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorSalao(String idSalao) {
	   List<Profissional> lista = this.ejb.consultarPorSalao(idSalao);
	   return Response.ok().entity(lista).build();
   }
	
   @Path("/avaliacao/{avaliacao}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorAvaliacao(String avaliacao) {
	   List<Profissional> lista = this.ejb.consultarPorAvaliacao(avaliacao);
	   return Response.ok().entity(lista).build();
   }
}
