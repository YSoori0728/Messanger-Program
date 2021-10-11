package com.pjt.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;


//로그인 후 홈 패널(미완성)
public class homeUI extends JPanel {
	//전역변수로 설정을 해주도록 한다.
	public static DefaultListModel mylist = new DefaultListModel();
	/**
	 * Create the panel.
	 */
	MainFrame frmMain;
	JPanel thispan = this;
	
	//리스트에 유저정보를 나타내는 메소드(매우 미완성)
	public static void add() {
		try {
			
			BufferedReader input = new BufferedReader(new InputStreamReader(userLogin.socket.getInputStream()));
			mylist.addElement(input.readLine());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public homeUI(MainFrame frmMain) {
		this.frmMain = frmMain;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 380, 101);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("img");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 10, 81, 81);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setBounds(105, 10, 120, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uC815\uBCF4 ~~~~~~~~");
		lblNewLabel_2_1.setBounds(105, 61, 120, 30);
		panel.add(lblNewLabel_2_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 111, 380, 43);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBorder(UIManager.getBorder("TextField.border"));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 13));
		btnNewButton.setBounds(335, 11, 33, 33);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\uCE5C\uAD6C\uBAA9\uB85D");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 10, 100, 33);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 164, 380, 426);
		add(panel_2);
		panel_2.setLayout(null);
		
		
		JList list = new JList(mylist);
		list.setBackground(Color.WHITE);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBounds(12, 0, 356, 416);
		
		JScrollPane p = new JScrollPane(list);
		p.setBounds(12, 0, 356, 416);
		panel_2.add(p);
		
	}
}