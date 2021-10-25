package com.pjt.DTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.pjt.thread.WorkerThread;

public class Member extends Thread{
	private String uId;
	private String uPw;
	private String uName;
	private String uTel;
	
	Socket socket;
	BufferedReader input;
	PrintWriter output;

	
	public Member(String id, String pw, String name, String tel) {
		this.uId = id;
		this.uPw = pw;
		this.uName = name;
		this.uTel = tel;
	}
	
	@Override
	public void run() {
		
			System.out.println("¿¡·¯");
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(true) {
					String protocol = input.readLine();
					if(protocol == null) {
						System.out.println("¿¬°á²÷±è");
						socket.close();
						WorkerThread.userMap.remove(getuId());
						WorkerThread.userListUpload();
						break;
					}
					if(protocol.equals("msg")) {
						String sendMember = input.readLine();
						String receiveMember = input.readLine();
						String message = input.readLine();
						send(message, receiveMember);
					}else if(protocol.equals("quit")) {
						System.out.println(getuId());
						socket.close();
						WorkerThread.userMap.remove(getuId());
						if(WorkerThread.userMap.size()!=0) {
						WorkerThread.userListUpload();
						}
						System.out.println("ÇØ½¬¸Ê »çÀÌÁî : "+WorkerThread.userMap.size());
						System.out.println(getuId()+"´Ô ·Î±×¾Æ¿ô");
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
	}

	public void send(String message, String receiver) {
		Socket receiveSocket = WorkerThread.userMap.get(receiver).getSocket();
		try {
			output = new PrintWriter(new OutputStreamWriter(receiveSocket.getOutputStream()));
			output.println("msg");
			output.println(message);
			output.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuPw() {
		return uPw;
	}

	public void setuPw(String uPw) {
		this.uPw = uPw;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuTel() {
		return uTel;
	}

	public void setuTel(String uTel) {
		this.uTel = uTel;
	}
}