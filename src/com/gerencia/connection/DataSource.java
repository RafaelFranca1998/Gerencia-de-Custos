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
			System.out.println("Não Conectou" + e.getMessage());
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
			System.out.println("Conexão Encerrada!");
		} catch (SQLException e) {
			System.out.println("Não Fechou a Conexão: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro Diverso: " + e.getMessage());

		}
	}
}
