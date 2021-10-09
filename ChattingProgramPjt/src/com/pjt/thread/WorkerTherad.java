package com.pjt.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class WorkerTherad extends Thread{
	
	Queue<Socket> access = new LinkedList<Socket>();
	
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
