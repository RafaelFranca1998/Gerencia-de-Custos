package com.gerencia.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private String hostname;
	private int port;
	private String database;
	private String username;
	private String password;

	private Connection conexao;

	public DataSource() {
		try {
			hostname = "localhost";
			port = 5432;
			database = "cost_management_db";
			username = "postgres";
			password = "32612421";

			String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + database+"?useTimezone=true&serverTimezone=UTC";
			conexao = DriverManager.getConnection(url, username, password);
			System.out.println("Conexao Efetuada!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("N�o Conectou" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro Diverso" + e.getMessage());
		}

	};///

	public Connection getConexao() {
		return conexao;

	}

	public void fecharConexao() {
		try {
			conexao.close();
			System.out.println("Conex�o Encerrada!");
		} catch (SQLException e) {
			System.out.println("N�o Fechou a Conex�o: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro Diverso: " + e.getMessage());

		}
	}
}
