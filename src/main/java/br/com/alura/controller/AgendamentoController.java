package br.com.alura.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.entidade.Agendamento;
import br.com.alura.servico.AgendamentoServico;

@Path("emails")
public class AgendamentoController {

	@Inject
	private AgendamentoServico agendamentoServico;
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listar() {
		return Response.ok(agendamentoServico.listar()).build();
	}

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response inserir(Agendamento agendamento) {
		agendamentoServico.inserir(agendamento);
		
		return Response.status(201).build();
	}
}
