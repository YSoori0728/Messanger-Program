package com.pjt.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Queue;

import com.pjt.DAO.MemberDAO;
import com.pjt.DTO.Member;

public class WorkerTherad extends Thread{
	
	Queue<Socket> access;
	MemberDAO mDAO = new MemberDAO();
	
	//���߿� ������ ����� ��Ÿ���� �ؽ���, ����� ������ �޾������� ������� �ٲ�����Ѵ�.(���� �Ϻ��� ���� �Ұ���.)
	static HashMap<String, Socket> userMap = new HashMap<String, Socket>();
	BufferedReader read;
	PrintWriter output;
	
	//������
	public WorkerTherad(Queue<Socket> access) {
		this.access = access;
	}
	
	//�����忡�� �� ��
	//1.�α���
	//ID,PW Ȯ���ϰ� �α��� �������� �������� ȸ��.
	//������ ���, Ŀ��Ʈ ����! ������ ��� Ŀ��Ʈ ���� ó�� â����.
	
	//2.ȸ�����
	//ID, PW, �̸�, ��ȭ��ȣ�� ����.
	//ID�ߺ�Ȯ�� �� ȸ����� OR �˸� ȸ��
	//
	@Override
	public void run() {
		while(true) {
			// ť�� ������� �ʴٸ� �α��� or ȸ������ ����
			if(!access.isEmpty()) {
				try {
					Socket socket = access.poll();
					
					read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					//� �۾��� �� �� process�� �Է¹���
					//1 : �α���, 2 : ȸ������
					String process = read.readLine();
					
					//�α���
					if(process.equals("1")) {
						//id, pw �� �Է��� �޾ƿ´�.
						String id = read.readLine();
						String pw = read.readLine();
						
						//id, pw�� ���� ��ġ�ϴ��� Ȯ�� �� �۾��� ó���ϰ� Ŭ���̾�Ʈ���Ե� ������ �����ش�.(61~73)
						//�α����� �������� ��� ������ userMap�� �־��־ Ȩ �гο��� ����Ʈ�� ��Ÿ���� ���ش�.
						int check = mDAO.check(id, pw);
						output.println(check);
						output.flush();
						if(check == 1) {
							System.out.println("�α��� ����!");
							userMap.put(id, socket);
							output.println(check);
							output.println(id);
							output.flush();
						}else {
							System.out.println("�α��� ����!");
						}
					}
					
					//ȸ������
					if(process.equals("2")) {
						//ȸ�����Կ� �ʿ��� ��� ������ �޾ƿ´�.
						String id = read.readLine();
						String pw = read.readLine();
						String name = read.readLine();
						String tel = read.readLine();
						
						//id �ߺ�Ȯ���� �ϰ� �ش� �۾��� ó���Ѵ�.(85~96)
						//���߿� DB���� ������ ���� ȸ������ ������ ��� ������ DB�� ������ �����־���Ѵ�.
						int check = mDAO.check(id);
						output.println(check);
						output.flush();
						if(check ==1) {
							System.out.println("ȸ������ ����!");
							MemberDAO.list.put(id, new Member(id, pw, name, tel));
						}else {
							System.out.println("ȸ������ ����!");
							System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
						}
						output.close();
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			//�����带 0.1�ʸ��� ����
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
