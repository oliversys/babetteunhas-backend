package br.com.oliversys.babetteunhas.service.rest;

import javax.ws.rs.core.Response;

import bean.Usuario;

public interface IUsuarioRESTService
{
	public Response consultarTodos();
	public Response consultarPorNome(String nome);
	public Response incluir(Usuario u);
	public Response remover(String nome);
	public Response atualizar(Usuario u);
	
}