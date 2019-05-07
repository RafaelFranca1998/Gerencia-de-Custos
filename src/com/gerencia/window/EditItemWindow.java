package com.gerencia.window;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.gerencia.core.Item;

public class EditItemWindow {

	private JFrame frame;
	private JTable table;
	private JTextField txtName;
	private JTextField txtValue;
	private JTextField txtPlots;
	
	private Item item;

	/**
	 * Create the application.
	 */
	public EditItemWindow(Item item) {
		this.item = item;
		
		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 531, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("Itens na Lista");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(53, 11, 132, 38);
		frame.getContentPane().add(label);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(53, 274, 166, 20);
		frame.getContentPane().add(txtName);

		txtValue = new JTextField();
		txtValue.setColumns(10);
		txtValue.setBounds(251, 274, 208, 20);
		frame.getContentPane().add(txtValue);

		JLabel label_1 = new JLabel("Valor (R$)");
		label_1.setBounds(251, 259, 99, 14);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Nome");
		label_2.setBounds(53, 259, 68, 14);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("Parcelas");
		label_3.setBounds(53, 305, 68, 14);
		frame.getContentPane().add(label_3);

		txtPlots = new JTextField();
		txtPlots.setColumns(10);
		txtPlots.setBounds(53, 319, 166, 20);
		frame.getContentPane().add(txtPlots);

		JButton button = new JButton("Adicionar");
		button.setBounds(210, 374, 89, 23);
		frame.getContentPane().add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 60, 412, 175);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, },
				new String[] { "Item", "Valor" }));
		scrollPane.setViewportView(table);
	}
}
