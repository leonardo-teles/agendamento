package br.com.alura.servico;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoDAO;
import br.com.alura.entidade.Agendamento;

@Stateless
public class AgendamentoServico {
	
	private static final Logger LOGGER = Logger.getLogger(AgendamentoServico.class.getName());
	
	@Inject
	private AgendamentoDAO dao; 

	public List<Agendamento> listar() {
		return dao.listar();
	}
	
	public void inserir(Agendamento agendamento) {
		agendamento.setAgendado(false);
		dao.inserir(agendamento);
	}
	
	public List<Agendamento> listarPorNaoAgendado() {
		return dao.listarPorNaoAgendado();
	}
	
	public void alterar(Agendamento agendamento) {
		agendamento.setAgendado(true);
		dao.alterar(agendamento);
	}
	
	public void enviar(Agendamento agendamento) {
		try {
			Thread.sleep(5000);
			LOGGER.info("O e-Mail do(a) usuario(a) " + agendamento.getEmail() + " foi enviado!");
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}
	
}
