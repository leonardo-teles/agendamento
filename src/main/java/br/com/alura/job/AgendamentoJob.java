package br.com.alura.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.entidade.Agendamento;
import br.com.alura.servico.AgendamentoServico;

@Stateless
public class AgendamentoJob {

	@Inject
	private AgendamentoServico agendamentoServico;
	
	@Schedule(hour = "*", minute = "*", second = "*/10")
	public void enviarEmail() {
		List<Agendamento> listarPorNaoAgendado = agendamentoServico.listarPorNaoAgendado();
		listarPorNaoAgendado.forEach(emailNaoAgendado -> {
			agendamentoServico.enviar(emailNaoAgendado);
			agendamentoServico.alterar(emailNaoAgendado);
		});
	}
}
