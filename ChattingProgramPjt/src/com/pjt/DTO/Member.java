package com.pjt.DTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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
		while(true) {
			try {
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String message = input.readLine();
				System.out.println(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void send(String message) {
		try {
			output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
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