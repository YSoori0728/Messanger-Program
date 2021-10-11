package com.pjt.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ChatServer {
	
	//사용할 임의의 포트 지정
	static final int portNum = 9090;
	
	//서버에 접근이 이뤄지면 소켓을 순서대로 저장할 큐
	static Queue<Socket> access = new LinkedList<Socket>();
	
	public static void main(String[] args) {
		
		//서버소켓
		ServerSocket sServer = null;
		System.out.println("Server Start!\nPortNumber : "+portNum+"\n");
		line();
		
		//Server Running
		try {
			sServer = new ServerSocket(portNum);
			
			//반복 : 반복문을 사용하지 않을 경우, 한명의 접속자만 받을 수 있다.
			//그래서 반복을 통해 계속 접속자를 받아주어야 한다.
			while(true) {
				
				//접속자를 받을 소켓 accept()를 계속 반복한다.
				Socket socket = sServer.accept();
				
				//접속자가 있을 경우, 큐에 순서대로 집어넣는다.
				//왜냐하면 멀티스레드를 구현해야 하기 때문이다.
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
