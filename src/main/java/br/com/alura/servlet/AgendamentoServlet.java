package br.com.alura.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.entidade.Agendamento;
import br.com.alura.servico.AgendamentoServico;

@WebServlet("emails")
public class AgendamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AgendamentoServico servico;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		servico.listar().forEach(resultado -> pw.print("Os e-mails disponíveis são: " + resultado.getEmail()));
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		String[] email = reader.readLine().split(",");
		Agendamento agendamento = new Agendamento();
		agendamento.setEmail(email[0]);
		agendamento.setAssunto(email[1]);
		agendamento.setMensagem(email[2]);
		
		servico.inserir(agendamento);
	}
	
}
