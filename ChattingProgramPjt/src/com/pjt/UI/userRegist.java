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

//ȸ������ �г�
public class userRegist extends JPanel {
	private JTextField textField;
	private JTextField uIdFeild;
	private JPasswordField uPwField;
	private JPasswordField uPwCheckField;
	private JTextField uNameField;
	private JTextField uTelField;
	
	/**
	 * Create the panel.
	 */
	
	MainFrame frmMain;
	JPanel thispan = this;
	
	public userRegist(MainFrame frmMain) {
		this.frmMain = frmMain;
		
		JPanel logoPan = new JPanel();
		logoPan.setBounds(0, 0, 380, 140);
		logoPan.setLayout(null);
		
		textField = new JTextField();
		textField.setText("\uD68C\uC6D0\uAC00\uC785");
		textField.setSelectionColor(Color.BLACK);
		textField.setSelectedTextColor(Color.WHITE);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("����", Font.PLAIN, 30));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.RED);
		textField.setBounds(99, 49, 174, 68);
		logoPan.add(textField);
		
		JPanel regPan = new JPanel();
		regPan.setBounds(0, 150, 380, 440);
		regPan.setLayout(null);
		
		JLabel uId = new JLabel("ID");
		uId.setBounds(23, 60, 77, 15);
		regPan.add(uId);
		
		uIdFeild = new JTextField();
		uId.setLabelFor(uIdFeild);
		uIdFeild.setColumns(10);
		uIdFeild.setBounds(138, 60, 223, 21);
		regPan.add(uIdFeild);
		
		JLabel uPw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		uPw.setBounds(23, 121, 77, 15);
		regPan.add(uPw);
		
		uPwField = new JPasswordField();
		uPw.setLabelFor(uPwField);
		uPwField.setBounds(138, 121, 223, 21);
		regPan.add(uPwField);
		
		JLabel uPwCheck = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		uPwCheck.setBounds(23, 182, 76, 15);
		regPan.add(uPwCheck);
		
		uPwCheckField = new JPasswordField();
		uPwCheck.setLabelFor(uPwCheckField);
		uPwCheckField.setBounds(137, 182, 223, 21);
		regPan.add(uPwCheckField);
		
		JLabel uName = new JLabel("\uC774\uB984");
		uName.setBounds(22, 241, 77, 15);
		regPan.add(uName);
		
		uNameField = new JTextField();
		uName.setLabelFor(uNameField);
		uNameField.setColumns(10);
		uNameField.setBounds(137, 241, 224, 21);
		regPan.add(uNameField);
		
		JLabel uTel = new JLabel("\uC804\uD654\uBC88\uD638");
		uTel.setBounds(23, 303, 77, 15);
		regPan.add(uTel);
		
		uTelField = new JTextField();
		uTel.setLabelFor(uTelField);
		uTelField.setColumns(10);
		uTelField.setBounds(138, 303, 224, 21);
		regPan.add(uTelField);
		
		JButton loginPageBtn = new JButton("\uCDE8\uC18C");
		loginPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.SetLog(thispan);
			}
		});
		loginPageBtn.setForeground(Color.WHITE);
		loginPageBtn.setFont(new Font("����", Font.PLAIN, 15));
		loginPageBtn.setBorder(UIManager.getBorder("TextField.border"));
		loginPageBtn.setBackground(Color.RED);
		loginPageBtn.setBounds(222, 376, 100, 25);
		regPan.add(loginPageBtn);
		
		JButton registBtn = new JButton("\uD68C\uC6D0 \uB4F1\uB85D");
		//ȸ������ �׼�
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket socket = null;
				BufferedReader input;
				PrintWriter output;
				
				try {
					//��ư�� ������ ������ Ȱ��ȭ�ϰ� ������ ������ �õ��Ѵ�. ��Ʈ��ȣ ���� IP�ּҴ� ������ IP�ּҷ� �ٲ�����Ѵ�.
					socket = new Socket("192.168.0.43", 9090);
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					//ȸ�������� ��Ÿ���� ��ȣ�δ� 2�� ���� �������ش�.
					output.println("regist");
					
					//ȸ�����Կ� �ʿ��� ����(id, password, name, tel)���� ������ ������ �غ� �Ѵ�.(141~145)
					String id = uIdFeild.getText();
					String pw = String.valueOf(uPwField.getPassword());
					String pwCheck = String.valueOf(uPwCheckField.getPassword());
					String name = uName.getText();
					String tel = uTel.getText();
					
					//���� �� ������ ��������� ������ Ȯ��, ��й�ȣ�� ��й�ȣ üũ�� ���� ������ Ȯ�� �� ������ �������� �����Ѵ�.(148~153)
					if(pw.equals(pwCheck)&&!id.equals("")&&!name.equals("")&&!pw.equals("")&&!tel.equals("")) {
						output.println(id);
						output.println(pw);
						output.println(name);
						output.println(tel);
						output.flush();
						
						//������ ���� ������ ȸ�������� �������� �Ұ������� ȸ���� �ް�, ȸ�ſ� ���� �۾��� �ǽ���.(157~165)
						//1�� ȸ���� ������ ȸ�������� ������ ������ �α��� �гη� �̵��ϰ� ������ �ݾ��ش�.
						String response = input.readLine();
						System.out.println(response);
						if(response.equals("1")) {
							System.out.println("Success");
							frmMain.SetLog(thispan);
						}else {
							System.out.println("Failed");
						}
						output.close();
					}
					
				}catch(IOException e2) {
					e2.printStackTrace();
				}
				finally {
					try {
						socket.close();
						System.out.println("socket closed");
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
			}
		});
		
		registBtn.setForeground(Color.WHITE);
		registBtn.setFont(new Font("����", Font.PLAIN, 15));
		registBtn.setBorder(UIManager.getBorder("TextField.border"));
		registBtn.setBackground(Color.RED);
		registBtn.setBounds(62, 376, 100, 25);
		regPan.add(registBtn);
		setLayout(null);
		add(logoPan);
		add(regPan);

	}
}