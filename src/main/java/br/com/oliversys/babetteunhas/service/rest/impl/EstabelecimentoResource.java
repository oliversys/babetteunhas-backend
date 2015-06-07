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

import bean.Estabelecimento;
import br.com.oliversys.babetteunhas.control.EstabelecimentoEJBImpl;
import br.com.oliversys.babetteunhas.service.ejb.IEstabelecimentoLocalEJB;
import br.com.oliversys.babetteunhas.service.rest.IEstabelecimentoRESTService;
 
 @TransactionManagement(TransactionManagementType.CONTAINER)
 @Path("/estabelecimento")
 public class EstabelecimentoResource implements IEstabelecimentoRESTService
 {
   @Inject
   private IEstabelecimentoLocalEJB ejb;
   
   public EstabelecimentoResource()
   {
     doSessionEJBlookup();
   }
       
   private void doSessionEJBlookup() {
     Hashtable<String, String> jndiProperties = new Hashtable();
     jndiProperties.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
     Context context = null;
     String appName = "babetteunhas-backend";
     String beanName = EstabelecimentoEJBImpl.class.getSimpleName();
     String viewClassName = IEstabelecimentoLocalEJB.class.getName();
     try
     {
       context = new InitialContext(jndiProperties);
       this.ejb = ((IEstabelecimentoLocalEJB)context.lookup(
         "java:app/babetteunhas-backend/" + beanName + "!" + viewClassName));
     } catch (NamingException e) {
       e.printStackTrace();
     }
   }

   @Path("/todos")
   @GET
   @Produces({"application/json"})
   public Response consultarTodos() {
	   List<Estabelecimento> lista = this.ejb.consultarTodos();
	   return Response.ok().entity(lista).build();
   }   

   @Path("/cep/{cep}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorCEP(@PathParam("cep") String cep) {
	   List<Estabelecimento> lista = this.ejb.consultarPorCEP(cep);
	   return Response.ok().entity(lista).build();
   }
   
   @Path("/bairro/{bairro}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorBairro(@PathParam("bairro") String b) {
	   List<Estabelecimento> lista = this.ejb.consultarPorBairro(b);
	   return Response.ok().entity(lista).build();
   }

   @Path("/cidade/{cidade}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorCidade(@PathParam("cidade") String c) {
	   List<Estabelecimento> lista = this.ejb.consultarPorCidade(c);
	   return Response.ok().entity(lista).build();
   }
   
   @Path("/remover/{id}")
   @DELETE
   public Response remover(@PathParam("id") String id)
   {
	 Estabelecimento e = this.ejb.findById(Long.valueOf(id));
	 if (e != null){
		 this.ejb.remover(e);
		 return Response.ok().build();
	 }
	 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }
   
   @Path("/incluir")
   @PUT
   @Consumes({"application/json"})
   public Response incluir(Estabelecimento e) {
	   Estabelecimento estab = this.ejb.incluir(e);
	   if (estab != null)
		   return Response.ok().entity(estab).build();
	   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }

   @PUT
   @Consumes({"application/json"})
   public Response atualizar(Estabelecimento e) {
	   this.ejb.atualizar(e);
	   return Response.ok().build();
   }
 }
