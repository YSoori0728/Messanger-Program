package com.pjt.UI;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

//ä�� �г�(�ſ� �̿ϼ�)
//�Ƹ� ���ο� �������� �������ִ� ������� �ٲ� ����
//�ذ��ؾ��� ����, �� ģ������ ä��â�� ��� ��Ÿ�� �� ���� ���ΰ�..?
public class chattingUI extends JPanel {

	/**
	 * Create the panel.
	 */
	MainFrame frmMain;
	JPanel thispan = this;
	private JTextField textField;
	public chattingUI() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 380, 510);
		add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(12, 10, 356, 500);
		panel.add(textPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 520, 380, 70);
		add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 10, 306, 50);
		panel_1.add(textField);
		
		JButton btnNewButton = new JButton("\uC804\uC1A1");
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setBounds(330, 10, 38, 24);
		panel_1.add(btnNewButton);

	}
}