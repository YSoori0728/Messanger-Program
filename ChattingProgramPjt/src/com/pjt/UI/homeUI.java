package com.pjt.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;


//로그인 후 홈 패널(미완성)
public class homeUI extends JPanel {
	//전역변수로 설정을 해주도록 한다.
	public static DefaultListModel mylist = new DefaultListModel();
	static JLabel name;
	static JLabel id;
	
	/**
	 * Create the panel.
	 */
	MainFrame frmMain;
	JPanel thispan = this;
	chat ct;
	
	//리스트에 유저정보를 나타내는 메소드
	//조금 보완함. 서버에서 보내준 유저목록 정보를 선별하여 나의 계정이면 내 정보창, 내 계정이 아니면 리스트에 업로드
	public static void add() {
		Thread th = new Thread() {
			public void run() {
				while(true) {
					try {
						BufferedReader input = new BufferedReader(new InputStreamReader(userLogin.socket.getInputStream()));
						String a = input.readLine();
						if(a.equals("listUpdate")) {
							mylist.removeAllElements();
							int size = Integer.parseInt(input.readLine());
							for(int i=0;i<size;i++) {
								String[] users = input.readLine().split("/");
								if(userLogin.saveId.equals(users[0])) {
									name.setText(users[1]);
									id.setText(users[0]);
								}else {
									mylist.addElement("이름 : "+users[1]+" / ID : "+users[0]);
								}
							}
							
						}else if(a.equals("msg")) {
							String message = input.readLine();
							System.out.println(message);
							chat.textArea.append(message + "\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
						try {
							userLogin.socket.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		};
		th.start();
		
	}
	
	public homeUI(MainFrame frmMain) {
		this.frmMain = frmMain;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 380, 101);
		add(panel);
		panel.setLayout(null);
		
		JLabel img = new JLabel("img");
		img.setBorder(new LineBorder(new Color(0, 0, 0)));
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(12, 10, 81, 81);
		panel.add(img);
		
		name = new JLabel("\uC774\uB984");
		name.setBounds(105, 10, 120, 30);
		panel.add(name);
		
		
		id = new JLabel("\uC815\uBCF4 ~~~~~~~~");
		id.setBounds(105, 61, 120, 30);
		panel.add(id);
		
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
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(Color.WHITE);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBounds(12, 0, 356, 416);
		
		openChatListener ocl = new openChatListener(list);
		list.addMouseListener(ocl);
		
		JScrollPane p = new JScrollPane(list);
		p.setBounds(12, 0, 356, 416);
		panel_2.add(p);
		
		frmMain.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				PrintWriter output;
				try {
					output = new PrintWriter(new OutputStreamWriter(userLogin.goSocket.getOutputStream()));
					String protocol = "quit\r\n";
					output.println(protocol);
					output.flush();
					System.out.println("클라이언트 종료");
					System.exit(0);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
	}

	//리스트항목을 눌렀을 때 대화창을 나타내는 기능 구현(아직 계정과의 연동 X)
	class openChatListener implements MouseListener{
		JList list;
		public openChatListener(JList list) {
			this.list = list;
		}
		
		
		public void mouseClicked(MouseEvent e) {
			//더블클릭했을 때 창 뜨도록 클릭수 체크
			if(e.getClickCount() ==2) {
				//아직 잘 모름. 일단 직사각형 박스의 크기를 구하고 그 범위를 벗어나면 해당 X하도록 하는 것 같음.
				Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex());
				if(r!=null && r.contains(e.getPoint())) {
					String sendId[] = list.getSelectedValue().toString().split(" : ");
					chat ct = new chat(frmMain, sendId[2]);
					ct.setVisible(true);
					ct.receiveMember = sendId[2];
					
					
					
				}
			}
		}

		public void mousePressed(MouseEvent e) {	
		}

		public void mouseReleased(MouseEvent e) {			
		}

		public void mouseEntered(MouseEvent e) {		
		}

		public void mouseExited(MouseEvent e) {
		}
		
	}
}