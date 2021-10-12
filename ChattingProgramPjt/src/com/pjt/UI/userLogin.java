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

//로그인 패널
public class userLogin extends JPanel {
	private JTextField logo;
	private JTextField idFeild;
	private JPasswordField uPwField;
	static String saveId;
	//전역변수로 설정해주어 다른 패널에서도 사용할 수 있도록 함.
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
		logo.setFont(new Font("굴림", Font.PLAIN, 30));
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
		uId.setFont(new Font("굴림", Font.BOLD, 18));
		uId.setBounds(12, 74, 77, 32);
		regPan.add(uId);

		idFeild = new JTextField();
		uId.setLabelFor(idFeild);
		idFeild.setColumns(10);
		idFeild.setBounds(145, 74, 223, 35);
		regPan.add(idFeild);

		JLabel uPw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		uPw.setHorizontalAlignment(SwingConstants.CENTER);
		uPw.setFont(new Font("굴림", Font.BOLD, 18));
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
		registPageBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		registPageBtn.setBorder(UIManager.getBorder("TextField.border"));
		registPageBtn.setBackground(Color.RED);
		registPageBtn.setBounds(218, 254, 100, 25);
		regPan.add(registPageBtn);
		
		JButton loginBtn = new JButton("\uB85C\uADF8\uC778");
		//로그인 액션
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BufferedReader input;
				PrintWriter output;
				
				try {
					//버튼을 누르면 소켓을 활성화해서 서버와 연결을 시도한다. 포트번호 앞의 IP주소는 서버의 IP주소로 바꿔줘야한다.
					socket = new Socket("127.0.0.1", 9090);
					goSocket = socket;
					//인풋을 통해 서버로부터 정보를 받아오고 아웃풋을 통해 서버에 정보를 전달한다.
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					//로그인을 나타내는 신호로 login을 먼저 전송해준다.
					output.println("login");
					
					//ID, Password 정보를 담아서 서버로 보내는 과정(120~124)
					String id = idFeild.getText();
					saveId = id; //접속할 때 사용한 나의 Id를 받아놓아줘야함. 자기 정보를 올리기 위해
					output.println(id);
					String pw = String.valueOf(uPwField.getPassword());
					output.println(pw);
					output.flush();
					
					//서버에 보낸 정보로 로그인이 가능한지 불가능한지 회신을 받고, 회신에 대한 작업을 실시함.(127~136)
					//1을 회신받으면 로그인이 성공했다는 신호. 따라서 홈화면으로 이동하도록 하고. 리스트 정보를 받아온다.(아직 동기화는 미적용)
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
		loginBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		loginBtn.setBorder(UIManager.getBorder("TextField.border"));
		loginBtn.setBackground(Color.RED);
		loginBtn.setBounds(58, 254, 100, 25);
		regPan.add(loginBtn);

	}

}