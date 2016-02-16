package br.com.oliversys.babetteunhas.rest.spec;

import javax.ws.rs.core.Response;

import br.com.oliversys.babette.bean.entity.Pergunta;

public interface IQuizRESTService
{
	public Response consultarTodasPerguntas();
	public Response incluir(Pergunta p);
	public Response remover(String id);
	public Response atualizar(Pergunta e);	
}