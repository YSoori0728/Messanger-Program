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
import java.awt.TextArea;

import javax.swing.border.EtchedBorder;

import com.pjt.thread.ClientThread;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//대화창 다이어로그
public class chat extends JDialog {
	private JTextField textField;
	static JTextArea textArea;
	BufferedReader input;
	PrintWriter output;
	String receiveMember;

	/**
	 * Create the dialog.
	 */
	public chat(JFrame frame, String title) {
		super(frame, title);
		setResizable(false);

		setModal(false);
		setBounds(500, 100, 396, 629);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 380, 510);
		getContentPane().add(panel);

		JScrollPane scrollPane = new JScrollPane(textField);
		scrollPane.setBounds(12, 10, 356, 500);
		panel.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

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
					output = new PrintWriter(new OutputStreamWriter(userLogin.goSocket.getOutputStream()));
					output.println("msg");
					output.println(homeUI.id.getText());
					output.println(receiveMember);
					String send = textField.getText();
					output.println(homeUI.id.getText() + " : " + send);
					textArea.append("나 : " + send + "\n");
					textField.setText("");
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
