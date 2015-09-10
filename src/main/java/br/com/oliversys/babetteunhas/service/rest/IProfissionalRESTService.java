package br.com.oliversys.babetteunhas.service.rest;

import javax.ws.rs.core.Response;

import bean.Profissional;

public interface IProfissionalRESTService
{
	public Response consultarTodos();
	public Response consultarPorSalao(String idSalao);
	public Response consultarPorAvaliacao(String avaliacao);
	public Response incluir(Profissional p);
	public Response remover(String id);
	public Response atualizar(Profissional e);
	
}