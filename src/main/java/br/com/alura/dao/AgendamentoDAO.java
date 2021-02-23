package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.alura.entidade.Agendamento;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AgendamentoDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserTransaction userTransaction;
	
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
		try {
			userTransaction.begin();
			em.merge(agendamento);
			userTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
