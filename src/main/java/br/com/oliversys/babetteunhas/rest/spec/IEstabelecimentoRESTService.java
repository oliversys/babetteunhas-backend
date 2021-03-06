package br.com.oliversys.babetteunhas.rest.spec;

import javax.ws.rs.core.Response;

import bean.Estabelecimento;

public interface IEstabelecimentoRESTService
{
	public Response consultarTodos();
	public Response consultarPorBairro(String b);
	public Response consultarPorCidade(String c);
	public Response consultarPorCEP(String c);
	public Response incluir(Estabelecimento e);
	public Response remover(String id);
	public Response atualizar(Estabelecimento e);
	
}