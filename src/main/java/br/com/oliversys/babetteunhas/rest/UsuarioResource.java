 package br.com.oliversys.babetteunhas.rest;
 
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

import bean.Usuario;
import br.com.oliversys.babetteunhas.ejb.UsuarioEJBImpl;
import br.com.oliversys.babetteunhas.ejb.spec.IUsuarioLocalEJB;
import br.com.oliversys.babetteunhas.rest.spec.IUsuarioRESTService;
 
 @TransactionManagement(TransactionManagementType.CONTAINER)
 @Path("/usuario")
 public class UsuarioResource implements IUsuarioRESTService
 {
   @Inject
   private IUsuarioLocalEJB ejb;
   
   public UsuarioResource(){}
       
   @Path("/todos")
   @GET
   @Produces({"application/json"})
   public Response consultarTodos() {
	   List<Usuario> lista = this.ejb.consultarTodos();	   
	   return Response.ok().entity(lista).build();
   }   

   @Path("/nome/{nome}")
   @GET
   @Produces({"application/json"})
   public Response consultarPorNome(@PathParam("nome") String nome) {
	   Usuario u = this.ejb.consultarPorNome(nome);
	   return Response.ok().entity(u).build();
   }
   
   @Path("/login/{usuario}/{senha}")
   @GET
   @Produces({"application/json"})
   public Response login(@PathParam("usuario") String username,@PathParam("senha") String senha) {
	   Usuario u = this.ejb.login(username,senha);
	   return Response.ok().entity(u).build();
   }
  
   @Path("/remover/{username}")
   @DELETE
   public Response remover(@PathParam("username") String username)
   {
	 Usuario u = this.ejb.findById(username);
	 if (u != null){
		 this.ejb.remover(u);
		 return Response.ok().build();
	 }
	 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }
   
   @Path("/incluir")
   @PUT
   @Consumes({"application/json"})
   public Response incluir(Usuario u) {
	   Usuario user = this.ejb.incluir(u);
	   if (user != null)
		   return Response.ok().entity(user).build();
	   return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
   }

   @PUT
   @Consumes({"application/json"})
   public Response atualizar(Usuario u) {
	   this.ejb.atualizar(u);
	   return Response.ok().build();
   }
 }
