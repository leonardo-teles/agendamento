package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entidade.Agendamento;

@Stateless
public class AgendamentoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Agendamento> listar() {
		return em.createQuery("SELECT a FROM Agendamento a", Agendamento.class).getResultList();
	}
	
	public void inserir(Agendamento agendamento) {
		em.persist(agendamento);
	}
	
	public List<Agendamento> listarPorNaoAgendado() {
		return em.createQuery("SELECT a FROM Agendamento a WHERE a.agendado = false", Agendamento.class).getResultList();
	}
	
	public void alterar(Agendamento agendamento) {
		em.merge(agendamento);
	}

}
