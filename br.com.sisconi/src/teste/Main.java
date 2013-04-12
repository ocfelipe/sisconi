package teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.PacienteDAO;

import model.Agendamento;
import model.Ala;
import model.Internacao;
import model.Leito;
import model.Medico;
import model.Paciente;

public class Main {
	public static void main(String[] args) throws ParseException {
		/*Paciente p = new Paciente();
		p.setCpf("99999999999");
		p.setDataNascimento(new Date("12/12/1956"));
		p.setEnderecoBairro("Luzia");
		p.setEnderecoCEP("49000000");
		p.setEnderecoCidade("Aracaju");
		p.setEnderecoComplemento("Atr�s do Posto Ipiranga");
		p.setEnderecoEstado("Sergipe");
		p.setEnderecoNumero(100);
		p.setEnderecoRua("Hermes Fontes");
		p.setNome("Jo�o dos Santos");
		p.setNomeMae("Maria dos Santos");
		p.setNumeroSus("999999999999999");
		p.setNumeroTelefone("7932222222");
		
		PacienteDAO pdao = new PacienteDAO();
		pdao.insert(p);*/
		
//		Paciente p = new Paciente();
//		p.setNumeroSus("333333333333333");		
//		p = p.localizarPaciente();
//		System.out.println(p.toString());
		
//		Ala a = new Ala();
//		List<Ala> lal = a.getAlas();
//		for (int i=0; i<lal.size(); i++) {
//			System.out.println(lal.get(i).getNomeAla());
//		}
		
//		Leito l = new Leito();
//		l.setCodigoAla(1);
//		l.setCodigoPatrimonio(1);
//		l.cadastrarLeito();
		
//		Leito l = new Leito();
//		l.setCodigoStatusLeito(2);
//		l.setCodigoLeito(1);
//		l.liberarLeito();
		
//		Leito l = new Leito();
//		List<Leito> lle = l.getLeitos(1);
//		for(int i=0; i<lle.size(); i++) {
//			System.out.println(lle.get(i).toString());
//		}
		
//		Leito l = new Leito();
//		List<Leito> lle = l.getLeitosLivres();
//		for(int i=0; i<lle.size(); i++) {
//			System.out.println(lle.get(i).toString());
//		}
		
//		Paciente p = new Paciente();
//		p.setCodigoPaciente(2);
//		Leito l = new Leito();
//		l.setCodigoLeito(1);
//		Medico m1 = new Medico();
//		m1.setCodigoUsuario(2);
//		List<Medico> lm = new ArrayList<Medico>();
//		lm.add(m1);
//		
//		Internacao i = new Internacao();
//		i.setPaciente(p);
//		i.setLeito(l);
//		i.setListaMedico(lm);
//		
//		i.iniciarInternacao();
		
//		Leito l = new Leito();
//		l = l.getStatusLeito(2);
//		System.out.println(l.toString());
		
//		Internacao i = new Internacao();
//		boolean b = i.verificaPacienteInternado("999999999999999");
//		if (b == true) {
//			System.out.println("Paciente Internado");
//		} else {
//			System.out.println("Paciente n�o internado");
//		}
		
//		Leito l = new Leito();
//		List<Leito> lle = l.getLeitosLivresAgendamento(new Date());
//		for (int i=0; i<lle.size(); i++) {
//			System.out.println(lle.get(i));
//		}
		
//		Agendamento a = new Agendamento();
//		System.out.println(a.getAgendamento("666666666666666").toString());
		
		Leito l = new Leito();
		int[][] lista = l.getEstatistica(new SimpleDateFormat("dd/MM/yyyy").parse("11/04/2013"), new SimpleDateFormat("dd/MM/yyyy").parse("14/04/2013"));
		for (int i=0; i<lista.length; i++) {
			System.out.println("Leito: "+lista[i][0]+" Quantidade: "+lista[i][1]);
		}
		
		
	}
	

}
