package com.pjt.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

//대화창 다이어로그
public class chat extends JDialog {
	private JTextField textField;
	BufferedReader input;
	PrintWriter output;
	/**
	 * Create the dialog.
	 */
	public chat(JFrame frame, String title) {
//		super(frame, title);
		
		setModal(false);
		setBounds(500, 100, 396, 629);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 380, 510);
		getContentPane().add(panel);
		
		JTextPane textPane = new JTextPane();
		textPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textPane.setBackground(SystemColor.inactiveCaption);
		textPane.setEditable(false);
		textPane.setBounds(12, 10, 356, 500);
		panel.add(textPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 520, 380, 70);
		getContentPane().add(panel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 10, 306, 50);
		panel_1.add(textField);
		
		JButton btnNewButton = new JButton("\uC804\uC1A1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					input = new BufferedReader(new InputStreamReader(userLogin.goSocket.getInputStream()));
					output = new PrintWriter(new OutputStreamWriter(userLogin.goSocket.getOutputStream()));
					output.println("123");
					output.flush();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setBounds(323, 10, 45, 31);
		panel_1.add(btnNewButton);
	}
}
