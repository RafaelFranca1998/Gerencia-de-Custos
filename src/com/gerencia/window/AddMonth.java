package com.gerencia.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gerencia.connection.DAO;
import com.gerencia.core.Item;
import com.gerencia.core.Month;
import com.gerencia.core.Year;

import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AddMonth {

	private JFrame add_Month_Frame;
	private JTable tableAddMonth;
	private JTextField txtFieldReceived;
	private JTextField textFieldPrevious;
	private JTextField txtAddName;
	private JTextField txtAddValue;
	
	private Item item;
	private Month month;
	private Year year;

	/**
	 * Create the application.
	 */
	public AddMonth() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					item = new Item();
					month = new Month();
					year = new Year();
					initialize();
					add_Month_Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		add_Month_Frame = new JFrame();
		add_Month_Frame.setBounds(100, 100, 834, 475);
		add_Month_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add_Month_Frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 88, 396, 154);
		add_Month_Frame.getContentPane().add(scrollPane);
		
		tableAddMonth = new JTable();
		tableAddMonth.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Nome do Item", "Valor"
			}
		));
		scrollPane.setViewportView(tableAddMonth);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DAO dao =  new DAO();
				item.setName("Pascoa");
				item.setPercentage(10);
				item.setValue(300);
				month.setMonth("Agosto");
				month.setPrervious(200);
				month.setReceived(400);
				year.setYear(2019);
				dao.create(month, year, item);
			}
		});
		btnSave.setBounds(583, 387, 89, 23);
		add_Month_Frame.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(700, 387, 89, 23);
		add_Month_Frame.getContentPane().add(btnCancel);
		
		Choice choiceYear = new Choice();
		choiceYear.setBounds(550, 110, 99, 20);
		add_Month_Frame.getContentPane().add(choiceYear);
		
		Choice choiceMonth = new Choice();
		choiceMonth.setBounds(550, 146, 99, 20);
		add_Month_Frame.getContentPane().add(choiceMonth);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(550, 95, 46, 14);
		add_Month_Frame.getContentPane().add(lblAno);
		
		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(550, 130, 46, 14);
		add_Month_Frame.getContentPane().add(lblMs);
		
		JLabel lblValorRecebido = new JLabel("Valor Recebido");
		lblValorRecebido.setBounds(550, 167, 99, 14);
		add_Month_Frame.getContentPane().add(lblValorRecebido);
		
		JLabel lblValorAnterior = new JLabel("Valor Anterior");
		lblValorAnterior.setBounds(550, 205, 99, 14);
		add_Month_Frame.getContentPane().add(lblValorAnterior);
		
		txtFieldReceived = new JTextField();
		txtFieldReceived.setBounds(550, 182, 99, 20);
		add_Month_Frame.getContentPane().add(txtFieldReceived);
		txtFieldReceived.setColumns(10);
		
		textFieldPrevious = new JTextField();
		textFieldPrevious.setBounds(550, 220, 99, 20);
		add_Month_Frame.getContentPane().add(textFieldPrevious);
		textFieldPrevious.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(522, 87, 267, 14);
		add_Month_Frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(522, 245, 267, 2);
		add_Month_Frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(522, 87, 1, 160);
		add_Month_Frame.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(788, 88, 1, 159);
		add_Month_Frame.getContentPane().add(separator_3);
		
		JLabel lblShowReceived = new JLabel("");
		lblShowReceived.setBounds(659, 185, 89, 14);
		add_Month_Frame.getContentPane().add(lblShowReceived);
		
		JLabel lblShowPrevious = new JLabel("");
		lblShowPrevious.setBounds(659, 223, 89, 14);
		add_Month_Frame.getContentPane().add(lblShowPrevious);
		
		txtAddName = new JTextField();
		txtAddName.setBounds(57, 283, 166, 20);
		add_Month_Frame.getContentPane().add(txtAddName);
		txtAddName.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(57, 258, 68, 14);
		add_Month_Frame.getContentPane().add(lblNome);
		
		txtAddValue = new JTextField();
		txtAddValue.setBounds(245, 283, 208, 20);
		add_Month_Frame.getContentPane().add(txtAddValue);
		txtAddValue.setColumns(10);
		
		JLabel lblValorr = new JLabel("Valor (R$)");
		lblValorr.setBounds(245, 258, 99, 14);
		add_Month_Frame.getContentPane().add(lblValorr);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(193, 325, 89, 23);
		add_Month_Frame.getContentPane().add(btnAdd);
		
		JLabel lblItensNaLista = new JLabel("Itens na Lista");
		lblItensNaLista.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblItensNaLista.setBounds(69, 48, 132, 38);
		add_Month_Frame.getContentPane().add(lblItensNaLista);
		
		JLabel lblDadosDoMs = new JLabel("Dados do M\u00EAs");
		lblDadosDoMs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDadosDoMs.setBounds(522, 46, 132, 38);
		add_Month_Frame.getContentPane().add(lblDadosDoMs);
	}

	
	private void addMonth() {
		
	}
}
