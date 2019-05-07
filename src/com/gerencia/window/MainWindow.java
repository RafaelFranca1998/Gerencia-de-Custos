package com.gerencia.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gerencia.connection.DAO;
import com.gerencia.connection.DAO.OnCompleteListener;
import com.gerencia.connection.DataSource;
import com.gerencia.core.Item;
import com.gerencia.core.Month;
import com.gerencia.core.Year;
import com.gerencia.window.AddMonth.OnAddCompleteListener;

import java.awt.Choice;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class MainWindow {

	private JFrame main_frame;
	private JTable tableItems;
	private JTable tableValues;

	private Choice choiceYear;
	private Choice choiceMonth;

	private ArrayList<Year> listYear;
	private ArrayList<Month> listMonth;

	private int idMonth = 0;
	private int idYear = 0;
	private int debit = 0;
	
	private Month selectedMonth;
	

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		main_frame = new JFrame();
		main_frame.setResizable(false);
		main_frame.setBounds(100, 100, 1000, 700);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_frame.getContentPane().setLayout(null);

		JButton btnAdicionarMs = new JButton("Adicionar M\u00EAs");
		btnAdicionarMs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddMonth month = new AddMonth();
				month.setOnCompleteListener(new OnAddCompleteListener() {
					
					@Override
					public void onComplete() {
						updateAll();
					}
				});
			}
		});
		btnAdicionarMs.setBounds(815, 612, 122, 23);
		main_frame.getContentPane().add(btnAdicionarMs);

		JScrollPane scrollItems = new JScrollPane();
		scrollItems.setBounds(300, 60, 637, 258);
		main_frame.getContentPane().add(scrollItems);

		tableItems = new JTable();
		tableItems.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, ""},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Item", "Parcelas", "Valor", "Porcentagem"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableItems.getColumnModel().getColumn(0).setResizable(false);
		tableItems.getColumnModel().getColumn(1).setResizable(false);
		tableItems.getColumnModel().getColumn(2).setResizable(false);
		tableItems.getColumnModel().getColumn(3).setResizable(false);
		tableItems.getColumnModel().getColumn(4).setResizable(false);
		scrollItems.setViewportView(tableItems);

		JScrollPane scrollValues = new JScrollPane();
		scrollValues.setBounds(300, 329, 637, 92);
		main_frame.getContentPane().add(scrollValues);

		tableValues = new JTable();
		scrollValues.setViewportView(tableValues);
		tableValues.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Recebido", "Anterior", "D\u00E9bito", "Restou (%)", "Restou (R$)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableValues.getColumnModel().getColumn(0).setResizable(false);

		JLabel lblYear = new JLabel("Ano");
		lblYear.setBounds(26, 67, 46, 14);
		main_frame.getContentPane().add(lblYear);

		JLabel lblMonth = new JLabel("M\u00EAs");
		lblMonth.setBounds(26, 112, 46, 14);
		main_frame.getContentPane().add(lblMonth);

		JButton btnConectar = new JButton("Editar Item");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// FIXME
					
					
					// XXX
					// Datasource.FecharConexao();
					// TODO
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		btnConectar.setBounds(160, 292, 130, 23);
		main_frame.getContentPane().add(btnConectar);

		choiceMonth = new Choice();
		choiceMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				getSelectedMonth();
			}
		});
		choiceMonth.setBounds(82, 112, 100, 20);

		main_frame.getContentPane().add(choiceMonth);

		choiceYear = new Choice();
		choiceYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				System.out.println("[Log]FOI");
				getSelectedYear();
			}
		});

		choiceYear.setBounds(82, 67, 100, 20);
		main_frame.getContentPane().add(choiceYear);
		
		JButton btnAddItem = new JButton("Adicionar Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idMonth != 0) {
					new AddItemWindow(idMonth);					
				} else {
					JOptionPane.showMessageDialog(scrollItems, "Selecionar Mês.", "Mês Não Selecionado", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAddItem.setBounds(160, 258, 130, 23);
		main_frame.getContentPane().add(btnAddItem);
		main_frame.setVisible(true);

		updateAll();
	}

	private void fillChoicesYear() {
		DAO dao = new DAO();
		listYear = dao.listYear();
		choiceYear.removeAll();
		choiceYear.add("Selecionar");
		for (int i = 0; i < listYear.size(); i++) {
			choiceYear.add(Integer.toString(listYear.get(i).getYear()));
		}
		dao.setOnCompleteListener(new OnCompleteListener() {
			
			@Override
			public void onComplete() {
				fillChoicesMonth();
			}
		});
		
	}

	private void fillChoicesMonth() {
		DAO dao =  new DAO();
		listMonth = dao.listMonth(idYear);
		choiceMonth.removeAll();
		choiceMonth.add("Selecionar");
		for (int i = 0; i < listMonth.size(); i++) {
			choiceMonth.add(listMonth.get(i).getMonth());
		}
		dao.setOnCompleteListener(new OnCompleteListener() {
			
			@Override
			public void onComplete() {
				getSelectedMonth();				
			}
		});
	}

	private void getSelectedYear() {
		int compare;
		try {
			compare = Integer.parseInt(choiceYear.getSelectedItem());
		} catch (Exception e) {
			compare = Integer.parseInt(choiceYear.getItem(0));
		}
		
		for (int i = 0; i < listYear.size(); i++) {
			if (listYear.get(i).getYear() == compare) {
				idYear = listYear.get(i).getIdYear();
				fillChoicesMonth();
				break;
			}
		}
	}
	private void getSelectedMonth() {
		System.out.println("[Log]getSelectedMonth");
		String compare = choiceMonth.getSelectedItem();
		for (int i = 0; i < listMonth.size(); i++) {
			if (listMonth.get(i).getMonth() == compare) {
				selectedMonth = listMonth.get(i);
				idMonth = listMonth.get(i).getIdMonth();
				fillTable();
				fillTableInfo();
				break;
			}
		}
	}

	private void fillTable() {
		System.out.println("[Log]FillTable");
		DAO dao =  new DAO();
		ArrayList<Item> list = dao.listItem(idMonth);
		
		DefaultTableModel modelo = (DefaultTableModel) tableItems.getModel();
		debit = 0;
		modelo.setNumRows(0);
		for (Item s : list) {
			debit =+ debit-s.getValue();
			
			double a = s.getValue();
			double b = selectedMonth.getReceived();
			int percentual = (int) ((a/b)*100);
			System.out.println("Percentual"+percentual);
			
			
			modelo.addRow(new Object[] { s.getId(), s.getName(),s.getPlots()+"x", s.getValue() + " R$",percentual+"%" });
		}
		
	}
	private void fillTableInfo() {		
		DefaultTableModel modelo = (DefaultTableModel) tableValues.getModel();
		modelo.setNumRows(0);
		for (Month m : listMonth) {
			if (m.getMonth().equals(choiceMonth.getSelectedItem())) {
				int r = debit +m.getReceived();
				
				double a = r;
				double b = m.getReceived();
				int percentual = (int) ((a/b)*100);
				System.out.println("Percentual"+percentual);
				
				modelo.addRow(new Object[] { m.getReceived()+ " R$",m.getPrervious()+ " R$", debit + " R$",percentual+"%",r });				
			}
		}
		
	}
	
	private void updateAll() {
		fillChoicesYear();
	}
}
