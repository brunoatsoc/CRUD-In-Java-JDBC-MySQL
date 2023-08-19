package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO{
	/*
	 * CRUD
	 * C = Create OK
	 * R = Read OK
	 * U = Update OK
	 * D = Delete OK
	 */
	
	public void save(Contato contato){
		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			//Cria conexão combanco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//PreparedStatement para executar uma query
			pstm = conn.prepareStatement(sql);
			//Adiciona os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new java.sql.Date(contato.getDataCadastro().getTime()));
			
			//Executar a query
			pstm.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//Fechar as conecxões
			try{
				if(pstm != null){
					pstm.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contatos";
		List<Contato> contatos = new ArrayList();
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai reculperar os dados do banco SELECT
		ResultSet rst = null;
		
		try{
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rst = pstm.executeQuery();
			
			while(rst.next()){
				Contato contato = new Contato();
				//Reculperar ID
				contato.setId(rst.getInt("id"));
				//Reculpera nome
				contato.setNome(rst.getString("nome"));
				//Reculpera a idade
				contato.setIdade(rst.getInt("idade"));
				//Reculpara Data de Cadadstro
				contato.setDataCadastro(rst.getDate("datacadastro"));
				
				//Guarda os contatos na lista
				contatos.add(contato);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rst != null){
					rst.close();
				}
				
				if(pstm != null){
					pstm.close();
				}
				
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return contatos;
	}

	public void update(Contato contato){
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.setInt(4, contato.getId());
			
			pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstm != null){
					pstm.close();
				}
				
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void delete(int id){
		String sql = "DELETE FROM contatos WHERE id = " + id;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.execute();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstm != null){
					pstm.close();
				}
				
				if(conn != null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}