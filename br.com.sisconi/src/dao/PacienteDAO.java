package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Cidade;
import model.Estado;
import model.Paciente;

public class PacienteDAO {
	
	public boolean insert(Paciente p) {		
		try {
			Connection con = ConexaoBD.getInstancia().getConexao();
			if (p.getCpf().equals("")) {
				p.setCpf(null);
			}
			if (p.getNumeroTelefone().equals("")) {
				p.setNumeroTelefone(null);
			}
			if (p.getEnderecoCEP().equals("")) {
				p.setEnderecoCEP(null);
			}
			if (p.getEnderecoComplemento().equals("")) {
				p.setEnderecoComplemento(null);
			}
			
			String sqlPaciente = "insert into tb_paciente (nr_sus, nr_cpf, nm_paciente, nm_mae, nr_telefone, dt_nascimento) " +
					"values (" +
					"'"+p.getNumeroSus()+"', " +
					"'"+p.getCpf()+"', " +
					"'"+p.getNome()+"', " +
					"'"+p.getNomeMae()+"', " +
					"'"+p.getNumeroTelefone()+"', " +
					"'"+new Date(p.getDataNascimento().getTime())+"'" +
					")";
			
			String sqlEndereco = "insert into tb_endereco (co_paciente, " +
					"ds_rua, " +
					"ds_numero, " +
					"ds_bairro, " +
					"ds_cep, " +
					"ds_complemento, " +
					"co_cidade) " +
					"values " +
					"((select tb_paciente.co_paciente from tb_paciente where tb_paciente.nr_sus like '"+p.getNumeroSus()+"'), " +
					"'"+p.getEnderecoRua()+"', " +
					"'"+p.getEnderecoNumero()+"', " +
					"'"+p.getEnderecoBairro()+"', " +
					"'"+p.getEnderecoCEP()+"', " +
					"'"+p.getEnderecoComplemento()+"', " + 
					"'"+p.getEnderecoCodigoCidade()+"')";
	
		    Statement smt = con.createStatement();
		    smt.execute(sqlPaciente);
		    smt.execute(sqlEndereco);
		    smt.close();
		    
		    System.out.println("Paciente "+p.getNome()+" inserido com sucesso.");
		    
		    //ConexaoBD.getInstancia().fecharConexao();
		    return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Paciente select(String numeroSus) {
		try {
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select tb_paciente.co_paciente, nr_sus, nr_cpf, nm_paciente, nm_mae, nr_telefone, dt_nascimento," +
					" ds_rua, ds_numero, ds_bairro, ds_cep, ds_complemento, tb_cidade.nm_cidade, tb_estado.nm_estado, tb_cidade.co_cidade, tb_estado.co_estado" +
					" from tb_paciente join tb_endereco on tb_paciente.co_paciente = tb_endereco.co_paciente" +
					" join tb_cidade on tb_endereco.co_cidade = tb_cidade.co_cidade" +
					" join tb_estado on tb_cidade.co_estado = tb_estado.co_estado" +
					" where nr_sus=\""+numeroSus+"\"");
			Paciente p = new Paciente();
			if (res.next()) {
				p.setCodigoPaciente(res.getInt("tb_paciente.co_paciente"));
				p.setNumeroSus(res.getString("nr_sus"));
				String cpf = res.getString("nr_cpf");
				if (cpf.equals(null)) {
					cpf="";
				}
				p.setCpf(cpf);
				p.setNome(res.getString("nm_paciente"));
				p.setNomeMae(res.getString("nm_mae"));
				String telefone = res.getString("nr_telefone");
				if (telefone.equals(null)) {
					telefone="";
				}
				p.setNumeroTelefone(telefone);
				String formatData = new SimpleDateFormat("dd/MM/yyyy").format(res.getDate("dt_nascimento"));
				p.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(formatData));
				p.setEnderecoRua(res.getString("ds_rua"));
				p.setEnderecoNumero(res.getInt("ds_numero"));
				p.setEnderecoBairro(res.getString("ds_bairro"));
				String cep = res.getString("ds_cep");
				if (cep.equals(null)) {
					cep="";
				}
				p.setEnderecoCEP(cep);
				String complemento = res.getString("ds_complemento");
				if (complemento.equals(null)) {
					complemento="";
				}
				p.setEnderecoComplemento(complemento);
				p.setEnderecoCidade(res.getString("tb_cidade.nm_cidade"));
				p.setEnderecoEstado(res.getString("tb_estado.nm_estado"));
				p.setEnderecoCodigoCidade(res.getInt("tb_cidade.co_cidade"));
				p.setEnderecoCodigoEstado(res.getInt("tb_estado.co_estado"));
			}
			//ConexaoBD.getInstancia().fecharConexao();
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean update(Paciente p) {
		try {
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			String sqlPaciente = "UPDATE tb_paciente SET " +
					"nr_sus = '"+p.getNumeroSus()+"', " +
					"nr_cpf = '"+p.getCpf()+"', " +
					"nm_paciente = '"+p.getNome()+"', " +
					"nm_mae = '"+p.getNomeMae()+"', " +
					"nr_telefone = '"+p.getNumeroTelefone()+"', " +
					"dt_nascimento = '"+new Date(p.getDataNascimento().getTime())+"' " +
					"WHERE co_paciente = "+p.getCodigoPaciente();
			stm.executeUpdate(sqlPaciente);
			
			String sqlEndereco = "UPDATE tb_endereco SET " +
					"ds_rua = '"+p.getEnderecoRua()+"', " +
					"ds_numero = '"+p.getEnderecoNumero()+"', " +
					"ds_bairro = '"+p.getEnderecoBairro()+"', " +
					"ds_cep = '"+p.getEnderecoCEP()+"', " +
					"ds_complemento = '"+p.getEnderecoComplemento()+"', " +
					"co_cidade = "+p.getEnderecoCodigoCidade()+" " +
					"WHERE co_paciente = "+p.getCodigoPaciente();
			stm.executeUpdate(sqlEndereco);
			stm.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Estado> getEstados() {
		try{
			List<Estado> les = new ArrayList<Estado>();
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select co_estado, nm_estado, nm_uf from tb_estado order by nm_uf");
			while (res.next()) {
				Estado es = new Estado();
				es.setCodigoEstado(res.getInt("co_estado"));
				es.setNome(res.getString("nm_estado"));
				es.setUF(res.getString("nm_uf"));
				les.add(es);				
			}
			return les;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Cidade> getCidades(int codigoEstado) {
		try{
			List<Cidade> lci = new ArrayList<Cidade>();
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select co_cidade, nm_cidade, co_estado from tb_cidade where co_estado="+codigoEstado + " order by nm_cidade");
			while (res.next()) {
				Cidade ci = new Cidade();
				ci.setCodigoCidade(res.getInt("co_cidade"));
				ci.setNomeCidade(res.getString("nm_cidade"));
				ci.setCodigoEstado(res.getInt("co_estado"));
				lci.add(ci);
			}
			return lci;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean existeCpfPaciente(String cpf) {
		try {
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select nr_cpf from tb_paciente where nr_cpf like '"+cpf+"' and nr_cpf not like ''");
			if (res.next()) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean existeNumeroSusPaciente(String numeroSus) {
		try {
			Connection con = ConexaoBD.getInstancia().getConexao();
			Statement stm = con.createStatement();
			ResultSet res = stm.executeQuery("select nr_sus from tb_paciente where nr_sus like '"+numeroSus+"'");
			if (res.next()) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

}