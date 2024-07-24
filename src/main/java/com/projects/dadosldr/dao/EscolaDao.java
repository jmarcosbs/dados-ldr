package com.projects.dadosldr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.projects.dadosldr.entity.Escola;

public class EscolaDao {

	public EscolaDao() {
		
	}
	
	public Connection getConexao() throws ClassNotFoundException {

		// Driver
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);

		// Banco de dados
		String url = "jdbc:mysql://localhost:3306/brasil38k?useTimezone=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";

		// Usuario
		String user = "root";

		// Senha
		String password = "root";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;

	}
	
	public int insereEscolaBruta(Escola escola) {
		
		String insert = "INSERT INTO lista_para_envio (lista, nome, inep, uf, municipio, endereco, telefone, porte, emeo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, escola.getLista());
			pst.setString(2, escola.getNome());
			pst.setInt(3, escola.getInep());
			pst.setString(4, escola.getUf());
			pst.setString(5, escola.getMunicipio());
			pst.setString(6, escola.getEndereco());
			pst.setString(7, escola.getTelefone());
			pst.setString(8, escola.getPorte());
			pst.setString(9, escola.getEmeo());
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			int chaveGerada;
			if(rs.next()) {
				chaveGerada = rs.getInt(1);	
				return chaveGerada;
			}
			
			rs.close();
			pst.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public String[] getListas() {
		
		String sql = "SELECT DISTINCT lista FROM brasil38k.lista_para_envio;";
		
		String[] listas = null;
		
		try {
			
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			int tamanhoVetor = 0;
			while (rs.next()) {
				tamanhoVetor++;
			}
			
			listas = new String[tamanhoVetor];
			
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			int i = 0;
			while (rs.next()) {
				listas[i] = rs.getString(1);
				i++;
			}
			
			rs.close();
			pst.close();
			con.close();
			
			return listas;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public Escola acessaLista(String nomeLista) {
		
		String sql = "SELECT * FROM lista_para_envio WHERE lista = ? limit 1;";
		
		Escola escola = new Escola();
		
		try {
			
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nomeLista);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				int idEscola = rs.getInt(1);
				String lista = rs.getString(2);
				String nome = rs.getString(3);
				int inep = rs.getInt(4);
				String uf = rs.getString(5);
				String municipio = rs.getString(6);
				String endereco = rs.getString(7);
				String telefone = rs.getString(8);
				String porte = rs.getString(9);
				String emeo = rs.getString(10);
				String cnpj = rs.getString(11);
				String email = rs.getString(12);
				String telefone2 = rs.getString(13);
				String telefone3 = rs.getString(14);
				String telefone4 = rs.getString(15);
				String site = rs.getString(16);
				String instagram = rs.getString(17);
				String sistemaEnsino = rs.getString(18);
				String agenda = rs.getString(19);
				String anotacoes = rs.getString(20);
				
				escola = new Escola(idEscola, lista, nome, inep, uf, municipio, endereco, telefone, porte, emeo, cnpj, email, telefone2, telefone3, telefone4, site, instagram, sistemaEnsino, agenda, anotacoes);
				
			}
		
			rs.close();
			pst.close();
			con.close();
			
		
			return escola;
			
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public Escola acessaEscolaById(int id, String listaEscolhida) {
		
		String sql = "SELECT * FROM lista_para_envio WHERE id = ? and lista = ?";
		
		Escola escola = new Escola();
		
		try {
			
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, listaEscolhida);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				int idEscola = rs.getInt(1);
				String lista = rs.getString(2);
				String nome = rs.getString(3);
				int inep = rs.getInt(4);
				String uf = rs.getString(5);
				String municipio = rs.getString(6);
				String endereco = rs.getString(7);
				String telefone = rs.getString(8);
				String porte = rs.getString(9);
				String emeo = rs.getString(10);
				String cnpj = rs.getString(11);
				String email = rs.getString(12);
				String telefone2 = rs.getString(13);
				String telefone3 = rs.getString(14);
				String telefone4 = rs.getString(15);
				String site = rs.getString(16);
				String instagram = rs.getString(17);
				String sistemaEnsino = rs.getString(18);
				String agenda = rs.getString(19);
				String anotacoes = rs.getString(20);
				
				escola = new Escola(idEscola, lista, nome, inep, uf, municipio, endereco, telefone, porte, emeo, cnpj, email, telefone2, telefone3, telefone4, site, instagram, sistemaEnsino, agenda, anotacoes);
				
			}
		
			rs.close();
			pst.close();
			con.close();
			
		
			return escola;
			
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public int atualizaEscola(Escola escola) {
		
		String sql = "UPDATE lista_para_envio SET nome = ?, inep = ?, uf = ?, municipio = ?,"
				+ "endereco = ?, telefone = ?, porte = ?, emeo = ?, cnpj = ?, email = ?, telefone2 = ?,"
				+ "telefone3 = ?, telefone4 = ?, site = ?, instagram = ?, sistema_de_ensino = ?,"
				+ "agenda_digital = ?, anotacoes = ? WHERE id = ?";
		
		int chaveFinal = 0;
		
		try {
			
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, escola.getNome());
			pst.setInt(2, escola.getInep());
			pst.setString(3, escola.getUf());
			pst.setString(4, escola.getMunicipio());
			pst.setString(5, escola.getEndereco());
			pst.setString(6, escola.getTelefone());
			pst.setString(7, escola.getPorte());
			pst.setString(8, escola.getEmeo());
			pst.setString(9, escola.getCnpj());
			pst.setString(10, escola.getEmail());
			pst.setString(11, escola.getTelefone2());
			pst.setString(12, escola.getTelefone3());
			pst.setString(13, escola.getTelefone4());
			pst.setString(14, escola.getSite());
			pst.setString(15, escola.getInstagram());
			pst.setString(16, escola.getSistemaDeEnsino());
			pst.setString(17, escola.getAgendaDigital());
			pst.setString(18, escola.getAnotacoes());
			pst.setInt(19, escola.getId());
			pst.executeUpdate();
			
			pst.close();
			con.close();
			
			chaveFinal = 1;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			chaveFinal = 0;
		}

		return chaveFinal;
		
	}
	
	public int excluiLista(String listaNome) {
		
		String sql = "DELETE FROM lista_para_envio WHERE lista = ?;";
		
		int chaveFinal = 0;
		
		try {
			
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, listaNome);

			pst.executeUpdate();
			
			pst.close();
			con.close();
			
			chaveFinal = 1;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			chaveFinal = 0;
		}

		return chaveFinal;
		
	}
	
	
}
