package model;

import java.util.List;

import dao.LeitoDAO;

public class Leito {
	private int codigoLeito;
	private int codigoAla;
	private String descricaoAla;
	private int codigoPatrimonio;
	private int codigoStatusLeito;
	private String descricaoStatusLeito;
	
	public int getCodigoLeito() {
		return codigoLeito;
	}

	public void setCodigoLeito(int codigoLeito) {
		this.codigoLeito = codigoLeito;
	}

	public int getCodigoAla() {
		return codigoAla;
	}

	public void setCodigoAla(int codigoAla) {
		this.codigoAla = codigoAla;
	}
	
	public String getDescricaoAla() {
		return descricaoAla;
	}

	public void setDescricaoAla(String descricaoAla) {
		this.descricaoAla = descricaoAla;
	}

	public int getCodigoPatrimonio() {
		return codigoPatrimonio;
	}

	public void setCodigoPatrimonio(int codigoPatrimonio) {
		this.codigoPatrimonio = codigoPatrimonio;
	}

	public int getCodigoStatusLeito() {
		return codigoStatusLeito;
	}

	public void setCodigoStatusLeito(int codigoStatusLeito) {
		this.codigoStatusLeito = codigoStatusLeito;
	}

	public String getDescricaoStatusLeito() {
		return descricaoStatusLeito;
	}

	public void setDescricaoStatusLeito(String descricaoStatusLeito) {
		this.descricaoStatusLeito = descricaoStatusLeito;
	}

	public boolean cadastrarLeito() {
		LeitoDAO ldao = new LeitoDAO();
		return ldao.insert(this);
	}
	
	public boolean desbloquearLeito() {
		LeitoDAO ldao = new LeitoDAO();
		if (codigoStatusLeito == 1) {
			this.setCodigoStatusLeito(2);
			return ldao.update(this);
		}
		else {
			return false;
		}
	}
	
	public boolean bloquearLeito() {
		LeitoDAO ldao = new LeitoDAO();
		this.setCodigoStatusLeito(1);
		return ldao.update(this);
	}
	
	public boolean liberarLeito() {
		LeitoDAO ldao = new LeitoDAO();
		if (codigoStatusLeito == 2) {
			this.setCodigoStatusLeito(3);
			return ldao.update(this);
		}
		else {
			return false;
		}
	}
	
	public List<Leito> getLeitosLivres(int codigoAla) {
		LeitoDAO ldao = new LeitoDAO();
		return ldao.getLeitosLivres(codigoAla);
	}
	
	public List<Leito> getLeitos() {
		LeitoDAO ldao = new LeitoDAO();
		return ldao.getLeitos();
	}
	
	public List<Leito> getLeitos(int codigoAla) {
		LeitoDAO ldao = new LeitoDAO();
		return ldao.getLeitos(codigoAla);
	}
	
	public Leito getStatusLeito(int codigoLeito) {
		LeitoDAO ldao = new LeitoDAO();
		return ldao.getStatusLeito(codigoLeito);
	}
	
	public List<Leito> getLeitosLivresAgendamento(java.util.Date data, int codigoAla) {
		LeitoDAO ldao = new LeitoDAO();
		return ldao.getLeitosLivresAgendamento(data, codigoAla);
	}
	
	public int[][] getEstatistica(java.util.Date dataInicio, java.util.Date dataFim) {
		LeitoDAO ldao = new LeitoDAO();
		return ldao.getEstatistica(dataInicio, dataFim);
	}
}