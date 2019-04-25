package com.gerencia.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gerencia.core.Item;
import com.gerencia.core.Month;
import com.gerencia.core.Year;

public class DAO {
	
	private Datasource ds;

	
	public DAO() {
		ds = new Datasource();
	}
	
	// -------------------------------------------------------------------
		/**
		 * Metodo que retorna um Arraylist contendo os dados da tabela no Banco de
		 * Dados.
		 * 
		 * @return Lista com ID, Nome, Tipo, endereco, arquivo e nome do arquivo.
		 */
		public ArrayList<Item> listItems () {
			try {
				PreparedStatement ps = ds.getConnection().prepareStatement("SELECT * FROM items");
				ResultSet rs = ps.executeQuery();
				ArrayList<Item> Lista = new ArrayList<Item>();
				while (rs.next()) {
					Item item = new Item();
					item.setName(rs.getString("item_name"));
					item.setPercentage(rs.getInt("percentage"));
					item.setValue(rs.getInt("value"));
					Lista.add(item);
				}
				return Lista;
			} catch (SQLException e) {
				System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
			} catch (Exception e) {
				System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
			} finally {
				ds.closeConnection();
			}
			return null;
		}
		// -------------------------------------------------------------
		
		/**
		 * Insere dados no banco de dados.
		 * 
		 * @param U
		 *            os dados do usuário.
		 */
		public void create(Month month,Year year, Item item) {
			Connection con = ds.getConnection();
			PreparedStatement stmp = null;
			//String sql = "UPDATE names INNER JOIN addresses ON names.ID = addresses.ID SET names.name = 'Peter', addresses.address = 'Third Street' WHERE names.ID = 1";
			try {
				stmp = con.prepareStatement("INSERT INTO items(item_name,value,percentage) VALUES (?,?,?);"
										+ "INSERT INTO year(year) VALUES (?);"
										+ "INSERT INTO month(`month`,`received`,`previous`) VALUES (?,?,?);");
				stmp.setString(1, item.getName());
				stmp.setInt(2, item.getValue());
				stmp.setInt(3, item.getPercentage());
				stmp.setInt(4, year.getYear());
				stmp.setString(5, month.getMonth());
				stmp.setInt(6, month.getReceived());
				stmp.setInt(7, month.getPrervious());
				stmp.executeUpdate();
				System.out.println("[Log] Sucesso!");
			} catch (SQLException u) {
				throw new RuntimeException(u);
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
				}
			}
		}

		public void Update(Month month,Year year, Item item) {
			Connection con = ds.getConnection();
			PreparedStatement stmp = null;
			try {
				stmp = con.prepareStatement(
						"UPDATE alunos SET nome = ?, sobrenome = ?, idade = ?, idadedia = ?, idademes = ?, idadeano = ?, curso = ?, turno = ? WHERE id = ?");
				/**stmp.setString(1, year.getNome());
				stmp.setString(2, year.getSobrenome());
				stmp.setInt(3, year.getIdade());
				stmp.setInt(4, year.getIdadedia());
				stmp.setInt(5, year.getIdademes());
				stmp.setInt(6, year.getIdadeano());
				stmp.setString(7, year.getCurso());
				stmp.setString(8, year.getTurno());
				stmp.setInt(9, year.getId());
				stmp.executeUpdate();
				**/
				System.out.println("[Log] Sucesso!");
			} catch (SQLException u) {
				throw new RuntimeException(u);
			} finally {
				try {
					ds.closeConnection();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
				}
			}

		}

		// -------------------------------------------------------------------
		/**
		 * Deleta dados baseado no ID passado como parâmetro.
		 * 
		 * @param U
		 *            ID a ser Deletado.
		 */
		public void delete(Item U) {
			Connection con = ds.getConnection();
			PreparedStatement stmp = null;
			try {
				stmp = con.prepareStatement("DELETE FROM alunos WHERE id = ?");
				stmp.setInt(1, U.getId());
				stmp.executeUpdate();
				System.out.println("[Log] Sucesso!");
			} catch (SQLException u) {
				throw new RuntimeException(u);
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
}
