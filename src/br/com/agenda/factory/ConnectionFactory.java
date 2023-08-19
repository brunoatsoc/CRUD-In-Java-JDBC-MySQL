package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory{
	//Nome do usuario
	private static final String USERNAME = "root";
	//Senha do usuario
	private static final String PASSWORD = "1003200039";
	//Caminho para o banco de dados
	private static final String DATABASEURL = "jdbc:mysql://localhost:3306/agenda";

	/*
	 *Conexão com banco de dados
	 */
	public static Connection createConnectionToMySQL() throws Exception{
		//Faz conexão com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Cria conexão com banco de dados
		Connection connection = DriverManager.getConnection(DATABASEURL, USERNAME, PASSWORD);
		
		return connection;
	}

	public static void main(String[] args) throws Exception{
		//Recupera conexão com banco de dados
		Connection con = createConnectionToMySQL();
		
		if(con == null){
			System.out.println("Conexão obtida com sucesso!!!");
			con.close();
		}
	}
}