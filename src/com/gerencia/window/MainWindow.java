package com.gerencia.window;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gerencia.connection.DataSource;


public class MainWindow {

	private JFrame main_frame;
	private JTable table_1;
	private JTable table_2;

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
				new AddMonth();
			}
		});
		btnAdicionarMs.setBounds(815, 612, 122, 23);
		main_frame.getContentPane().add(btnAdicionarMs);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(300, 60, 637, 258);
		main_frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Item", "", "Valor", "Porcentagem"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(300, 329, 637, 92);
		main_frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Recebido", "Anterior", "D\u00E9bito", "Restou (%)", "Restou (R$)"
			}
		));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(82, 64, 100, 20);
		main_frame.getContentPane().add(comboBox);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(26, 67, 46, 14);
		main_frame.getContentPane().add(lblAno);
		
		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(26, 112, 46, 14);
		main_frame.getContentPane().add(lblMs);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(82, 109, 100, 20);
		main_frame.getContentPane().add(comboBox_1);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {		
				//FIXME 
						System.out.println("Clicou");
						DataSource ds =  new DataSource();
						ds.getConexao();
						//XXX
						//Datasource.FecharConexao();
						//TODO
				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		btnConectar.setBounds(121, 496, 89, 23);
		main_frame.getContentPane().add(btnConectar);
		main_frame.setVisible(true);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
