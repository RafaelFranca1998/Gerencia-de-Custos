package com.gerencia.connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {
	
	private String hostname;
	private int port;
	private String database;
	private String username;
	private String password;
	
	private Connection conexao;
	
	public Datasource() {
		try {
			
			hostname = "localhost";
			port = 3306;
			database = "cost_management_db";
			username = "root";
			password = "32612421";

			String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useTimezone=true&serverTimezone=UTC?useSSL=false";
			DriverManager.getConnection(url, username, password);
			System.out.println("Conexao Efetuada!");
		} catch (SQLException e) {
			System.out.println("Não Conectou" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro Diverso" + e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return conexao;

	}

	public void closeConnection() {
		try {
			conexao.close();
			System.out.println("Conexão Encerrada!");
		} catch (SQLException e) {
			System.out.println("Não Fechou a Conexão: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro Diverso: " + e.getMessage());

		}
	}
}
