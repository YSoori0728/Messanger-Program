package com.pjt.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ChatServer {
	
	//����� ������ ��Ʈ ����
	static final int portNum = 9090;
	
	//������ ������ �̷����� ������ ������� ������ ť
	static Queue<Socket> access = new LinkedList<Socket>();
	
	public static void main(String[] args) {
		
		//��������
		ServerSocket sServer = null;
		System.out.println("Server Start!\nPortNumber : "+portNum+"\n");
		line();
		
		//Server Running
		try {
			sServer = new ServerSocket(portNum);
			
			//�ݺ� : �ݺ����� ������� ���� ���, �Ѹ��� �����ڸ� ���� �� �ִ�.
			//�׷��� �ݺ��� ���� ��� �����ڸ� �޾��־�� �Ѵ�.
			while(true) {
				
				//�����ڸ� ���� ���� accept()�� ��� �ݺ��Ѵ�.
				Socket socket = sServer.accept();
				
				//�����ڰ� ���� ���, ť�� ������� ����ִ´�.
				//�ֳ��ϸ� ��Ƽ�����带 �����ؾ� �ϱ� �����̴�.
				access.add(socket);
				
	
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
	
	public static void line() {
		System.out.println("=================================================\n");
	}
}
