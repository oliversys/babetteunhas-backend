package br.com.oliversys.babetteunhas.service.rest;

import javax.ws.rs.core.Response;

import bean.Estabelecimento;

public interface IEstabelecimentoRESTService
{
	public Response consultarPorBairro(String b);
	public Response consultarPorCidade(String c);
	public Response incluir(Estabelecimento e);
	public Response remover(String id);
	public Response atualizar(Estabelecimento e);
	
}