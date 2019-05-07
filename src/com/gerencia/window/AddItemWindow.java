package com.gerencia.window;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.gerencia.connection.DAO;
import com.gerencia.connection.DAO.OnCompleteListener;
import com.gerencia.core.Item;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddItemWindow {

	private JFrame frame;
	private JTable table;
	private JTextField txtName;
	private JTextField txtValue;
	private JTextField txtPlots;
	
	private ArrayList<Item> itemList;
	private Item item;
	private int idMonth;

	/**
	 * Create the application.
	 */
	public AddItemWindow(int idMonth) {
		this.idMonth = idMonth;
		
		itemList =  new ArrayList<>();
		
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addItem();
			}
		});
		btnAdd.setBounds(304, 318, 89, 23);
		frame.getContentPane().add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 60, 412, 175);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, },
				new String[] { "Item", "Valor" }));
		scrollPane.setViewportView(table);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO dao =  new DAO();
				dao.addItem(itemList);
				dao.setOnCompleteListener(new OnCompleteListener() {
					
					@Override
					public void onComplete() {
						frame.dispose();	
					}
				});
			}
		});
		btnSave.setBounds(206, 393, 89, 23);
		frame.getContentPane().add(btnSave);
	}
	
	private void addItem() {
		item = new Item();
		item.setIdMonth(idMonth);
		item.setName(txtName.getText());
		item.setValue(Integer.valueOf(txtValue.getText()));
		if (!txtPlots.getText().equals("")) {
			item.setPlots(Integer.parseInt(txtPlots.getText()));			
		}
		itemList.add(item);
		updateResultadoList();
		txtValue.setText("");
		txtName.setText("");
		txtPlots.setText("");
	}
	
	private void updateResultadoList() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		for (Item s : itemList) {
			modelo.addRow(new Object[] { s.getName(), s.getValue() + " R$" });
		}
	}
}
