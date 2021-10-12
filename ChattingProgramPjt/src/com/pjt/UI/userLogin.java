package com.pjt.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

//�α��� �г�
public class userLogin extends JPanel {
	private JTextField logo;
	private JTextField idFeild;
	private JPasswordField uPwField;
	static String saveId;
	//���������� �������־� �ٸ� �гο����� ����� �� �ֵ��� ��.
	public static Socket socket = null;
	static Socket goSocket;

	/**
	 * Create the panel.
	 */

	MainFrame frmMain;
	JPanel thispan = this;

	public userLogin(MainFrame frmMain) {
		this.frmMain = frmMain;

		setLayout(null);

		JPanel logoPan = new JPanel();
		logoPan.setLayout(null);
		logoPan.setBounds(0, 0, 380, 237);
		add(logoPan);

		logo = new JTextField();
		logo.setText("String");
		logo.setSelectionColor(Color.BLACK);
		logo.setSelectedTextColor(Color.WHITE);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("����", Font.PLAIN, 30));
		logo.setEditable(false);
		logo.setColumns(10);
		logo.setBackground(Color.RED);
		logo.setBounds(80, 75, 213, 113);
		logoPan.add(logo);

		JPanel regPan = new JPanel();
		regPan.setLayout(null);
		regPan.setBounds(0, 247, 380, 343);
		add(regPan);

		JLabel uId = new JLabel("ID");
		uId.setHorizontalAlignment(SwingConstants.CENTER);
		uId.setFont(new Font("����", Font.BOLD, 18));
		uId.setBounds(12, 74, 77, 32);
		regPan.add(uId);

		idFeild = new JTextField();
		uId.setLabelFor(idFeild);
		idFeild.setColumns(10);
		idFeild.setBounds(145, 74, 223, 35);
		regPan.add(idFeild);

		JLabel uPw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		uPw.setHorizontalAlignment(SwingConstants.CENTER);
		uPw.setFont(new Font("����", Font.BOLD, 18));
		uPw.setBounds(12, 135, 77, 32);
		regPan.add(uPw);

		uPwField = new JPasswordField();
		uPw.setLabelFor(uPwField);
		uPwField.setBounds(145, 135, 223, 35);
		regPan.add(uPwField);

		JButton registPageBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		registPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.SetReg(thispan);
			}
		});
		registPageBtn.setForeground(Color.WHITE);
		registPageBtn.setFont(new Font("����", Font.PLAIN, 15));
		registPageBtn.setBorder(UIManager.getBorder("TextField.border"));
		registPageBtn.setBackground(Color.RED);
		registPageBtn.setBounds(218, 254, 100, 25);
		regPan.add(registPageBtn);
		
		JButton loginBtn = new JButton("\uB85C\uADF8\uC778");
		//�α��� �׼�
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BufferedReader input;
				PrintWriter output;
				
				try {
					//��ư�� ������ ������ Ȱ��ȭ�ؼ� ������ ������ �õ��Ѵ�. ��Ʈ��ȣ ���� IP�ּҴ� ������ IP�ּҷ� �ٲ�����Ѵ�.
					socket = new Socket("127.0.0.1", 9090);
					goSocket = socket;
					//��ǲ�� ���� �����κ��� ������ �޾ƿ��� �ƿ�ǲ�� ���� ������ ������ �����Ѵ�.
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					//�α����� ��Ÿ���� ��ȣ�� login�� ���� �������ش�.
					output.println("login");
					
					//ID, Password ������ ��Ƽ� ������ ������ ����(120~124)
					String id = idFeild.getText();
					saveId = id; //������ �� ����� ���� Id�� �޾Ƴ��������. �ڱ� ������ �ø��� ����
					output.println(id);
					String pw = String.valueOf(uPwField.getPassword());
					output.println(pw);
					output.flush();
					
					//������ ���� ������ �α����� �������� �Ұ������� ȸ���� �ް�, ȸ�ſ� ���� �۾��� �ǽ���.(127~136)
					//1�� ȸ�Ź����� �α����� �����ߴٴ� ��ȣ. ���� Ȩȭ������ �̵��ϵ��� �ϰ�. ����Ʈ ������ �޾ƿ´�.(���� ����ȭ�� ������)
					String response = input.readLine();
					System.out.println(response);

					if (response.equals("1")) { 
						homeUI.add();
						frmMain.SetHome(thispan);
						
						System.out.println("login Success");
					} else {
						System.out.println("login failed");
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setFont(new Font("����", Font.PLAIN, 15));
		loginBtn.setBorder(UIManager.getBorder("TextField.border"));
		loginBtn.setBackground(Color.RED);
		loginBtn.setBounds(58, 254, 100, 25);
		regPan.add(loginBtn);

	}

}