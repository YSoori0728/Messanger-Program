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
	//���� �α����г�, ȸ�������г�, �α��� �� Ȩ �г� ��ü
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

	// �α��� �І��� �����ϴ� �޼ҵ�
	public void SetLog(JPanel panCurr) {
		if (panCurr != null)
			contentPane.remove(panCurr);
		contentPane.add(ul, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	//ȸ������ �г��� �����ϴ� �޼ҵ�
	public void SetReg(JPanel panCurr) {
		if (panCurr != null)
			contentPane.remove(panCurr);
		contentPane.add(ur, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	//�α��� �� ���� �г��� �����ϴ� �޼ҵ�
	public void SetHome(JPanel panCurr) {
		if (panCurr != null)
			contentPane.remove(panCurr);
		contentPane.add(home, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
}