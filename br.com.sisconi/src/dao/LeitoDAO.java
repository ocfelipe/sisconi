package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Leito;

public class LeitoDAO {
	
	public boolean insert(Leito l) {
		try {
			Connection con = ConexaoBD.getInstancia().getConexao();
			String sqlLeito = "insert into tb_leito (co_ala, co_patrimonio) values (" +
					""+l.getCodigoAla()+", " +
					""+l.getCodigoPatrimonio()+")";
			Statement smt = con.createStatement();
		    smt.execute(sqlLeito);
		    
		    String sqlCodigoLeito = "select co_leito from tb_leito " +
		    		"where co_patrimonio = "+l.getCodigoPatrimonio();
		    
		    ResultSet res = smt.executeQuery(sqlCodigoLeito);
		    if (res.next()) {
		    	l.setCodigoLeito(res.getInt("co_leito"));
		    }
		    res.close();
		    
		    String sqlStatusLeito = "insert into tb_status_leito (co_status_leito, co_leito, co_status, dt_inicial) values (" +
		    		"1, " +
					""+l.getCodigoLeito()+", " +
					"1, " +
					"NOW())";
		    smt.execute(sqlStatusLeito);
		    smt.close();
		    
		    return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(Leito l) {
		try {
			Connection con = ConexaoBD.getInstancia().getConexao();
			String sqlUpdateLeitoAntigo = "update tb_status_leito set " +
				"dt_final = NOW() " + 
				"where co_leito = "+l.getCodigoLeito()+" and " +
				"dt_final is null";
			Statement smt = con.createStatement();
		    smt.executeUpdate(sqlUpdateLeitoAntigo);
		    
		    String sqlInsertLeitoStatusNovo = "insert into tb_status_leito (co_status_leito, co_leito, co_status, dt_inicial) values (" +
		    		"(select count(temp.co_leito)+1 from (select co_leito from tb_status_leito) as temp where temp.co_leito = "+l.getCodigoLeito()+"), " +
					""+l.getCodigoLeito()+", " +
					""+l.getCodigoStatusLeito()+", " +
					"NOW())";
		    
		    smt.execute(sqlInsertLeitoStatusNovo);
		    smt.close();
		    
		    return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Leito> getLeitosLivres(int codigoAla) {
		try{
			List<Leito> lle = new ArrayList<Leito>();
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select tb_leito.co_leito, tb_leito.co_ala, tb_leito.co_patrimonio, tb_status_leito.co_status, tb_status.ds_status, tb_ala.ds_ala from tb_leito " +
					"join tb_status_leito on tb_status_leito.co_leito = tb_leito.co_leito " +
					"join tb_status on tb_status_leito.co_status = tb_status.co_status " +
					"join tb_ala on tb_leito.co_ala = tb_ala.co_ala " +
					"where tb_leito.co_ala = "+codigoAla+" and tb_status_leito.co_status = 3 and tb_status_leito.dt_final is null " +
					"order by co_leito");
			while (res.next()) {
				Leito l = new Leito();
				l.setCodigoLeito(res.getInt("tb_leito.co_leito"));
				l.setCodigoAla(res.getInt("tb_leito.co_ala"));
				l.setDescricaoAla(res.getString("tb_ala.ds_ala"));
				l.setCodigoPatrimonio(res.getInt("tb_leito.co_patrimonio"));
				l.setCodigoStatusLeito(res.getInt("tb_status_leito.co_status"));
				l.setDescricaoStatusLeito(res.getString("tb_status.ds_status"));
				lle.add(l);				
			}
			return lle;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<Leito> getLeitos() {
		try{
			List<Leito> lle = new ArrayList<Leito>();
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select tb_leito.co_leito, tb_leito.co_ala, tb_leito.co_patrimonio, tb_status_leito.co_status, tb_status.ds_status, tb_ala.ds_ala from tb_leito " +
					"join tb_status_leito on tb_status_leito.co_leito = tb_leito.co_leito " +
					"join tb_status on tb_status_leito.co_status = tb_status.co_status " +
					"join tb_ala on tb_leito.co_ala = tb_ala.co_ala " +
					"where tb_status_leito.dt_final is null order by tb_ala.ds_ala, tb_leito.co_leito");
			while (res.next()) {
				Leito l = new Leito();
				l.setCodigoLeito(res.getInt("tb_leito.co_leito"));
				l.setCodigoAla(res.getInt("tb_leito.co_ala"));
				l.setDescricaoAla(res.getString("tb_ala.ds_ala"));
				l.setCodigoPatrimonio(res.getInt("tb_leito.co_patrimonio"));
				l.setCodigoStatusLeito(res.getInt("tb_status_leito.co_status"));
				l.setDescricaoStatusLeito(res.getString("tb_status.ds_status"));
				lle.add(l);				
			}
			return lle;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Leito> getLeitos(int codigoAla) {
		try{
			List<Leito> lle = new ArrayList<Leito>();
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select tb_leito.co_leito, tb_leito.co_ala, tb_leito.co_patrimonio, tb_status_leito.co_status, tb_status.ds_status, tb_ala.ds_ala from tb_leito " +
					"join tb_status_leito on tb_status_leito.co_leito = tb_leito.co_leito " +
					"join tb_status on tb_status_leito.co_status = tb_status.co_status " +
					"join tb_ala on tb_leito.co_ala = tb_ala.co_ala " +
					"where tb_leito.co_ala = "+codigoAla+" and tb_status_leito.dt_final is null order by tb_ala.ds_ala, tb_leito.co_leito");
			while (res.next()) {
				Leito l = new Leito();
				l.setCodigoLeito(res.getInt("tb_leito.co_leito"));
				l.setCodigoAla(res.getInt("tb_leito.co_ala"));
				l.setDescricaoAla(res.getString("tb_ala.ds_ala"));
				l.setCodigoPatrimonio(res.getInt("tb_leito.co_patrimonio"));
				l.setCodigoStatusLeito(res.getInt("tb_status_leito.co_status"));
				l.setDescricaoStatusLeito(res.getString("tb_status.ds_status"));
				lle.add(l);				
			}
			return lle;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Leito getStatusLeito(int codigoLeito) {
		try{
			Leito l = new Leito();
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select tb_leito.co_leito, tb_status_leito.co_status, tb_status.ds_status from tb_leito " +
					"join tb_status_leito on tb_leito.co_leito = tb_status_leito.co_leito " +
					"join tb_status on tb_status_leito.co_status = tb_status.co_status " +
					"where tb_leito.co_leito = "+codigoLeito+" and tb_status_leito.dt_final is null");
			while (res.next()) {
				l.setCodigoLeito(res.getInt("tb_leito.co_leito"));
				l.setCodigoStatusLeito(res.getInt("tb_status_leito.co_status"));
				l.setDescricaoStatusLeito(res.getString("tb_status.ds_status"));				
			}
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Leito> getLeitosLivresAgendamento(java.util.Date data, int codigoAla) {
		try{
			List<Leito> lle = new ArrayList<Leito>();
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			String sqlGetLeitosLivresAgendamento = "select l.co_leito, l.co_ala, a.ds_ala, l.co_patrimonio from tb_leito as l " +
					"join tb_status_leito as stl on (l.co_leito = stl.co_leito) " +
					"join tb_ala as a on (a.co_ala = l.co_ala) " +
				    "where stl.co_status <> 1 and a.co_ala = "+codigoAla+" and stl.dt_final is null and l.co_leito not in (select agen.co_leito " +
					"from tb_agendamento as agen where DATE(agen.dt_agendamento) = '"+new SimpleDateFormat("yyyy-MM-dd").format(data)+"')";
			ResultSet res = stm.executeQuery(sqlGetLeitosLivresAgendamento);
			while (res.next()) {
				Leito l = new Leito();
				l.setCodigoLeito(res.getInt("l.co_leito"));
				l.setCodigoAla(res.getInt("l.co_ala"));
				l.setDescricaoAla(res.getString("a.ds_ala"));
				l.setCodigoPatrimonio(res.getInt("l.co_patrimonio"));
				lle.add(l);				
			}
			return lle;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int[][] getEstatistica(java.util.Date dataInicio, java.util.Date dataFim) {
		try{
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select count(stl.co_status) as cont, stl.co_leito, stl.co_status " +
					"from tb_status_leito as stl " +
					"where co_status = 4 " +
					"and DATE(stl.dt_inicial) >= '"+new SimpleDateFormat("yyyy-MM-dd").format(dataInicio)+"' " +
					"and DATE(dt_inicial) <= '"+new SimpleDateFormat("yyyy-MM-dd").format(dataFim)+"' " +
					"group by stl.co_leito");
			int tamanho = 0;
			if (res.last()) {
				tamanho = res.getRow();
				res.beforeFirst();
			}
			int[][] retorno = new int[tamanho][2];
			int contador = 0;
			while (res.next()) {				
				retorno[contador][0] = res.getInt("stl.co_leito");
				retorno[contador][1] = res.getInt("cont");
				contador++;
			}
			return retorno;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

}
