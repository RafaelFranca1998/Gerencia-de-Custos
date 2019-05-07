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

	private DataSource ds;

	public DAO() {
		ds = new DataSource();
		
		setOnCompleteListener(new OnCompleteListener() {
			
			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	// -------------------------------------------------------------------
	/**
	 * Metodo que retorna um Arraylist contendo os dados da tabela no Banco de
	 * Dados.
	 * 
	 * @return Lista com ID, Nome, Tipo, endereco, arquivo e nome do arquivo.
	 */
	public ArrayList<Item> listItems() {
		try {
			PreparedStatement ps = ds.getConexao().prepareStatement("SELECT * FROM items");
			ResultSet rs = ps.executeQuery();
			ArrayList<Item> Lista = new ArrayList<Item>();
			while (rs.next()) {
				Item item = new Item();
				item.setName(rs.getString("item_name"));
				item.setValue(rs.getInt("value"));
				Lista.add(item);
			}
			return Lista;
		} catch (SQLException e) {
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
		} finally {
			ds.fecharConexao();
		}
		return null;
	}
	

	public ArrayList<Year> listYear() {
		ArrayList<Year> list = new ArrayList<Year>();
		DataSource ds = new DataSource();
		PreparedStatement ps;
		try {
			ps = ds.getConexao().prepareStatement("SELECT * FROM year ORDER BY year ASC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Year year = new Year();
				year.setYear(rs.getInt("year"));
				year.setIdYear(rs.getInt("idyear"));
				list.add(year);
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			onCompleteListener.onComplete();
		}
		return list;
	}

	public ArrayList<Month> listMonth(int idYear) {
		ArrayList<Month> list = new ArrayList<>();
		DataSource ds = new DataSource();
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = ds.getConexao().prepareStatement("SELECT * FROM month where year_idyear = " + idYear);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs != null) {
					Month month = new Month();
					month.setIdMonth(rs.getInt("idmonth"));
					month.setMonth(rs.getString("month"));
					month.setReceived(rs.getInt("received"));
					month.setPrervious(rs.getInt("previous"));
					month.setIdYear(rs.getInt("year_idyear"));
					list.add(month);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onCompleteListener.onComplete();
		}
		return list;

	}
	public ArrayList<Month> listMonth(String year) {
		ArrayList<Month> list = new ArrayList<>();
		DataSource ds = new DataSource();
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = ds.getConexao().prepareStatement("SELECT * FROM month where year_idyear = (SELECT idyear FROM year WHERE year =" + year+")");
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs != null) {
					Month month = new Month();
					month.setIdMonth(rs.getInt("idmonth"));
					month.setMonth(rs.getString("month"));
					month.setReceived(rs.getInt("received"));
					month.setPrervious(rs.getInt("previous"));
					month.setIdYear(rs.getInt("year_idyear"));
					list.add(month);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onCompleteListener.onComplete();
		}
		return list;
		
	}

	public ArrayList<Item> listItem(int idMonth) {
		DataSource ds = new DataSource();
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Item> items = new ArrayList<>();
		try {
			ps = ds.getConexao().prepareStatement("SELECT * FROM items where month_idmonth = " + idMonth);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs != null) {
					Item item = new Item();
					item.setId(rs.getInt("iditems"));
					item.setIdMonth(rs.getInt("month_idmonth"));
					item.setName(rs.getString("item_name"));
					item.setValue(rs.getInt("value"));
					item.setPlots(rs.getInt("plots"));
					items.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onCompleteListener.onComplete();
		}
		return items;

	}
	
	
	public void resetId() {
		try {
			PreparedStatement ps = ds.getConexao().prepareStatement("ALTER TABLE items AUTO_INCREMENT = 0");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("[ERRO!] Erro de Reset: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("[ERRO!] ERRO GERAL: " + e.getMessage());
		} 
	}
	
	// -------------------------------------------------------------

	/**
	 * Insere dados no banco de dados.
	 * 
	 * @param U
	 *            os dados do usuário.
	 */
	public void create(Month month, Year year, ArrayList<Item> item) {
		Connection con = ds.getConexao();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			if (checkYear(year.getYear())) {
				PreparedStatement psYear = con.prepareStatement("INSERT INTO year (year) VALUES (?);");
				System.out.println("Ano" + year.getYear());
				psYear.setInt(1, year.getYear());
				psYear.executeUpdate();
			}
			ps = ds.getConexao().prepareStatement("SELECT idyear FROM year where year =" + year.getYear());
			rs = ps.executeQuery();
			if (rs != null && rs.next()) {
				month.setIdYear(rs.getInt("idyear"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (checkMonth(month.getMonth(),year.getIdYear())) {
				PreparedStatement psMonth = con.prepareStatement(
						"INSERT INTO month(month,received,previous,year_idyear) VALUES (?,?,?,?);");
				System.out.println("mes" + month.getMonth());
				psMonth.setString(1, month.getMonth());
				System.out.println("Recebido" + month.getReceived());
				psMonth.setInt(2, month.getReceived());
				System.out.println("anterior" + month.getPrervious());
				psMonth.setInt(3, month.getPrervious());
				System.out.println("id Ano" + month.getIdYear());
				psMonth.setInt(4, month.getIdYear());
				psMonth.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resetId();
		for (Item i : item) {
			try {
				ps = ds.getConexao()
						.prepareStatement("SELECT idmonth FROM month where (year_idyear = ? AND month = ?)");
				ps.setInt(1, month.getIdYear());
				ps.setString(2, month.getMonth());
				rs = ps.executeQuery();
				if (rs != null && rs.next()) {
					i.setIdMonth(rs.getInt("idmonth"));
				}
				
				PreparedStatement psItem = con.prepareStatement(
						"INSERT INTO items(item_name,value,month_idmonth,plots) VALUES (?,?,?,?);");
				System.out.println("[Log] nome do item" + i.getName());
				psItem.setString(1, i.getName());
				System.out.println("[Log] Valor do item" + i.getValue());
				psItem.setInt(2, i.getValue());
				System.out.println("[Log] id do mes" + i.getIdMonth());
				psItem.setInt(3, i.getIdMonth());
				psItem.setInt(4, i.getPlots());
				psItem.executeUpdate();
				System.out.println("[Log] Sucesso!");
			} catch (SQLException u) {
				u.printStackTrace();
				throw new RuntimeException(u);
			}
		}
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} finally {
			System.out.println("Complete");
			onCompleteListener.onComplete();
		}

	}

	public void addItem( ArrayList<Item> itemList) {
		Connection con = ds.getConexao();
		for (Item i : itemList) {
			try {
				PreparedStatement psItem = con.prepareStatement(
						"INSERT INTO items(item_name,value,month_idmonth,plots) VALUES (?,?,?,?);");
				System.out.println("[Log] nome do item" + i.getName());
				psItem.setString(1, i.getName());
				System.out.println("[Log] Valor do item" + i.getValue());
				psItem.setInt(2, i.getValue());
				System.out.println("[Log] id do mes" + i.getIdMonth());
				psItem.setInt(3, i.getIdMonth());
				psItem.setInt(4, i.getPlots());
				psItem.executeUpdate();
				System.out.println("[Log] Sucesso!");
			} catch (SQLException u) {
				u.printStackTrace();
				throw new RuntimeException(u);
			}
		}
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("[ERRO!] Erro na Listagem " + e.getMessage());
		} finally {
			System.out.println("Complete");
			onCompleteListener.onComplete();
		}

	}
	
	public void UpdateItem(Item item) {
		Connection con = ds.getConexao();
		PreparedStatement stmp = null;
		try {
			String sql = "UPDATE items SET item_name = ?, value = ?, month_idmonth = ?, plots = ? WHERE id = ?";
			stmp = con.prepareStatement(sql);
			  stmp.setString(1, item.getName()); 
			  stmp.setInt(2, item.getValue());
			  stmp.setInt(3, item.getIdMonth()); 
			  stmp.setInt(5, item.getPlots());
			  stmp.setInt(6, item.getId());
			  stmp.executeUpdate();
			System.out.println("[Log] Update Sucess!");
		} catch (SQLException u) {
			throw new RuntimeException(u);
		} finally {
			try {
				ds.getConexao();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("[ERRO!] Update Error! " + e.getMessage());
			}
			onCompleteListener.onComplete();
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
		Connection con = ds.getConexao();
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
			} finally {
				onCompleteListener.onComplete();
			}
		}
	}

	private boolean checkYear(int year) {
		Connection cnnct = null;
		PreparedStatement pStmnt = null;
		try {
			cnnct = ds.getConexao();
			String preQueryStatement = "SELECT * FROM year WHERE year = ?;";
			pStmnt = cnnct.prepareStatement(preQueryStatement);
			pStmnt.setLong(1, year);
			ResultSet rs = pStmnt.executeQuery();
			if (!rs.next()) {
				return true;
			} else {
				System.out.println("[Log] year already in database");
				return false;
			}

		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} finally {
			onCompleteListener.onComplete();
		}
		return false;
	}

	private boolean checkMonth(String month,int year) {
		Connection cnnct = null;
		PreparedStatement pStmnt = null;
		try {
			cnnct = ds.getConexao();
			String preQueryStatement = "SELECT * FROM month WHERE month = ? AND year_idyear = ?;";
			pStmnt = cnnct.prepareStatement(preQueryStatement);
			pStmnt.setString(1, month);
			pStmnt.setInt(2, year);
			ResultSet rs = pStmnt.executeQuery();
			if (!rs.next()) {
				System.out.println("[Log]Created new Month");
				return true;
			} else {
				System.out.println("[Log]Month already in database");
				return false;
			}

		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} finally {
			onCompleteListener.onComplete();
		}
		return false;
	}

	// #####################Listener################################################
	private OnCompleteListener onCompleteListener;

	/**
	 * Listener da classe.
	 * 
	 * @param onCompleteListener
	 */
	public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
		this.onCompleteListener = onCompleteListener; // listener da classe.
	}

	/**
	 * interface a ser inplementada toda vez que o setOnCompleteListener for usado.
	 * 
	 * @author Rafael
	 *
	 */
	public interface OnCompleteListener {// interface a ser executada.
		public void onComplete();
	}
}
