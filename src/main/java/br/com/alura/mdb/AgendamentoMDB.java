package br.com.alura.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.alura.entidade.Agendamento;
import br.com.alura.servico.AgendamentoServico;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/EmailQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class AgendamentoMDB implements MessageListener {

	@Inject
	private AgendamentoServico agendamentoServico;

	@Override
	public void onMessage(Message message) {
		try {
			Agendamento agendamento = message.getBody(Agendamento.class);
			agendamentoServico.enviar(agendamento);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
}
