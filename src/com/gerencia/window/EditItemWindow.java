package com.gerencia.window;


import javax.swing.JFrame;

public class EditItemWindow {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public EditItemWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
