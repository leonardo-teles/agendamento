package br.com.alura.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoDAO;
import br.com.alura.entidade.Agendamento;

@Stateless
public class AgendamentoServico {
	
	@Inject
	private AgendamentoDAO dao; 

	public List<Agendamento> listar() {
		return dao.listar();
	}
	
	public void inserir(Agendamento agendamento) {
		agendamento.setAgendado(false);
		dao.inserir(agendamento);
	}
}
