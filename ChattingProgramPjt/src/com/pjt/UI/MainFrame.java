package com.pjt.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	private JPanel contentPane;
	static MainFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//각각 로그인패널, 회원가입패널, 로그인 후 홈 패널 객체
	userLogin ul;
	userRegist ur;
	homeUI home;
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		ul = new userLogin(this);
		ur = new userRegist(this);
		home = new homeUI(this);
		SetLog(null);
	}

	// 로그인 패넗을 세팅하는 메소드
	public void SetLog(JPanel panCurr) {
		if (panCurr != null)
			contentPane.remove(panCurr);
		contentPane.add(ul, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	//회원가입 패널을 세팅하는 메소드
	public void SetReg(JPanel panCurr) {
		if (panCurr != null)
			contentPane.remove(panCurr);
		contentPane.add(ur, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	//로그인 후 메인 패널을 세팅하는 메소드
	public void SetHome(JPanel panCurr) {
		if (panCurr != null)
			contentPane.remove(panCurr);
		contentPane.add(home, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
}